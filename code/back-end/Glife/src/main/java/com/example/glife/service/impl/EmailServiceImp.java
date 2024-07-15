package com.example.glife.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImp {
    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String to, String verificationCode) throws MessagingException{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject("Glife Email Verification");
        helper.setText("Your verification code is: " +
                verificationCode+
                ", the code will be expired within 10 minutes");

        mailSender.send(message);
    }
}
