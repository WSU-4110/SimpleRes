package com.example.simpleres;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;

public class ViewReservationPopup extends AppCompatActivity implements AdapterView.OnItemSelectedListener , DatePickerDialog.OnDateSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reservation_popup);

        //setting the size of the pop-up window
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8), (int)(height*0.8));

        //get ID that is passed as an extra
        int entryId = getIntent().getIntExtra("DB_ID", 0);

        //create link to the database
        WaitlistDatabaseHelper wdb = new WaitlistDatabaseHelper(this);

        //grab information for the selected entry
        final WaitlistEntry selectedEntry = wdb.getWaitlistEntry(entryId);

        //button to close the window without saving, exit and save changes
        final ImageButton exitViewRes = findViewById(R.id.exitViewRes);
        final Button exitAndSave = findViewById(R.id.exit_and_save);

        //button to select a new reservation date
        final Button selectDate = findViewById(R.id.select_date);
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerActivity();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        //spinner for reservation times
        Spinner time_spinner = findViewById(R.id.reservation_times);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.reservation_times,
                R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_spinner.setAdapter(adapter);
        time_spinner.setOnItemSelectedListener(this);

        //objects for the datafields
        final EditText nameField = findViewById(R.id.enter_name);
        final EditText sizeField = findViewById(R.id.enter_party_size);
        final EditText phoneField = findViewById(R.id.enter_number);
        //final Spinner reservationTime = findViewById(R.id.reservation_times);

        //populate form with entry information
        nameField.setText(selectedEntry.getName());
        sizeField.setText(Integer.toString(selectedEntry.getNumberOfPeople()));
        phoneField.setText(selectedEntry.getTelephone());
        //not sure how to pull the reservation time with how it is stored
        //not sure how to pull the date with how it is stored
        //can't pull notes for party - needs to be be added to the DB

        View.OnClickListener listener = new View.OnClickListener() {
            //method for which actions are taken when a button is clicked
            @Override
            public void onClick(View view) {
                switch(view.getId()){

                    case R.id.exitViewRes:
                        //essentially a 'cancel' button. User simply wants to view the reservation information
                        //or they wish to discard the changes that were made
                        finish();
                        break;
                    case R.id.exit_and_save:
                        //if user selects this button, then they want to update the reservation to
                        //reflect the changes that they made in this pop-up

                        //here is where the information will be pulled from the form and stored

                        selectedEntry.setName(nameField.getText().toString());
                        selectedEntry.setNumberOfPeople(Integer.parseInt(sizeField.getText().toString()));
                        selectedEntry.setTelephone(phoneField.getText().toString());
                        //don't know how to pull/display/save the time for the reservation
                        //don't know how to pull/display/save the date for the reservation
                        //can't store the reservation notes - needs to be added to the DB
                        finish();
                }
            }
        };

        exitViewRes.setOnClickListener(listener);
        exitAndSave.setOnClickListener(listener);
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

    private TextView mDisplayDate;

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        month = month + 1;

        mDisplayDate = findViewById(R.id.display_date);

        String date = month + "/" + dayOfMonth + "/" + year;
        mDisplayDate.setText(date);

        //not used but can show the full date
        //String selectedDateString = DateFormat.getDateInstance(DateFormat.MEDIUM).format(c.getTime());
        //Toast.makeText(PopupCreateReservation.this, selectedDateString, Toast.LENGTH_LONG).show();
    }

}
