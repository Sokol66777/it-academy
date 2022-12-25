package com.web.fasad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendEmail(String toAddress, String fromAddress, String subject, String msgBody) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress);
        message.setTo(toAddress);
        message.setText(msgBody);
        message.setSubject(subject);
        emailSender.send(message);
    }
}
