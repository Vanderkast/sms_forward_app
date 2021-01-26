package com.vanderkast.smsforward.sms.handlers;

import android.annotation.SuppressLint;
import androidx.core.util.Pair;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SimpleSmsHandler {
    @Inject
    public SimpleSmsHandler() {
    }

    @SuppressLint("SimpleDateFormat")
    public String handle(List<Pair<Date, String>> history) {
        StringBuilder builder = new StringBuilder();
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        history.forEach(pair ->
                builder.append(timeFormat.format(pair.first))
                        .append(": ")
                        .append(pair.second)
        );
        return builder.toString();
    }
}
