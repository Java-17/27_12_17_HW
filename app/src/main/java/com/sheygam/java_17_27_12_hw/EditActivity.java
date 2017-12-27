package com.sheygam.java_17_27_12_hw;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText inputName, inputEmail, inputPhone;
    private Button okBtn;
    private String action;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent intent = getIntent();
        action = intent.getAction();
        String data = intent.getStringExtra("DATA");
        inputName = findViewById(R.id.input_name);
        inputEmail = findViewById(R.id.input_email);
        inputPhone = findViewById(R.id.input_phone);
        okBtn = findViewById(R.id.ok_btn);
        okBtn.setOnClickListener(this);
        showNeedsEdit(data);
    }

    private void showNeedsEdit(String data) {
        switch (action){
            case "telran.edit.name":
                inputName.setVisibility(View.VISIBLE);
                inputName.setText(data);
                break;
            case "telran.edit.email":
                inputEmail.setVisibility(View.VISIBLE);
                inputEmail.setText(data);
                break;
            case "telran.edit.phone":
                inputPhone.setVisibility(View.VISIBLE);
                inputPhone.setText(data);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ok_btn){
            String res = "";
            switch (action){
                case "telran.edit.name":
                    res = inputName.getText().toString();
                    break;
                case "telran.edit.email":
                    res = inputEmail.getText().toString();
                    break;
                case "telran.edit.phone":
                    res = inputPhone.getText().toString();
                    break;
            }
            Intent intent = new Intent();
            intent.putExtra("DATA",res);
            setResult(RESULT_OK,intent);
            finish();
        }
    }
}
