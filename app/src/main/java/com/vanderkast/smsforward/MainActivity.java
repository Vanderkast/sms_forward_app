package com.vanderkast.smsforward;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.snackbar.Snackbar;
import com.vanderkast.smsforward.extension.DatePickerFragment;
import com.vanderkast.smsforward.extension.SavingButton;
import com.vanderkast.smsforward.extension.SavingButtonImpl;
import com.vanderkast.smsforward.global.GlobalModule;
import com.vanderkast.smsforward.global.GlobalModuleSingleton;
import com.vanderkast.smsforward.model.Email;
import com.vanderkast.smsforward.model.Input;
import com.vanderkast.smsforward.model.Phone;
import com.vanderkast.smsforward.model.handlers.DataHandler;
import com.vanderkast.smsforward.model.handlers.DataHandlerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private SavingButton phoneViewSaving;
    private SavingButton emailViewSaving;

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpGlobal();

        Optional.ofNullable((DataHandler<String>) DataHandlerFactory.create(Phone.class))
                .ifPresent(this::setUpPhoneViews);
        Optional.ofNullable((DataHandler<String>) DataHandlerFactory.create(Email.class))
                .ifPresent(this::setUpEmailViews);
        setUpDatePicker();
        setUpSendButton();
    }

    private void setUpGlobal() {
        new GlobalModuleSingleton(new GlobalModule(this, this));
    }

    private void setUpPhoneViews(DataHandler<String> handler) {
        phoneViewSaving = new SavingButtonImpl();
        phoneViewSaving.setUp(findViewById(R.id.save_phone_button), findViewById(R.id.phone_input), handler);
    }

    private void setUpEmailViews(DataHandler<String> handler) {
        emailViewSaving = new SavingButtonImpl();
        emailViewSaving.setUp(findViewById(R.id.save_email_button), findViewById(R.id.email_input), handler);
    }

    @SuppressLint("SimpleDateFormat")
    private void setUpDatePicker() {
        ((EditText) findViewById(R.id.date_input)).setText(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
        (findViewById(R.id.date_button)).setOnClickListener(view -> {
            DialogFragment datePicker = new DatePickerFragment();
            datePicker.show(getSupportFragmentManager(), "date_picker");
        });
    }

    private void setUpSendButton() {
        (findViewById(R.id.send_sms_to_email_button)).setOnClickListener(view -> {
            findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
            askForPermission();
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);
        String currentDateString = DateFormat.getDateInstance(DateFormat.SHORT).format(c.getTime());
        currentDateString = currentDateString.replaceAll("/", ".");

        EditText textView = findViewById(R.id.date_input);
        textView.setText(currentDateString);
    }

    private void askForPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                == PackageManager.PERMISSION_GRANTED)
            new HandleTask(this::onSendingDone)
                    .execute(new Input(getDate(), getPhone(), getEmail(), isWithCsvFile()));
        else
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_SMS}, 911);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 911) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                findViewById(R.id.send_sms_to_email_button).setClickable(false);
            } else {
                new HandleTask(this::onSendingDone)
                        .execute(new Input(getDate(), getPhone(), getEmail(), isWithCsvFile()));
            }
        }
    }

    private void onSendingDone(Boolean success) {
        findViewById(R.id.progressBar).setVisibility(View.GONE);
        int message = success ? R.string.sending_complete_successfully : R.string.sending_gone_wrong;
        Snackbar.make(findViewById(R.id.main_coordinator_layout), getString(message), Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        Optional.ofNullable(phoneViewSaving).ifPresent(SavingButton::onStop);
        Optional.ofNullable(emailViewSaving).ifPresent(SavingButton::onStop);
        super.onStop();
    }

    private String getPhone() {
        return ((EditText) findViewById(R.id.phone_input)).getText().toString();
    }

    private String getDate() {
        return ((EditText) findViewById(R.id.date_input)).getText().toString();
    }

    private String getEmail() {
        return ((EditText) findViewById(R.id.email_input)).getText().toString();
    }

    private boolean isWithCsvFile() {
        return ((CheckBox) findViewById(R.id.sendAsCsvFile)).isChecked();
    }
}
