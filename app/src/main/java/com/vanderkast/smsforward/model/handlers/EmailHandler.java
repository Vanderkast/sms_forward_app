package com.vanderkast.smsforward.model.handlers;

import android.content.Context;
import android.content.SharedPreferences;

public class EmailHandler extends SharedPreferencesHandler<String> {

    public EmailHandler(Context context, String preferencesName) {
        super(context, preferencesName);
    }

    @Override
    protected String read(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(preferencesName, null);
    }
}
