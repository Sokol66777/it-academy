package com.web.fasad;

public interface EmailService {

    void sendEmail(String toAddress, String fromAddress, String subject, String msgBody);
}
