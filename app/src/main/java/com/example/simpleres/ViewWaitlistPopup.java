package com.example.simpleres;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ViewWaitlistPopup extends MainInterface implements AdapterView.OnItemSelectedListener {

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

        int entryId = getIntent().getIntExtra("DB_ID", 0);
        final WaitlistDatabaseHelper wdb = new WaitlistDatabaseHelper(this);
        final WaitlistEntry selectedEntry = wdb.getWaitlistEntry(entryId);

        //spinner for waitlist times
        final Spinner time_spinner = findViewById(R.id.wait_times);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.quote_times,
                R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_spinner.setAdapter(adapter);
        time_spinner.setOnItemSelectedListener(this);

        //buttons to close window, add party to waitlist
        final ImageButton exitCreateRes = findViewById(R.id.exitViewWait);
        final Button addToWaitlist = findViewById(R.id.exit_and_save);

        //objects for the data fields
        final EditText nameField = findViewById(R.id.enter_name);
        final EditText sizeField = findViewById(R.id.enter_party_size);
        final EditText phoneField = findViewById(R.id.enter_number);
        final EditText notesField = findViewById(R.id.enter_wait_notes);

        //populate form with entry information
        nameField.setText(selectedEntry.getName());
        String numberOfPeople = Integer.toString(selectedEntry.getNumberOfPeople());
        sizeField.setText(numberOfPeople);
        phoneField.setText(selectedEntry.getTelephone());
        notesField.setText(selectedEntry.getNotes());

        View.OnClickListener listener = new View.OnClickListener() {

            //method for which actions are taken when a button is clicked
            @Override
            public void onClick(View view) {
            //used for input validation toasts
            boolean showDateToast = true;
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;
            int yOffset = 80;

            switch(view.getId()){
                case R.id.exitViewWait:
                    finish();
                    break;
                case R.id.exit_and_save:
                    try {
                        if (nameField.getText().toString().equals("")) {
                            Toast toast = Toast.makeText(context, "Please enter party name!", duration);
                            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, yOffset);
                            toast.show();

                            showDateToast = false;
                            throw new IllegalArgumentException("Cannot have name fields blank!");
                        }  else if (phoneField.getText().toString().length() != 10){
                            Toast toast = Toast.makeText(context, "Please enter valid phone number!", duration);
                            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, yOffset);
                            toast.show();

                            showDateToast = false;
                            throw new IllegalArgumentException("Cannot have incomplete phone number!");
                        } else if(sizeField.getText().toString().equals("")){
                            Toast toast = Toast.makeText(context, "Please enter party size!", duration);
                            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, yOffset);
                            toast.show();

                            showDateToast = false;
                            throw new IllegalArgumentException("Cannot have party size fields blank!");
                        }
                        //get date
                        String currentDate = selectedEntry.parseDate();
                        System.out.println("date stored as: " + currentDate);
                        String[] dateValues = currentDate.split("/");
                        int month = Integer.parseInt(dateValues[0]);
                        int day = Integer.parseInt(dateValues[1]);
                        int year = Integer.parseInt(dateValues[2]);

                        //get time
                        boolean pmFlag = false;
                        if (selectedEntry.parseTime().contains("pm"))
                            pmFlag = true;
                        String time = selectedEntry.parseTime().replaceAll("am", "").replaceAll("pm", "");
                        String[] timeValues = time.split(":");
                        int hour = Integer.parseInt(timeValues[0]);
                        if (pmFlag)
                            hour += 12;
                        int minute = Integer.parseInt(timeValues[1]);
                        System.out.print("year month and day: " + year + ", " + month + ", " + day);
                        LocalDate localDate = LocalDate.of(year, month, day);
                        LocalTime localTime = LocalTime.of(hour, minute, 0);
                        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

                        long quoted = Long.parseLong(time_spinner.getSelectedItem().toString().replaceAll("min", ""));

                        selectedEntry.setFormattedDateTime(WaitlistEntry.formatDate(localDateTime.plusMinutes(quoted)));// this will always add at least 5 minutes when a change is made needs tweaking in the menu
                        selectedEntry.setName(nameField.getText().toString());
                        selectedEntry.setNumberOfPeople(Integer.parseInt(sizeField.getText().toString()));
                        selectedEntry.setTelephone(phoneField.getText().toString());
                        selectedEntry.setNotes(notesField.getText().toString());
                        wdb.updateWaitlistEntry(selectedEntry);
                        } catch(IllegalArgumentException x){

                            if(showDateToast) {
                                Toast toast = Toast.makeText(context, "Please select a date!", duration);
                                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, yOffset);
                                toast.show();
                            }
                            System.out.println("Illegal Argument encountered.");
                            break;
                        } catch(Exception e){
                            System.out.println("Exception encountered.");
                        }

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result",1);
                    setResult(RESULT_OK,returnIntent);
                    finish();
                }
            }
        };
        exitCreateRes.setOnClickListener(listener);
        addToWaitlist.setOnClickListener(listener);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
