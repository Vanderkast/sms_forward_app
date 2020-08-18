package com.vanderkast.smsforward.sms;

public interface SmsHandler<Result> {
    Result handle(HistoryLoader loader, String number, String date);
}
