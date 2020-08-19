package com.vanderkast.smsforward.sms.handlers;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.core.util.Pair;
import com.opencsv.CSVWriter;

import javax.inject.Inject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SaveCsvHandler {
    public final static String CSV_FILE = "history.csv";

    @Inject
    public SaveCsvHandler() {
    }

    @SuppressLint("SimpleDateFormat")
    public String parse(List<Pair<Date, String>> history, Context context) throws IOException {
        String path = getPath(context);
        CSVWriter writer = new CSVWriter(new FileWriter(path, false));
        DateFormat format = new SimpleDateFormat("dd:MM:yyyy HH:mm");
        history.forEach(pair -> writer.writeNext(new String[]{format.format(pair.first), pair.second}));
        writer.close();
        return path;
    }

    private String getPath(Context context) {
        return context.getApplicationInfo().dataDir + File.separatorChar + CSV_FILE;
    }
}
