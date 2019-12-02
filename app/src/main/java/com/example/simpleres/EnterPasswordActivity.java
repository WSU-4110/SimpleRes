package com.example.simpleres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EnterPasswordActivity extends AppCompatActivity {
    private EditText entpwd;
    private Button welbtn;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_password);

        entpwd = findViewById(R.id.Enterpwd);
        welbtn = findViewById(R.id.wlcbtn);
        SharedPreferences settings = getSharedPreferences("PREFS",0);
        password = settings.getString("password", "");

        welbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = entpwd.getText().toString();

                if(pwd.equals(password)){
                    Intent enter = new Intent(EnterPasswordActivity.this, MainInterface.class);
                    startActivity(enter);
                    finish();
                }else{
                    Toast.makeText(EnterPasswordActivity.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
