package com.vanderkast.smsforward.sms_refactor;

import androidx.core.util.Pair;

import java.util.Date;
import java.util.List;

public interface SmsHistoryReader {
    List<Pair<Date, String>> read(String number);
}
