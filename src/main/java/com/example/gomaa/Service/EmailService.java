package com.example.gomaa.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    //send code
    public void sendResetCode(String to, String resetCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("إعادة تعيين كلمة المرور");
        message.setText("كود استعادة كلمة المرور الخاص بك: " + resetCode);
        mailSender.send(message);
    }
}
