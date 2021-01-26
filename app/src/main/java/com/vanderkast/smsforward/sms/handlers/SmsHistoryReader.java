package com.vanderkast.smsforward.sms.handlers;

import androidx.core.util.Pair;

import java.util.Date;
import java.util.List;

public interface SmsHistoryReader {
    List<Pair<Date, String>> read(String number);
}
