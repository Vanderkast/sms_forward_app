package com.vanderkast.smsforward;

import android.widget.EditText;
import com.google.android.material.button.MaterialButton;
import com.vanderkast.smsforward.model.handlers.DataHandler;

public class SavingButtonImpl implements SavingButton {
    private boolean saving;

    public SavingButtonImpl() {
    }

    @Override
    public void setUp(MaterialButton button, EditText editText, DataHandler<String> handler) {
        String data = handler.get();
        saving = data != null;
        if (saving) {
            editText.setText(data);
            button.setIcon(button.getContext().getDrawable(R.drawable.lock_closed));
        }
        button.setOnClickListener(view -> {
            saving = !saving;
            if (saving) {
                ((MaterialButton) view).setIcon(button.getContext().getDrawable(R.drawable.lock_closed));
                handler.save(editText.getText().toString());
            } else {
                ((MaterialButton) view).setIcon(button.getContext().getDrawable(R.drawable.lock_open));
                handler.save(null);
            }
        });
    }
}
