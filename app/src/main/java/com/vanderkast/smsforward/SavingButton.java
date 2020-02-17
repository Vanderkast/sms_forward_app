package com.vanderkast.smsforward;

import android.widget.EditText;
import com.google.android.material.button.MaterialButton;
import com.vanderkast.smsforward.model.handlers.DataHandler;

public interface SavingButton {
    void setUp(MaterialButton button, EditText editText, DataHandler<String> handler);
}
