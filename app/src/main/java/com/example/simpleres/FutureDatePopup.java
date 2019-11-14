package com.example.simpleres;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class FutureDatePopup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_date_popup);

        //setting the size of the pop-up window
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.3), (int)(height*0.8));

        //fields
        final TextView displayDate = findViewById(R.id.display_date);
        final ImageButton closePopup = findViewById(R.id.closeFuturePopup);
        //anything for the reservation list - Will be just like it is in Main Interface, just with no waitlist parties

        //PASSED EXTRA containing the date selected in form YYYY-MM-DD
        String dateSelected = getIntent().getStringExtra("DATE_SELECTED");

        //reformat dateSelect to display for example November 14, 2019 for 2019-11-14
        //store values in following variables
        String month = "Month";
        String day = "Day";
        String year = "Year";

        String formattedDate = month + " " + day + ", " + year;

        //displayDate.setText(month); //causing app to crash for some reason?


        //used to end the activity
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.closeFuturePopup){
                    finish();
                }
            }

            //Just like in main interface, listen for when a party is tapped and open menu
                //menu will only have 'Cancel' or 'View' options -> may need to make new menu resource file
        };

        closePopup.setOnClickListener(listener);
    }
}
