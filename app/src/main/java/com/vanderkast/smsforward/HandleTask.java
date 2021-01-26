package com.vanderkast.smsforward;


import android.os.AsyncTask;
import com.vanderkast.smsforward.email_handler.EmailData;
import com.vanderkast.smsforward.email_handler.EmailInfo;
import com.vanderkast.smsforward.email_handler.EmailSender;
import com.vanderkast.smsforward.model.Input;
import com.vanderkast.smsforward.sms.SmsHandlersChainFacade;
import dagger.Component;

import javax.inject.Inject;

public class HandleTask extends AsyncTask<Input, Void, Boolean> {
    @Inject
    EmailSender emailSender;

    @Inject
    SmsHandlersChainFacade facade;

    private final OnDoneAction onDoneAction;

    public HandleTask(OnDoneAction onDoneAction) {
        this.onDoneAction = onDoneAction;

        DaggerHandleTask_TaskComponent.create().inject(this);
    }

    @Override
    protected Boolean doInBackground(Input... inputs) {
        EmailData data = facade.run(inputs[0]);
        try {
            emailSender.send(data, EmailInfo.address);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        onDoneAction.onDone(success);
    }

    @FunctionalInterface
    public interface OnDoneAction {
        void onDone(Boolean success);
    }

    @Component(modules = DependencyModule.class)
    public interface TaskComponent {
        void inject(HandleTask task);

        EmailSender smsSender();

        SmsHandlersChainFacade facade();
    }
}
