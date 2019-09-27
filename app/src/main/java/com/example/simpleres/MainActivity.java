package com.example.simpleres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //called when the user hits the send button
    public void sendPasscode(View view) {
        Intent intent = new Intent(this, MainInterface.class);




        //when start activity is called here, the main interface activity begins
        startActivity(intent);

    }
}
