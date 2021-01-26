package com.vanderkast.smsforward.email_handler;

public interface EmailSender {
    void send(EmailData data, String to) throws Exception;
}
