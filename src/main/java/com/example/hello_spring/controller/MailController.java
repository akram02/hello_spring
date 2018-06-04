package com.example.hello_spring.controller;

import com.example.hello_spring.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {
    @Autowired
    EmailService emailService;

    @GetMapping("/send")
    void sendMail(){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo("akrammiru@gmail.com");
        mailMessage.setSubject("Registration Confirmation");
        mailMessage.setText("To confirm e-mail click this link");
        mailMessage.setFrom("noreply@akramkhan.com");

        emailService.sendEmail(mailMessage);
    }
}
