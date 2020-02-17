package com.vanderkast.smsforward.global;

import android.app.Activity;
import android.content.Context;
import dagger.Component;

@Component(modules = GlobalModule.class)
public interface GlobalComponent {
    Context getContext();
    Activity getActivity();
}
