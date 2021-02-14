package com.autodeli.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService
{
    @Autowired
    private JavaMailSender emailSender;

    public void sendMail(SimpleMailMessage message) {
        emailSender.send(message);
    }
}