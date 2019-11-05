package com.example.simpleres;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Context;
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

public class PopupCreateReservation extends AppCompatActivity implements AdapterView.OnItemSelectedListener , DatePickerDialog.OnDateSetListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_create_reservation);

        //setting the size of the pop-up window
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8), (int)(height*0.8));

        //button to close the window, create reservation
        final ImageButton exitCreateRes = findViewById(R.id.exitCreateRes);
        final Button createRes = findViewById(R.id.create_res_button);


        //button to select a reservation date
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

                    case R.id.exitCreateRes:
                        finish();
                        break;
                    case R.id.create_res_button:
                        //save reservation data to database IF:
                        //user has entered name, phone number, size, date, and time
                        try{

                            final EditText nameField = findViewById(R.id.enter_name);
                            final EditText sizeField = findViewById(R.id.enter_party_size);
                            if(sizeField.getText().toString() == "" || nameField.getText().toString() == ""){
                                throw new IllegalArgumentException("Cannot have name or party size fields blank!") ;
                            }
                            //get date
                            final TextView reservationDate = findViewById(R.id.display_date);
                            String displayedDate = reservationDate.getText().toString();
                            System.out.println("date stored as: "+displayedDate);

                            String[] dateValues = displayedDate.split("/");
                            int month = Integer.parseInt(dateValues[0]);
                            int day = Integer.parseInt(dateValues[1]);
                            int year = Integer.parseInt(dateValues[2]);

                            //get time
                            final Spinner reservationTime = findViewById(R.id.reservation_times);
                            String time = reservationTime.getSelectedItem().toString().replaceAll("pm","");
                            String [] timeValues = time.split(":");

                            int hour = Integer.parseInt(timeValues[0])+12;//adding 12 because dinner only
                            int minute = Integer.parseInt(timeValues[1]);

                            LocalDate  localDate = LocalDate.of(year,month,day);
                            LocalTime localTime = LocalTime.of(hour,minute,0);
                            LocalDateTime localDateTime = LocalDateTime.of(localDate,localTime);

                            String dateTime = WaitlistEntry.FormatDate(localDateTime);
                            final EditText phoneField = findViewById(R.id.enter_number);

                            String name = nameField.getText().toString();
                            String phone = phoneField.getText().toString();
                            int size = Integer.parseInt(sizeField.getText().toString());

                            System.out.println("Creating entry with parameters (name="+name+",phone="+phone+",size="+size+",dateTime="+dateTime+",localDate="+localDateTime.toString()+")");
                            returnWaitlistEntry(name,phone,size,dateTime,localDateTime);
                        }
                        catch(IllegalArgumentException x){System.out.println(x);
                            break;
                        }
                        catch(Exception e){System.out.println(e);}
                        finish();
                }
            }
        };

        exitCreateRes.setOnClickListener(listener);
        createRes.setOnClickListener(listener);
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
    private WaitlistEntry returnWaitlistEntry(String Name, String Telephone,int NumberOfPeople, String FormattedDateTime, LocalDateTime DateTime){
        WaitlistDatabaseHelper wdb = new WaitlistDatabaseHelper(this);
        WaitlistEntry entry = new WaitlistEntry(Name,Telephone,NumberOfPeople,FormattedDateTime,DateTime,1);
        wdb.addWaitlistEntry(entry);
        entry.createId(wdb);
        System.out.println("Waitlist Entry created in database with id:" + entry.getId());
        return entry;
    }
}

