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

        View.OnClickListener listener = new View.OnClickListener() {
            //method for which actions are taken when a button is clicked
            @Override
            public void onClick(View view) {
                switch(view.getId()){

                    case R.id.exitViewRes:
                        finish();
                        break;
                    case R.id.exit_and_save:
                        //save reservation data to database IF:
                        //user has entered name, phone number, size, date, and time

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
