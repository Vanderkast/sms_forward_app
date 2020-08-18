package com.vanderkast.smsforward.sms;

import com.vanderkast.smsforward.abs.AbsDataHandler;
import com.vanderkast.smsforward.abs.DataKeeper;

import javax.inject.Inject;

public class SmsDateFilter extends AbsDataHandler {
    @Inject
    HistoryLoader historyLoader;

    public SmsDateFilter(DataKeeper dataKeeper) {
        super(dataKeeper);
    }

    @Override
    public void proceed() {

        super.proceed();
    }
}
