package com.vanderkast.smsforward.sms;

public class SmsHandlerFactory {
    private static final String SBERBANK = "900";

    public static SmsHandler byPhone(String phone) {
        if(phone.equals(SBERBANK))
            return new SberbankSmsHandlerImpl();
        return new SimpleSmsHandler();
    }
}
