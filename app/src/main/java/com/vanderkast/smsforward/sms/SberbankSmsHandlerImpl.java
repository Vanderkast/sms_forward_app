package com.vanderkast.smsforward.sms;

import android.annotation.SuppressLint;
import android.database.Cursor;
import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.*;

public class SberbankSmsHandlerImpl implements SmsHandler {

    @SuppressWarnings("ConstantConditions")
    @SuppressLint("SimpleDateFormat")
    @Override
    public String handle(HistoryLoader loader, String number, @NonNull String selectedDate) {
        Map<String, List<String>> map = new HashMap<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        Cursor cursor = loader.load(number);
        if (cursor != null) {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Date smsDate = new Date(Long.parseLong(cursor.getString(1)));
                String formattedDate = dateFormat.format(smsDate);
                String body = cursor.getString(2);
                String card = body.split(" ")[0];
                if (selectedDate.isEmpty() || formattedDate.equals(selectedDate)) {
                    if (!map.containsKey(card))
                        map.put(card, new LinkedList<>());
                    map.get(card).add(dateTimeFormat.format(smsDate) + " " + body);
                }
            }
            cursor.close();
        }
        return fromMap(map);
    }

    private String fromMap(Map<String, List<String>> map) {
        StringBuilder builder = new StringBuilder();
        map.forEach((k, arr) -> {
            builder.append(k).append(":\n\n");
            arr.forEach(body -> builder.append(body).append("\n"));
        });
        return builder.toString();
    }
}
