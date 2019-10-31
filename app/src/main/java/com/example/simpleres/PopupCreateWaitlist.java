package com.example.simpleres;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalDateTime;

public class PopupCreateWaitlist extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

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
                switch(view.getId()){
                    case R.id.exitCreateWait:
                        finish();
                        break;
                    case R.id.add_to_waitlist_button:
                        //add waitlist info to database IF:
                        //user has entered name, phone number, size, quote time
                        try{
                            String date = WaitlistEntry.FormatDate(LocalDateTime.now());
                            final EditText nameField = findViewById(R.id.enter_name);
                            final EditText phoneField = findViewById(R.id.enter_number);
                            final EditText sizeField = findViewById(R.id.enter_party_size);
                            final Spinner quotedField = findViewById(R.id.wait_times);

                            String name = nameField.getText().toString();
                            String phone = phoneField.getText().toString();
                            int size = Integer.parseInt(sizeField.getText().toString());
                            long quoted = Long.parseLong(quotedField.getSelectedItem().toString().replaceAll("min",""));

                            System.out.println("Creating entry with parameters (name="+name+",phone="+phone+",size="+size+",date="+date+",quoted="+quoted+")");
                            returnWaitlistEntry(name,phone,size,date,quoted);
                        }
                        catch(Exception e){System.out.println(e);}
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

    private WaitlistEntry returnWaitlistEntry(String Name, String Telephone,int NumberOfPeople, String FormattedDateTime, long QuotedTime){
        WaitlistDatabaseHelper wdb = new WaitlistDatabaseHelper(this);
        WaitlistEntry entry = new WaitlistEntry(Name,Telephone,NumberOfPeople,FormattedDateTime,QuotedTime);
        wdb.addWaitlistEntry(entry);
        entry.createId(wdb);
        System.out.println("Waitlist Entry created in database with id:" + entry.getId());
        return entry;
    }
}
