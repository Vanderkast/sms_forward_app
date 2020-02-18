package com.vanderkast.smsforward.sms;

import android.database.Cursor;

public interface HistoryLoader {
    Cursor load(String number);
}
