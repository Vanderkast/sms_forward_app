package com.vanderkast.smsforward.sms.handlers;

import android.annotation.SuppressLint;
import androidx.core.util.Pair;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.*;

public class SimpleSberbankSmsHandler {

    @Inject
    public SimpleSberbankSmsHandler() {
    }

    @SuppressLint("SimpleDateFormat")
    public String handle(List<Pair<Date, String>> history) {
        final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("HH:mm");
        Map<String, List<String>> map = new HashMap<>();
        history.forEach(pair -> {
            String card = pair.second.split(" ")[0];
            if (!map.containsKey(card))
                map.put(card, new ArrayList<>());
            map.get(card).add(dateTimeFormat.format(pair.first) + ": " + pair.second);
        });

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
