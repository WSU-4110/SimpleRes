package com.example.simpleres;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class PastDatePopup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_date_popup);

        //setting the size of the pop-up window
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.5), (int)(height*0.5));

        //fields
        final TextView displayDate = findViewById(R.id.displayPastDate);
        final TextView displayCovers = findViewById(R.id.displayCoversCompleted);
        final ImageButton closePopup = findViewById(R.id.closePastPopup);

        //PASSED EXTRA containing the date selected in form YYYY-MM-DD
        String dateSelected = getIntent().getStringExtra("DATE_SELECTED");

        //use the dateSelected to lookup that date in the DB
            //find cover count info for this date
        String coversCompleted = "123"; //using 123 as a placeholder

        //if there is info for this date
            //change value of coversCompleted
        //else if there is no info
            //coversCompleted = "N/A"

        //Display this information
        displayCovers.setText(coversCompleted);

        //reformat dateSelected to display for example November 14, 2019 for 2019-11-14
        //store values in following variables
        String month = "Month";
        String day = "Day";
        String year = "Year";

        //display formatted date
        String formattedDate = month + " " + day + ", " + year;
        displayDate.setText(formattedDate);

        //used to end the activity
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.closePastPopup){
                    finish();
                }
            }
        };

        closePopup.setOnClickListener(listener);

    }
}
