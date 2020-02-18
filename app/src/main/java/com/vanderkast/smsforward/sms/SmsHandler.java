package com.vanderkast.smsforward.sms;

public interface SmsHandler {
    String handle(HistoryLoader loader, String number, String date);
}
