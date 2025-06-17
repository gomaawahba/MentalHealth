package com.example.gomaa.Service;

import com.example.gomaa.Repository.UserRepository;
import com.example.gomaa.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class PasswordResetService {

    private UserRepository userRepository;

    private EmailService emailService;

    @Autowired
    public PasswordResetService(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public String sendResetCode(String email) {
        Optional<Users> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return "البريد الإلكتروني غير مسجل!";
        }
        // إنشاء كود عشوائي من 6 أرقام
        String resetCode = String.valueOf(new Random().nextInt(900000) + 100000);

        Users user = userOptional.get();
        user.setResetCode(resetCode);
        userRepository.save(user);

        emailService.sendResetCode(email, resetCode);
        return "تم إرسال كود استعادة كلمة المرور إلى بريدك الإلكتروني.";
    }

    public String verifyResetCode(String email, String code) {
        Optional<Users> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return "البريد الإلكتروني غير مسجل!";
        }

        Users user = userOptional.get();
        if (!code.equals(user.getResetCode())) {
            return "كود التحقق غير صحيح!";
        }

        return "تم التحقق بنجاح! يمكنك الآن إعادة تعيين كلمة المرور.";
    }
    //new add
    public String verifyResetCode2(String code) {
        Optional<Users> userOptional = userRepository.findByResetCode(code);
        if (userOptional.isEmpty()) {
            return "كود التحقق غير صحيح أو منتهي الصلاحية!";
        }

        return "تم التحقق بنجاح! يمكنك الآن إعادة تعيين كلمة المرور.";
    }

    public String resetPassword(String email, String newPassword) {
        Optional<Users> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return "البريد الإلكتروني غير مسجل!";
        }

        Users user = userOptional.get();
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        user.setResetCode(null); // حذف كود الاستعادة بعد الاستخدام
        userRepository.save(user);

        return "تم إعادة تعيين كلمة المرور بنجاح.";
    }
}
