package com.vanderkast.smsforward.sms;

import android.annotation.SuppressLint;
import android.database.Cursor;
import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.*;

public class SimpleSmsHandler implements SmsHandler {
    @SuppressLint("SimpleDateFormat")
    @Override
    public String handle(HistoryLoader loader, String number, @NonNull String selectedDate) {
        StringBuilder builder = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        Cursor cursor = loader.load(number);
        if (cursor != null) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Date smsDate = new Date(Long.parseLong(cursor.getString(1)));
                String formattedDate = dateFormat.format(smsDate);
                String body = cursor.getString(2);
                if (selectedDate.isEmpty() || formattedDate.equals(selectedDate)) {
                    builder.append(dateTimeFormat.format(smsDate)).append(" ").append(body).append("\n");
                }
            }
            cursor.close();
        }
        return builder.toString();
    }
}
