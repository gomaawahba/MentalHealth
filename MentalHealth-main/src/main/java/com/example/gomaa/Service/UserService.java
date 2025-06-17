package com.example.gomaa.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.gomaa.Dto.*;
import com.example.gomaa.Repository.UserRepository;
import com.example.gomaa.Util.JwtUtil;
import com.example.gomaa.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class UserService {

    private UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private Cloudinary cloudinary;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String registerUser(UserRequest userRequest) {
        Optional<Users> existingUser = userRepository.findByEmail(userRequest.getEmail());

        if (existingUser.isPresent()) {
            return "البريد الإلكتروني مسجل بالفعل";
        }

        Users user = new Users();
        user.setName(userRequest.getName());
        user.setBirthdate(userRequest.getBirthdate());
        user.setEmail(userRequest.getEmail());
        user.setCountry(userRequest.getCountry());
        // In a real app, hash the password before saving!
        user.setPassword(passwordEncoder.encode(userRequest.getPassword())); // Hash password
        userRepository.save(user);
        return "تم انشاء الحساب";
    }

    //Login
//    public String login(LoginRequest loginRequest) {
//        String email = loginRequest.getEmail();
//        String password = loginRequest.getPassword();
//
//        // Validate Email Format
//        if (!isValidEmail(email)) {
//            return "البريد الإلكتروني غير صالح";
//        }
//
//        // Find User by Email
//        Optional<Users> userOptional = userRepository.findByEmail(email);
//        if (userOptional.isEmpty()) {
//            return "اسم المستخدم أو كلمة المرور غير صحيحة";
//        }
//
//        Users user = userOptional.get();
//
//        // Validate Password
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            return "اسم المستخدم أو كلمة المرور غير صحيحة";
//        }
//
//        return "تم تسجيل الدخول بنجاح";
    ////        return JwtUtil.generateToken(user.getId(), user.getEmail());
//    }
//public LoginResponse login(LoginRequest loginRequest) {
//    String email = loginRequest.getEmail();
//    String password = loginRequest.getPassword();
//
//    if (!isValidEmail(email)) {
//        throw new IllegalArgumentException("البريد الإلكتروني غير صالح");
//    }
//
//    Users user = userRepository.findByEmail(email)
//            .orElseThrow(() -> new IllegalArgumentException("اسم المستخدم أو كلمة المرور غير صحيحة"));
//
//    if (!passwordEncoder.matches(password, user.getPassword())) {
//        throw new IllegalArgumentException("اسم المستخدم أو كلمة المرور غير صحيحة");
//    }
//
//    String token = jwtService.generateToken(user.getEmail());
//    return new LoginResponse(user.getId(), token);
//}

    // Email Validation: Must be @gmail.com

    public LoginResponse login(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("البريد الإلكتروني غير صالح");
        }

        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("اسم المستخدم أو كلمة المرور غير صحيحة"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("اسم المستخدم أو كلمة المرور غير صحيحة");
        }

        String accessToken = jwtService.generateToken(user.getEmail());
        String refreshToken = jwtService.generateRefreshToken(user.getEmail());

        return new LoginResponse(user.getId(), accessToken, refreshToken);
    }


    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        return Pattern.matches(emailRegex, email);
    }


    //profile with email
    public Optional<UserProfileResponse> getProfile(String email) {
        return userRepository.findByEmail(email).map(user -> {
            String avatar = (user.getAvatar() != null && !user.getAvatar().isEmpty())
                    ? user.getAvatar()
                    : "https://example.com/default-avatar.png";
            String coverImage = (user.getCoverImage() != null && !user.getCoverImage().isEmpty())
                    ? user.getCoverImage()
                    : "https://example.com/default-cover.jpg";
            String birthdate = "";
            if (user.getBirthdate() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                birthdate = sdf.format(user.getBirthdate());
            }
            return new UserProfileResponse(
                    user.getName(),
                    user.getEmail(),
                    user.getCountry(),
                    birthdate,
                    avatar,
                    coverImage
            );
        });
    }

    //profile by id
    public Optional<UserProfileResponse> getProfile(Long userId) {
        return userRepository.findById(userId).map(user -> {
            String avatar = (user.getAvatar() != null && !user.getAvatar().isEmpty())
                    ? user.getAvatar()
                    : "https://example.com/default-avatar.png";

            String coverImage = (user.getCoverImage() != null && !user.getCoverImage().isEmpty())
                    ? user.getCoverImage()
                    : "https://example.com/default-cover.jpg";

            String birthdate = "";
            if (user.getBirthdate() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                birthdate = sdf.format(user.getBirthdate());
            }

            return new UserProfileResponse(
                    user.getName(),
                    user.getEmail(),
                    user.getCountry(),
                    birthdate,
                    avatar,
                    coverImage
            );
        });
    }


    public String updateUserProfile(Long id, UserProfileUpdateDTO dto, MultipartFile avatar, MultipartFile coverImage) {
        Optional<Users> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) {
            return "المستخدم غير موجود";
        }

        Users user = userOptional.get();
        user.setName(dto.getName());
        user.setBirthdate(dto.getBirthdate());
        user.setCountry(dto.getCountry());

        try {
            if (avatar != null && !avatar.isEmpty()) {
                Map uploadResult = cloudinary.uploader().upload(avatar.getBytes(),
                        ObjectUtils.asMap("folder", "avatars"));
                user.setAvatar((String) uploadResult.get("secure_url"));
            }

            if (coverImage != null && !coverImage.isEmpty()) {
                Map uploadResult = cloudinary.uploader().upload(coverImage.getBytes(),
                        ObjectUtils.asMap("folder", "covers"));
                user.setCoverImage((String) uploadResult.get("secure_url"));
            }
        } catch (IOException e) {
            return "حدث خطأ أثناء تحميل الصور: " + e.getMessage();
        }

        userRepository.save(user);
        return "تم تحديث البيانات بنجاح";
    }
}
