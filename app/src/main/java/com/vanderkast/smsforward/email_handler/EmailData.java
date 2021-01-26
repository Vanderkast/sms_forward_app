package com.vanderkast.smsforward.email_handler;

public class EmailData {
    private final String recipientAddress;
    private final String subject;
    private final String text;
    private final String attachment;

    public EmailData(String recipientAddress, String subject, String text) {
        this.recipientAddress = recipientAddress;
        this.subject = subject;
        this.text = text;
        attachment = null;
    }

    public EmailData(String recipientAddress, String subject, String text, String attachment) {
        this.recipientAddress = recipientAddress;
        this.subject = subject;
        this.text = text;
        this.attachment = attachment;
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

    public String getAttachment() {
        return attachment;
    }
}
