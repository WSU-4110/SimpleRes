package com.example.simpleres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences settings = getSharedPreferences("PREFS",0);
        password = settings.getString("password", "");

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //if there is no password
                if(password.equals("")){
                    Intent enter = new Intent(getApplicationContext(), CreatePasswordActivity.class);
                    startActivity(enter);
                    finish();
                }else {
                    //if there is a password
                    Intent enter = new Intent(getApplicationContext(), EnterPasswordActivity.class);
                    startActivity(enter);
                    finish();
                }

            }
        },2000);
    }
}
