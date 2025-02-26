package com.example.gomaa.Controller;

import com.example.gomaa.Dto.*;
import com.example.gomaa.Service.UserService;
import com.example.gomaa.entity.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest userRequest, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        String message = userService.registerUser(userRequest);
        return ResponseEntity.ok(message);
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
//        String message = userService.login(loginRequest);
//        return ResponseEntity.ok(message);
//    }
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    try {
        LoginResponse response = userService.login(loginRequest);
        return ResponseEntity.ok(response);
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}

    @GetMapping("/profile")
    public UserProfileResponse getProfile(@RequestParam String email) {
        return userService.getProfile(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "المستخدم غير موجود"));
    }

    @GetMapping("/profile/{userId}")
    public UserProfileResponse getProfile(@PathVariable Long userId) {
        return userService.getProfile(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "المستخدم غير موجود"));
    }


    @PutMapping(value = "/profile/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<?> updateProfile(
            @PathVariable Long id,
            @RequestPart(value = "data") String data, // Receive as String
            @RequestPart(value = "avatar", required = false) MultipartFile avatar,
            @RequestPart(value = "coverImage", required = false) MultipartFile coverImage) {

        // Convert `data` JSON String to DTO
        ObjectMapper objectMapper = new ObjectMapper();
        UserProfileUpdateDTO userProfileUpdateDTO;
        try {
            userProfileUpdateDTO = objectMapper.readValue(data, UserProfileUpdateDTO.class);
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("Invalid JSON format for data");
        }

        String message = userService.updateUserProfile(id, userProfileUpdateDTO, avatar, coverImage);
        return ResponseEntity.ok(message);
    }


}
