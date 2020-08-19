package com.vanderkast.smsforward.sms;

import com.vanderkast.smsforward.email_handler.EmailData;
import com.vanderkast.smsforward.model.Input;

public interface SmsHandlersChain {
    EmailData run(Input input);

    default String standardSubject(Input input) {
        return "SMS " + input.getNumber() + " " + input.getDate();
    }
}
