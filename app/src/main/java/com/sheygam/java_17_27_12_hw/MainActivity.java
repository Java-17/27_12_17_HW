package com.sheygam.java_17_27_12_hw;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView nameTxt, emailTxt, phoneTxt;
    private Button setNameBtn, setEmailBtn, setPhoneBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameTxt = findViewById(R.id.name_txt);
        emailTxt = findViewById(R.id.email_txt);
        phoneTxt = findViewById(R.id.phone_txt);
        setNameBtn = findViewById(R.id.set_name_btn);
        setEmailBtn = findViewById(R.id.set_email_btn);
        setPhoneBtn = findViewById(R.id.set_phone_btn);
        setNameBtn.setOnClickListener(this);
        setEmailBtn.setOnClickListener(this);
        setPhoneBtn.setOnClickListener(this);
        load();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.set_name_btn){
            Intent intent = new Intent("telran.edit.name");
            String data = nameTxt.getText().toString();
            intent.putExtra("DATA",data);
            startActivityForResult(intent,1);
        }else if(v.getId() == R.id.set_email_btn){
            Intent intent = new Intent("telran.edit.email");
            String data = emailTxt.getText().toString();
            intent.putExtra("DATA",data);
            startActivityForResult(intent,2);
        }else if(v.getId() == R.id.set_phone_btn){
            Intent intent = new Intent("telran.edit.phone");
            String data = phoneTxt.getText().toString();
            intent.putExtra("DATA",data);
            startActivityForResult(intent,3);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case 1:
                    nameTxt.setText(data.getStringExtra("DATA"));
                    break;
                case 2:
                    emailTxt.setText(data.getStringExtra("DATA"));
                    break;
                case 3:
                    phoneTxt.setText(data.getStringExtra("DATA"));
                    break;
            }
        }
    }

    private void save(){
        SharedPreferences sharedPreferences = getSharedPreferences("DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("NAME",nameTxt.getText().toString());
        editor.putString("EMAIL",emailTxt.getText().toString());
        editor.putString("PHONE",phoneTxt.getText().toString());
        editor.commit();
    }

    private void load(){
        SharedPreferences sharedPreferences = getSharedPreferences("DATA",MODE_PRIVATE);
        nameTxt.setText(sharedPreferences.getString("NAME",""));
        emailTxt.setText(sharedPreferences.getString("EMAIL",""));
        phoneTxt.setText(sharedPreferences.getString("PHONE",""));
    }

    @Override
    protected void onStop() {
        super.onStop();
        save();
    }
}
