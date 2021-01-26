package com.vanderkast.smsforward;

import com.vanderkast.smsforward.email_handler.EmailSender;
import com.vanderkast.smsforward.email_handler.GMailSender;
import com.vanderkast.smsforward.sms.HistoryLoader;
import com.vanderkast.smsforward.sms.HistoryLoaderImpl;
import com.vanderkast.smsforward.sms.handlers.SmsDateFilter;
import com.vanderkast.smsforward.sms.handlers.SmsDateFilterImpl;
import com.vanderkast.smsforward.sms.handlers.SmsHistoryReader;
import com.vanderkast.smsforward.sms.handlers.SmsHistoryReaderImpl;
import dagger.Binds;
import dagger.Module;

@Module
public abstract class DependencyModule {
    @Binds
    public abstract SmsHistoryReader bindSmsHistoryReader(SmsHistoryReaderImpl implementation);

    @Binds
    public abstract SmsDateFilter bindSmsDateFilter(SmsDateFilterImpl implementation);

    @Binds
    public abstract HistoryLoader bindHistoryLoader(HistoryLoaderImpl implementation);

    @Binds
    public abstract EmailSender bindEmailSender(GMailSender implementation);
}
