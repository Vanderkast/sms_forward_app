package com.vanderkast.smsforward.sms.handlers;

import androidx.core.util.Pair;

import java.util.Date;
import java.util.List;

public interface SmsDateFilter {
    List<Pair<Date, String>> doFilter(List<Pair<Date, String>> history, String date);
}
