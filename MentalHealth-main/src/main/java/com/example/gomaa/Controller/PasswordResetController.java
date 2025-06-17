package com.example.gomaa.Controller;

import com.example.gomaa.Service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PasswordResetController {

    private PasswordResetService passwordResetService;


    @Autowired
    public PasswordResetController(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }
    //http://localhost:8080/api/forgot-password
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        return ResponseEntity.ok(passwordResetService.sendResetCode(email));
    }

    @PostMapping("/verify-code")
    public ResponseEntity<String> verifyCode(@RequestParam String email, @RequestParam String code) {
        return ResponseEntity.ok(passwordResetService.verifyResetCode(email, code));
    }

    //add method2
    @PostMapping("/verify-code2")
    public ResponseEntity<String> verifyCode2(@RequestParam String code) {
        return ResponseEntity.ok(passwordResetService.verifyResetCode2(code));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String newPassword) {
        return ResponseEntity.ok(passwordResetService.resetPassword(email, newPassword));
    }
}
