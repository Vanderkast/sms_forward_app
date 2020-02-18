package com.vanderkast.smsforward.sms;

import com.vanderkast.smsforward.email_handler.EmailSender;
import com.vanderkast.smsforward.email_handler.GMailSender;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class SmsModule {
    @Binds
    public abstract EmailSender bindSmsSender(GMailSender implementation);
}
