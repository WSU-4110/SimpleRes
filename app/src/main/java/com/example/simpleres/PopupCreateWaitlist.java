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
                        TextView guest_name = (TextView) findViewById(R.id.guest_name);
                        String name = guest_name.toString();
                        TextView guest_phone = (TextView) findViewById(R.id.guest_phone_number);
                        String phone = guest_phone.toString();
                        TextView party_size = (TextView) findViewById(R.id.party_size);
                        //int size = Integer.parseInt(party_size.toString());
                        //TextView timeofWaitlist = (TextView) findViewById(R.id.timeofWaitlist;
                        //String date = timeofWaitlist.toString();
                        String date = WaitlistEntry.FormatDate(LocalDateTime.now());
                        TextView wait_times = (TextView) findViewById(R.id.wait_times);
                        long quoted = Long.parseLong(wait_times.toString().replaceAll("min",""));
                        //TextView waitlist_notes = (TextView) findViewById(R.id.waitlist_notes);
                        //String notes = waitlist_notes.toString();
                        try{
                            System.out.println("Creating entry with parameters (name="+name+",phone="+phone+",size="+/*size*/2+",date="+date+",quoted="+quoted+")");
                            returnWaitlistEntry(name,phone,/*size*/2,date,quoted);
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

    public WaitlistEntry returnWaitlistEntry(String Name, String Telephone,int NumberOfPeople, String FormattedDateTime, long QuotedTime){



        return (new WaitlistEntry(Name,Telephone,NumberOfPeople,FormattedDateTime,QuotedTime));
    }
}
