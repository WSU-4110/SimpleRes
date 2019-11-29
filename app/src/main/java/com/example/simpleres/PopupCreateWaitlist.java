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
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;

public class PopupCreateWaitlist extends MainInterface implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_create_waitlist);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*0.8), (int)(height*0.8));

        //buttons to close window, add party to waitlist
        final ImageButton exitCreateRes = findViewById(R.id.exitCreateWait);
        final Button addToWaitlist = findViewById(R.id.add_to_waitlist_button);

        //spinner for waitlist times
        Spinner time_spinner = findViewById(R.id.wait_times);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.quote_times,
                R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        time_spinner.setAdapter(adapter);
        time_spinner.setOnItemSelectedListener(this);

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
                    case R.id.exitCreateWait:
                        finish();
                        break;
                    case R.id.add_to_waitlist_button:
                        try{
                            final EditText nameField = findViewById(R.id.enter_name);
                            final EditText sizeField = findViewById(R.id.enter_party_size);
                            final EditText phoneField = findViewById(R.id.enter_number);

                            //input validation
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

                            String date = WaitlistEntry.formatDate(LocalDateTime.now());
                            final Spinner quotedField = findViewById(R.id.wait_times);

                            final EditText notesField = findViewById(R.id.enter_wait_notes);

                            String name = nameField.getText().toString();
                            String phone = phoneField.getText().toString();
                            int size = Integer.parseInt(sizeField.getText().toString());
                            long quoted = Long.parseLong(quotedField.getSelectedItem().toString().replaceAll("min",""));

                            String notes = notesField.getText().toString();

                            System.out.println("Creating entry with parameters (name="+name+",phone="+phone+",size="+size+",date="+date+",quoted="+quoted+")");
                            returnWaitlistEntry(name,phone,size,date,quoted,notes);
                        }
                        catch(IllegalArgumentException x){

                            if(showDateToast) {
                                Toast toast = Toast.makeText(context, "Please select a date!", duration);
                                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, yOffset);
                                toast.show();
                            }
                            System.out.println("IllegalArgument encountered");
                            break;
                        }
                        catch(Exception e){
                            System.out.println("Exception encountered");
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

    private void returnWaitlistEntry(String Name, String Telephone, int NumberOfPeople, String FormattedDateTime, long QuotedTime, String ReservationNotes){
        WaitlistDatabaseHelper wdb = new WaitlistDatabaseHelper(this);
        WaitlistEntry entry = new WaitlistEntry(Name,Telephone,NumberOfPeople, QuotedTime,ReservationNotes);
        wdb.addWaitlistEntry(entry);
        entry.createId(wdb);
        System.out.println("Waitlist Entry created in database with id:" + entry.getId());
    }
}