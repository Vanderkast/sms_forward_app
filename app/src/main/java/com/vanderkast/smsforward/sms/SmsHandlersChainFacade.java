package com.vanderkast.smsforward.sms;

import com.vanderkast.smsforward.email_handler.EmailData;
import com.vanderkast.smsforward.model.Input;

import javax.inject.Inject;

public class SmsHandlersChainFacade {
    private final CsvSmsHandlersChain csvChain;
    private final StandardHandlersChain standardChain;
    private final StandardSberbankHandlersChain sberChain;

    @Inject
    public SmsHandlersChainFacade(CsvSmsHandlersChain csvChain, StandardHandlersChain standardChain, StandardSberbankHandlersChain sberChain) {
        this.csvChain = csvChain;
        this.standardChain = standardChain;
        this.sberChain = sberChain;
    }

    public EmailData run(Input input) {
        if (input.isAsCsvFile())
            return csvChain.run(input);
        if (input.getNumber().equals("900"))
            return sberChain.run(input);
        return standardChain.run(input);
    }
}
