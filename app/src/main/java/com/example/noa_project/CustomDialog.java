package com.example.noa_project;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

public class CustomDialog extends Dialog implements View.OnClickListener {
    Button btnYes, btnNo;
    Context context;

    public CustomDialog(@NonNull Context context) {
        super(context);

        setContentView(R.layout.custom_dialog);
        this.context = context;

        btnYes = findViewById(R.id.btnYes);
        btnNo = findViewById(R.id.btnNo);
        btnYes.setOnClickListener(this);
        btnNo.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(btnYes == view)
        {
            dismiss(); // eliminate the dialog
            ((MainActivity)context).recreate();

        }

        if(btnNo == view)
        {
            ((MainActivity)context).finish();
        }
    }
}
