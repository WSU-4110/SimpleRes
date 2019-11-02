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

public class ViewWaitlistPopup extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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

        //spinner for waitlist times
        Spinner time_spinner = findViewById(R.id.wait_times);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.quote_times,
                R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_spinner.setAdapter(adapter);
        time_spinner.setOnItemSelectedListener(this);

        //populate form with entry information
        /* TODO: take the information for the specific entry (passed as an extra??) and use
             it to populate the form as if the user entered it
         */

        View.OnClickListener listener = new View.OnClickListener() {

            //method for which actions are taken when a button is clicked
            @Override
            public void onClick(View view) {
                switch(view.getId()){
                    case R.id.exitViewWait:
                        //essentially a 'cancel' button. User simply wants to view the waitlist party's information
                        //or they wish to discard the changes that were made
                        finish();
                        break;
                    case R.id.exit_and_save:
                        //if user selects this button, then they want to update the waitlist party to
                        //reflect the changes that they made in this pop-up

                        //here is where the information will be pulled from the form and stored

                        /* TODO: pull the information from the form and overwrite the existing data entry
                            with the updated information
                         */

                        finish();

                }
            }
        };

        exitCreateRes.setOnClickListener(listener);
        addToWaitlist.setOnClickListener(listener);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //uncomment following if we want a toast
        //String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
