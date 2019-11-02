package com.example.simpleres;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.time.LocalDateTime;

public class ViewWaitlistPopup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_waitlist_popup);


        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8), (int)(height*0.8));

        //buttons to close window, add party to waitlist
        final ImageButton exitCreateRes = findViewById(R.id.exitViewWait);
        final Button addToWaitlist = findViewById(R.id.exit_and_save);

        View.OnClickListener listener = new View.OnClickListener() {

            //method for which actions are taken when a button is clicked
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.exitViewWait:
                        finish();
                        break;
                    case R.id.exit_and_save:
                        //add waitlist info to database IF:
                        //user has entered name, phone number, size, quote time

                        finish();

                }
            }
        };

        exitCreateRes.setOnClickListener(listener);
        addToWaitlist.setOnClickListener(listener);
    }


}
