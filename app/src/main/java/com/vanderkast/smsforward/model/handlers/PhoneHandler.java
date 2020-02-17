package com.vanderkast.smsforward.model.handlers;

import android.content.Context;
import android.content.SharedPreferences;

public class PhoneHandler extends SharedPreferencesHandler<String>{

    public PhoneHandler(Context context, String preferencesName) {
        super(context, preferencesName);
    }

    @Override
    protected String read(SharedPreferences sharedPreferences) {
        return null;
    }
}
