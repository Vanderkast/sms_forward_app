package com.vanderkast.smsforward.sms;

import android.content.Context;
import com.vanderkast.smsforward.email_handler.EmailData;
import com.vanderkast.smsforward.email_handler.EmailDataBuilder;
import com.vanderkast.smsforward.global.DaggerGlobalComponent;
import com.vanderkast.smsforward.global.GlobalModuleSingleton;
import com.vanderkast.smsforward.model.Input;
import com.vanderkast.smsforward.sms.handlers.SaveCsvHandler;
import com.vanderkast.smsforward.sms.handlers.SmsDateFilter;
import com.vanderkast.smsforward.sms.handlers.SmsHistoryReader;

import javax.inject.Inject;
import java.io.IOException;

public class CsvSmsHandlersChain implements SmsHandlersChain {
    private final SmsDateFilter dateFilter;
    private final SmsHistoryReader historyReader;
    private final SaveCsvHandler csvHandler;
    private final Context context;

    @Inject
    public CsvSmsHandlersChain(SmsDateFilter dateFilter, SmsHistoryReader historyReader, SaveCsvHandler csvHandler) {
        this.dateFilter = dateFilter;
        this.historyReader = historyReader;
        this.csvHandler = csvHandler;
        context = DaggerGlobalComponent.builder().globalModule(GlobalModuleSingleton.getModule()).build().getContext();
    }

    @Override
    public EmailData run(Input input) {
        EmailDataBuilder dataBuilder = new EmailDataBuilder();
        dataBuilder.setSubject(standardSubject(input));
        dataBuilder.setRecipientAddress(input.getEmail());
        try {
            dataBuilder.setAttachment(csvHandler.parse(dateFilter.doFilter(historyReader.read(input.getNumber()), input.getDate()), context));
            dataBuilder.setText("Во вложении .csv  с историей сообщений");
        } catch (IOException e) {
            dataBuilder.setAttachment(null);
            dataBuilder.setText(
                    "Во время записи .csv файла произошла ошибка. Обратитесь к разработчику. \n Ошибка:\n"
                            + e.getMessage());
        }
        return dataBuilder.build();
    }
}
