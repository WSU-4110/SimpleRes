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

        //setting the pop-up window dimensions
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8), (int)(height*0.8));

        //get ID that is passed as an extra
        int entryId = getIntent().getIntExtra("DB_ID", 0);

        //create link to the database
        final WaitlistDatabaseHelper wdb = new WaitlistDatabaseHelper(this);

        //grab information for the selected entry
        final WaitlistEntry selectedEntry = wdb.getWaitlistEntry(entryId);

        //spinner for waitlist times
        Spinner time_spinner = findViewById(R.id.wait_times);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.quote_times,
                R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_spinner.setAdapter(adapter);
        //time_spinner.setSelection(//call method to calculate position);
        time_spinner.setOnItemSelectedListener(this);


        //buttons to close window, add party to waitlist
        final ImageButton exitCreateRes = findViewById(R.id.exitViewWait);
        final Button addToWaitlist = findViewById(R.id.exit_and_save);

        //objects for the datafields
        final EditText nameField = findViewById(R.id.enter_name);
        final EditText sizeField = findViewById(R.id.enter_party_size);
        final EditText phoneField = findViewById(R.id.enter_number);
        //final Spinner quotedField = findViewById(R.id.wait_times);

        //populate form with entry information
        nameField.setText(selectedEntry.getName());
        sizeField.setText(Integer.toString(selectedEntry.getNumberOfPeople()));
        phoneField.setText(selectedEntry.getTelephone());
        //can't pull the time party was quoted - needs to be added to the DB
        //can't pull notes for party - needs to be be added to the DB


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
                        selectedEntry.setName(nameField.getText().toString());
                        selectedEntry.setNumberOfPeople(Integer.parseInt(sizeField.getText().toString()));
                        selectedEntry.setTelephone(phoneField.getText().toString());
                        wdb.updateWaitlistEntry(selectedEntry);
                        //can't pull/save the time party was quoted - needs to be added to DB
                        //can't pull/save the notes for party - needs to be added to DB

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
