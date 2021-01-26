package com.vanderkast.smsforward.sms;

import com.vanderkast.smsforward.email_handler.EmailData;
import com.vanderkast.smsforward.email_handler.EmailDataBuilder;
import com.vanderkast.smsforward.model.Input;
import com.vanderkast.smsforward.sms.handlers.SimpleSberbankSmsHandler;
import com.vanderkast.smsforward.sms.handlers.SmsDateFilter;
import com.vanderkast.smsforward.sms.handlers.SmsHistoryReader;

import javax.inject.Inject;

public class StandardSberbankHandlersChain implements SmsHandlersChain {
    private final SmsHistoryReader historyReader;
    private final SmsDateFilter dateFilter;
    private final SimpleSberbankSmsHandler simpleSberbankSmsHandler;

    @Inject
    public StandardSberbankHandlersChain(SmsHistoryReader historyReader, SmsDateFilter dateFilter, SimpleSberbankSmsHandler simpleSberbankSmsHandler) {
        this.historyReader = historyReader;
        this.dateFilter = dateFilter;
        this.simpleSberbankSmsHandler = simpleSberbankSmsHandler;
    }

    @Override
    public EmailData run(Input input) {
        EmailDataBuilder builder = new EmailDataBuilder();
        builder.setAttachment(null);
        builder.setRecipientAddress(input.getEmail());
        builder.setSubject(standardSubject(input));
        builder.setText(
                simpleSberbankSmsHandler.handle(
                        dateFilter.doFilter(
                                historyReader.read(input.getNumber()), input.getDate())));
        return builder.build();
    }
}
