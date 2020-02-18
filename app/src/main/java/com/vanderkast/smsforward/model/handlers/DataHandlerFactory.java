package com.vanderkast.smsforward.model.handlers;

import android.content.Context;
import com.vanderkast.smsforward.R;
import com.vanderkast.smsforward.global.DaggerGlobalComponent;
import com.vanderkast.smsforward.global.GlobalModuleSingleton;
import com.vanderkast.smsforward.model.Data;
import com.vanderkast.smsforward.model.Email;
import com.vanderkast.smsforward.model.Phone;

public class DataHandlerFactory {
    @SuppressWarnings("unchecked")
    public static DataHandler<?> create(Class<? extends Data> dataChild) {
        Context context = DaggerGlobalComponent
                .builder()
                .globalModule(GlobalModuleSingleton.getModule())
                .build()
                .getContext();
        if (dataChild.equals(Email.class))
            return (DataHandler) new EmailHandler(context, context.getString(R.string.email_preference));
        if(dataChild.equals(Phone.class))
            return (DataHandler) new PhoneHandler(context, context.getString(R.string.phone_hint));
        return null;
    }
}
