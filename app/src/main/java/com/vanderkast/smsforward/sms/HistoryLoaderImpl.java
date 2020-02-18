package com.vanderkast.smsforward.sms;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.vanderkast.smsforward.global.DaggerGlobalComponent;
import com.vanderkast.smsforward.global.GlobalModuleSingleton;

import javax.inject.Inject;

public class HistoryLoaderImpl implements HistoryLoader {

    @Inject
    public HistoryLoaderImpl() {
    }

    @Override
    public Cursor load(String number) {
        Context context = DaggerGlobalComponent.builder().globalModule(GlobalModuleSingleton.getModule()).build().getContext();
        Uri uriSms = Uri.parse("content://sms/inbox");
        return context.getContentResolver()
                .query(
                        uriSms,
                        new String[]{ "address", "date", "body"},
                        "address=?",
                        new String[]{number},
                        null);
    }
}
