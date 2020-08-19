package com.vanderkast.smsforward.sms;

import com.vanderkast.smsforward.email_handler.EmailData;
import com.vanderkast.smsforward.email_handler.EmailDataBuilder;
import com.vanderkast.smsforward.model.Input;
import com.vanderkast.smsforward.sms.handlers.SimpleSmsHandler;
import com.vanderkast.smsforward.sms.handlers.SmsDateFilter;
import com.vanderkast.smsforward.sms.handlers.SmsHistoryReader;

import javax.inject.Inject;

public class StandardHandlersChain implements SmsHandlersChain {
    private final SimpleSmsHandler simpleSmsHandler;
    private final SmsHistoryReader historyReader;
    private final SmsDateFilter dateFilter;

    @Inject
    public StandardHandlersChain(SmsHistoryReader historyReader, SmsDateFilter dateFilter, SimpleSmsHandler simpleSmsHandler) {
        this.simpleSmsHandler = simpleSmsHandler;
        this.historyReader = historyReader;
        this.dateFilter = dateFilter;
    }

    @Override
    public EmailData run(Input input) {
        EmailDataBuilder builder = new EmailDataBuilder();
        builder.setSubject(standardSubject(input));
        builder.setRecipientAddress(input.getEmail());
        builder.setAttachment(null);
        builder.setText(
                simpleSmsHandler.handle(
                        dateFilter.doFilter(
                                historyReader.read(input.getNumber()), input.getDate())));
        return builder.build();
    }
}
