package com.vanderkast.smsforward;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.vanderkast.smsforward.global.GlobalModule;
import com.vanderkast.smsforward.global.GlobalModuleSingleton;
import com.vanderkast.smsforward.model.Data;
import com.vanderkast.smsforward.model.Email;
import com.vanderkast.smsforward.model.Phone;
import com.vanderkast.smsforward.model.handlers.DataHandler;
import com.vanderkast.smsforward.model.handlers.DataHandlerFactory;
import com.vanderkast.smsforward.model.handlers.EmailHandler;
import com.vanderkast.smsforward.model.handlers.PhoneHandler;

import java.util.Optional;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpGlobal();
        (Optional.ofNullable((DataHandler<String>) DataHandlerFactory.create(Phone.class))).ifPresent(this::setUpPhoneViews);
        Optional.ofNullable((DataHandler<String>) DataHandlerFactory.create(Email.class)).ifPresent(this::setUpEmailViews);
    }

    private void setUpGlobal() {
        new GlobalModuleSingleton(new GlobalModule(this, this));
    }

    private void setUpPhoneViews(DataHandler<String> handler) {
        new SavingButtonImpl().setUp(findViewById(R.id.save_phone_button), findViewById(R.id.phone_input), handler);
    }

    private void setUpEmailViews(DataHandler<String> handler) {
        new SavingButtonImpl().setUp(findViewById(R.id.save_email_button), findViewById(R.id.email_input), handler);
    }
}
