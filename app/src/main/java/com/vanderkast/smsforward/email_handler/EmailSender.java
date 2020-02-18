package com.vanderkast.smsforward.email_handler;

public interface EmailSender {
    void send(String subject, String body, String sender, String recipients) throws Exception;
}
