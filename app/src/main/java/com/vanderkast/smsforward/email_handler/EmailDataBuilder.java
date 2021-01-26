package com.vanderkast.smsforward.email_handler;

public final class EmailDataBuilder {
    private String recipientAddress;
    private String subject;
    private String text;
    private String attachment;

    public EmailData build() {
        return new EmailData(recipientAddress, subject, text, attachment);
    }

    public EmailDataBuilder setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailDataBuilder setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
        return this;
    }

    public EmailDataBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public EmailDataBuilder setAttachment(String attachment) {
        this.attachment = attachment;
        return this;
    }
}
