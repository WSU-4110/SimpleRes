package com.example.simpleres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class SeatWalkInParty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seat_walk_in_party);

        //setting the size of the pop-up window
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.3), (int)(height*0.4));

        //fields
        final ImageButton exitSeatWalkIn = findViewById(R.id.closeSeatWalkIn);
        final Button selectATable = findViewById(R.id.selectATable);
        final EditText walkInSize = findViewById(R.id.enterWalkInSize);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //used for toast
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
                int yOffset = 220;

                switch(view.getId()){
                    case R.id.selectATable:

                        if(walkInSize.getText().toString().equals("")) {
                            Toast toast = Toast.makeText(context, "Please enter party size!", duration);
                            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, yOffset);
                            toast.show();

                        } else {

                            int partySize = Integer.parseInt(walkInSize.getText().toString());
                            //add the party size to the cover count

                            //allow the user to select a table

                            //might need to refresh main interface to show change in cover count
                            finish();
                        }
                        break;

                    case R.id.closeSeatWalkIn:

                        finish();
                        break;

                }
            }
        };

        exitSeatWalkIn.setOnClickListener(listener);
        selectATable.setOnClickListener(listener);
    }
}
