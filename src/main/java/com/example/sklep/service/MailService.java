package com.example.sklep.service;

public interface MailService {
    void sendMail(String to, String subject, String text, boolean isHtmlContent);

}
