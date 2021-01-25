package com.vanderkast.smsforward;


import android.os.AsyncTask;
import com.vanderkast.smsforward.email_handler.EmailData;
import com.vanderkast.smsforward.email_handler.EmailInfo;
import com.vanderkast.smsforward.email_handler.EmailSender;
import com.vanderkast.smsforward.sms.HistoryLoaderImpl;
import com.vanderkast.smsforward.sms.SmsHandlerFactory;
import com.vanderkast.smsforward.sms.SmsModule;
import dagger.Component;

import javax.inject.Inject;

public class HandleTask extends AsyncTask<HandleTask.InputValues, Void, Boolean> {
    @Inject
    EmailSender emailSender;

    private final OnDoneAction onDoneAction;

    public HandleTask(OnDoneAction onDoneAction) {
        this.onDoneAction = onDoneAction;

        DaggerHandleTask_TaskComponent.create().inject(this);
    }

    @Override
    protected Boolean doInBackground(InputValues... inputValuesArr) {
        InputValues inputValues = inputValuesArr[0];
        String emailText = SmsHandlerFactory.byPhone(inputValues.phone)
                .handle(new HistoryLoaderImpl(), inputValues.phone, inputValues.date);
        try {
            sendEmail(fillEmailData(inputValues, emailText));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        onDoneAction.onDone(success);
    }

    private void sendEmail(EmailData emailData) throws Exception {
        emailSender.send(emailData.getSubject(),
                emailData.getText(),
                EmailInfo.address,
                emailData.getRecipientAddress());
    }

    private EmailData fillEmailData(InputValues inputValues, String text) {
        return new EmailData(inputValues.email, "SMS " + inputValues.phone + " " + inputValues.date, text);
    }

    static class InputValues {
        private final String email;
        private final String phone;
        private final String date;

        InputValues(String email, String phone, String date) {
            this.email = email;
            this.phone = phone;
            this.date = date;
        }
    }

    @FunctionalInterface
    public interface OnDoneAction {
        void onDone(Boolean success);
    }

    @Component(modules = SmsModule.class)
    public interface TaskComponent {
        void inject(HandleTask task);

        EmailSender smsSender();
    }
}