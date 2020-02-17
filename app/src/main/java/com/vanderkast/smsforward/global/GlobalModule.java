package com.vanderkast.smsforward.global;

import android.app.Activity;
import android.content.Context;
import dagger.Module;
import dagger.Provides;

@Module
public class GlobalModule {
    private final Context context;
    private final Activity activity;

    public GlobalModule(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    @Provides
    public Context getContext() {
        return context;
    }

    @Provides
    public Activity getActivity() {
        return activity;
    }
}
