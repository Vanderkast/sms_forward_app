package com.vanderkast.smsforward.sms_refactor;

import android.annotation.SuppressLint;
import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import com.vanderkast.smsforward.sms.HistoryLoader;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmsHistoryReaderImpl implements SmsHistoryReader {
    private final HistoryLoader loader;

    @Inject
    public SmsHistoryReaderImpl(HistoryLoader loader) {
        this.loader = loader;
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public List<Pair<Date, String>> read(String number) {
        List<Pair<Date, String>> history = new ArrayList<>();
        Cursor cursor = loader.load(number);
        if (cursor != null) {
            while (cursor.moveToNext())
                history.add(parse(cursor));
        }
        return history;
    }

    Pair<Date, String> parse(@NonNull Cursor cursor) {
        Date date = new Date(Long.parseLong(cursor.getString(1)));
        String text = cursor.getString(2);
        return new Pair<>(date, text);
    }
}
