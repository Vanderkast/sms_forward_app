package com.vanderkast.smsforward.extension;

import android.widget.EditText;
import com.google.android.material.button.MaterialButton;
import com.vanderkast.smsforward.R;
import com.vanderkast.smsforward.model.handlers.DataHandler;

import javax.inject.Inject;

public class SavingButtonImpl implements SavingButton {
    private boolean saving;
    private DataHandler<String> handler;
    private EditText view;


    @Inject
    public SavingButtonImpl() {
    }

    @Override
    public void setUp(MaterialButton button, EditText editText, DataHandler<String> handler) {
        this.handler = handler;
        this.view = editText;
        String data = handler.get();
        saving = data != null && !data.isEmpty();
        if (saving) {
            editText.setText(data);
            button.setIcon(button.getContext().getDrawable(R.drawable.lock_closed));
        } else
            button.setIcon(button.getContext().getDrawable(R.drawable.lock_open));
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

    @Override
    public void onStop() {
        if(handler != null && view != null) {
            handler.save(saving ? view.getText().toString() : "");
            handler = null;
            view = null;
        }
    }
}
