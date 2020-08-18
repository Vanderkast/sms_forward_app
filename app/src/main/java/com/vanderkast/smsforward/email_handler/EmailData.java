package com.vanderkast.smsforward.email_handler;

import java.io.File;

public class EmailData {
    private final String recipientAddress;
    private final String subject;
    private final String text;
    private final File attachment;

    public EmailData(String recipientAddress, String subject, String text) {
        this.recipientAddress = recipientAddress;
        this.subject = subject;
        this.text = text;
        attachment = null;
    }

    public EmailData(String recipientAddress, String subject, String text, File attachment) {
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

    public File getAttachment() {
        return attachment;
    }
}
