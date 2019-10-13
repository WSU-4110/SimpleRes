package com.example.simpleres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreatePasswordActivity extends AppCompatActivity {

    EditText passwordagn, password;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_password);

        password = findViewById(R.id.password);
        passwordagn = findViewById(R.id.passwordagn);
        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pwd = password.getText().toString();
                String pwdagn = passwordagn.getText().toString();
                if (pwdagn.equals("") || pwd.equals("")) {
                    //if there is no password
                    Toast.makeText(CreatePasswordActivity.this, "There is no password!", Toast.LENGTH_SHORT).show();
                } else if (pwd.equals(pwdagn)) {
                    //save the password
                    SharedPreferences settings = getSharedPreferences("PREFS", 0);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("password", pwd);
                    editor.apply();

                    //enter the app
                    Intent enter = new Intent(CreatePasswordActivity.this, MainInterface.class);
                    startActivity(enter);
                    finish();
                } else {
                    //the passwords doesn't match
                    Toast.makeText(CreatePasswordActivity.this, "Passwords don't match!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}