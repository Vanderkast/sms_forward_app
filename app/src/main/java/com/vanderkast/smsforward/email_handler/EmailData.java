package com.vanderkast.smsforward.email_handler;

public class EmailData {
    private final String recipientAddress;
    private final String subject;
    private final String text;

    public EmailData(String recipientAddress, String subject, String text) {
        this.recipientAddress = recipientAddress;
        this.subject = subject;
        this.text = text;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public String getSubject() {
        return subject;
    }

    public String getText() {
        return text;
    }
}
