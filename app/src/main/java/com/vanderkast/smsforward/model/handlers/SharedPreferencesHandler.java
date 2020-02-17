package com.vanderkast.smsforward.model.handlers;

import android.content.Context;
import android.content.SharedPreferences;

public abstract class SharedPreferencesHandler<T> implements DataHandler<T> {
    protected final String preferencesName;
    private final Context context;

    protected SharedPreferencesHandler(Context context, String preferencesName) {
        this.context = context;
        this.preferencesName = preferencesName;
    }

    @Override
    public void save(T data) {
        if (context != null) {
            SharedPreferences.Editor editor =
                    context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE).edit();
            if (data == null)
                editor.clear();
            else
                write(editor, data);
            editor.apply();
        } else throw new NoContextFoundException();
    }

    private void write(SharedPreferences.Editor editor, T data) {
        if (data instanceof String) {
            editor.putString(preferencesName, (String) data);
            return;
        }
        if (data instanceof Integer) {
            editor.putInt(preferencesName, (Integer) data);
            return;
        }
        if (data instanceof Long) {
            editor.putLong(preferencesName, (Long) data);
            return;
        }
        if (data instanceof Boolean) {
            editor.putBoolean(preferencesName, (Boolean) data);
        }
    }

    @Override
    public T get() {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(preferencesName, Context.MODE_PRIVATE);
            return read(preferences);
        } else throw new NoContextFoundException();
    }

    protected abstract T read(SharedPreferences sharedPreferences);

    static class NoContextFoundException extends RuntimeException {

    }
}
