package com.vanderkast.smsforward;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.vanderkast.smsforward.global.GlobalModule;
import com.vanderkast.smsforward.global.GlobalModuleSingleton;
import com.vanderkast.smsforward.model.Email;
import com.vanderkast.smsforward.model.handlers.DataHandler;
import com.vanderkast.smsforward.model.handlers.DataHandlerFactory;
import com.vanderkast.smsforward.model.handlers.EmailHandler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpGlobal();
        EmailHandler handler = (EmailHandler) DataHandlerFactory.create(Email.class);
    }

    private void setUpGlobal() {
        new GlobalModuleSingleton(new GlobalModule(this, this));
    }
}
