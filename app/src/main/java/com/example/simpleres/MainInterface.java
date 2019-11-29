package com.example.simpleres;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class MainInterface extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, DialogInterface.OnDismissListener {
    WaitlistDatabaseHelper wdb = new WaitlistDatabaseHelper(this);//these objects act as a link an open link to the database
    TableDatabaseHelper tdb = new TableDatabaseHelper(this);
    ArrayList<WaitlistEntry> resPartyArrayList = new ArrayList<>();
    ArrayList<WaitlistEntry> waitPartyArrayList = new ArrayList<>();

    //instantiating database/database tables

    //initializing the listview and adapter for the list items
    private ListView resListView;
    private ListView waitListView;
    private ResPartyAdapter rAdapter;
    private WaitPartyAdapter wAdapter;
    static final int isfinished = 1;
    boolean showPastOrFuture;

    LocalDate today = LocalDate.now();
    int year = today.getYear();
    int month = today.getMonthValue();
    int dayOfMonth = today.getDayOfMonth();
    String currentDay = year + "-" +(month<10?("0"+month):(month)) + "-" + (dayOfMonth<10?("0"+dayOfMonth):(dayOfMonth));
    String dateSelectedPastOrFuture = currentDay;

    public String sms = "Look mom, I can fly";
    public void SendSMS(String phone){
        try{
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("smsto:"));
            i.setType("vnd.android-dir/mms-sms");
            i.putExtra("address", phone);
            i.putExtra("sms_body",sms);
            startActivity(Intent.createChooser(i, "Send sms via:"));
        }
        catch(Exception e){
            Toast.makeText(MainInterface.this, "SMS Failed to Send, Please try again", Toast.LENGTH_SHORT).show();
        }
    }
    public void Text(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        if(checked){
            int dbId = waitPartyArrayList.get(1).getId();
            WaitlistEntry selectedEntry = wdb.getWaitlistEntry(dbId);
            String number = selectedEntry.getTelephone();
            SendSMS(number);
        }


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);
     /*
        setContentView(R.layout.waitlist_list_item);
        final CheckBox checkBox = findViewById(R.id.Here);
        try {
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        System.out.println("checked");// Do your coding
                        checkBox.setChecked(true);
                    } else {
                        System.out.println("unchecked");// Do your coding
                    }
                }
            });
        }
        catch(Exception e){System.out.println(e);}
        setContentView(R.layout.reslist_item_layout);
        try {
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        System.out.println("checked");// Do your coding
                        checkBox.setChecked(true);
                    } else {
                        System.out.println("unchecked");// Do your coding
                    }
                }
            });
        }
        catch(Exception e){System.out.println(e);}
        */

        setContentView(R.layout.activity_main_interface);
        //creating new table objects
        final TableClass[] Tables = new TableClass[11];
        Tables[0] = new TableClass(101, "Empty", "None" );
        Tables[1] = new TableClass(102, "Empty", "None" );
        Tables[2] = new TableClass(103, "Empty", "None" );
        Tables[3] = new TableClass(104, "Empty", "None" );
        Tables[4] = new TableClass(105, "Empty", "None" );
        Tables[5] = new TableClass(106, "Empty", "None" );
        Tables[6] = new TableClass(107, "Empty", "None" );
        Tables[7] = new TableClass(108, "Empty", "None" );
        Tables[8] = new TableClass(109, "Empty", "None" );
        Tables[9] = new TableClass(201, "Empty", "None" );
        Tables[10] = new TableClass(202, "Empty", "None" );

        // get from database tables
        try {
            Tables[0] = tdb.getTableClass(101);
            Tables[1] = tdb.getTableClass(102);
            Tables[2] = tdb.getTableClass(103);
            Tables[3] = tdb.getTableClass(104);
            Tables[4] = tdb.getTableClass(105);
            Tables[5] = tdb.getTableClass(106);
            Tables[6] = tdb.getTableClass(107);
            Tables[7] = tdb.getTableClass(108);
            Tables[8] = tdb.getTableClass(109);
            Tables[9] = tdb.getTableClass(201);
            Tables[10] = tdb.getTableClass(202);
        }
        catch (Exception e){
            System.out.println("error getting table info from database");
            System.out.println("adding tables in nested try/catch block");
            try {
                for (int i=0; i<11; i++) {
                    tdb.addTableClass(Tables[i]);
                }
            }
            catch(Exception x) {
                System.out.println("error adding tables to database");
            }
        }


        //creating new table buttons
        final Button[] buttons = new Button[11];

        buttons[0] = findViewById(R.id.button1);
        buttons[1] = findViewById(R.id.button2);
        buttons[2] = findViewById(R.id.button3);
        buttons[3] = findViewById(R.id.button4);
        buttons[4] = findViewById(R.id.button5);
        buttons[5] = findViewById(R.id.button6);
        buttons[6] = findViewById(R.id.button7);
        buttons[7] = findViewById(R.id.button8);
        buttons[8] = findViewById(R.id.button9);
        buttons[9] = findViewById(R.id.button10);
        buttons[10] = findViewById(R.id.button11);

        //set tables on the interface with saved table status data on start
        for (int i = 0; i<11;i++)
        {
            switch (Tables[i].getTableStatus()) {
                case "Seated":
                    buttons[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.pink));
                    break;
                case "Entree":
                    buttons[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.blue));
                    break;
                case "Dessert":
                    buttons[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.orange));
                    break;
                case "Paid":
                    buttons[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.green));
                    break;
                case "Dirty":
                    buttons[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
                    break;
                case "Empty":
                    buttons[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.purple));
                    break;
            }
        }

        //top bar 'Add party (+)' button
        final ImageButton addPartyButton = findViewById(R.id.addPartyButton);

        //top bar 'Access Calendar' button
        final ImageButton accessCalendar = findViewById(R.id.access_calendar);

        //set date at top to the current day
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int dayOfMonth = today.getDayOfMonth();
        String currentDay = year + "-" +(month<10?("0"+month):(month)) + "-" + (dayOfMonth<10?("0"+dayOfMonth):(dayOfMonth));
        String displayDate = FutureDatePopup.getMonthString(currentDay) + " " + FutureDatePopup.getDayString(currentDay)
                + ", " + FutureDatePopup.getYearString(currentDay);

        final TextView currentDisplayDate = findViewById(R.id.selected_date);
        currentDisplayDate.setText(displayDate);

        final Button cancelSeating = findViewById(R.id.cancel_seating);
        final View cancelSeatingView = findViewById(R.id.cancel_seating);
        cancelSeatingView.setVisibility(View.INVISIBLE);

        View.OnClickListener listener = new View.OnClickListener() {

            //method for which actions are taken when a button is clicked
            @Override
            public void onClick(View view) {

                for (int i = 0; i<11;i++){
                    if (view.getId() == buttons[i].getId()) {
                        final int j = i;
                        PopupMenu dropdownMenu = new PopupMenu(MainInterface.this, buttons[j]);
                        dropdownMenu.getMenuInflater().inflate(R.menu.dropdown_menu, dropdownMenu.getMenu());

                        dropdownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                Tables[j].setTableStatus(menuItem.toString()); // set the value of tableStatus in TableClass to the selected name
                                tdb.updateTableInfo(Tables[j]);

                                //set background of button based on the choice from the dropdown menu
                                switch (menuItem.toString()) {
                                    case "Seated":
                                        buttons[j].setBackgroundDrawable(getResources().getDrawable(R.drawable.pink));
                                        break;
                                    case "Entree":
                                        buttons[j].setBackgroundDrawable(getResources().getDrawable(R.drawable.blue));
                                        break;
                                    case "Dessert":
                                        buttons[j].setBackgroundDrawable(getResources().getDrawable(R.drawable.orange));
                                        break;
                                    case "Paid":
                                        buttons[j].setBackgroundDrawable(getResources().getDrawable(R.drawable.green));
                                        break;
                                    case "Dirty":
                                        buttons[j].setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
                                        break;
                                    case "Empty":
                                        buttons[j].setBackgroundDrawable(getResources().getDrawable(R.drawable.purple));
                                        break;
                                }

                                return true;
                            }
                        });

                        dropdownMenu.show();
                    }
                }

                //Add party (+) button in main interface -> top bar
                if (view.getId() == R.id.addPartyButton) {
                        PopupMenu selectPartyTypeMenu = new PopupMenu(MainInterface.this, addPartyButton);
                        selectPartyTypeMenu.getMenuInflater().inflate(R.menu.party_type_menu, selectPartyTypeMenu.getMenu());

                        selectPartyTypeMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {

                                switch(item.toString()){
                                    case "Reservation":

                                        //open and begin create reservation pop-up activity
                                        Intent pop1 = new Intent(getApplicationContext(), PopupCreateReservation.class);
                                        startActivityForResult(pop1, isfinished);
                                        break;
                                    case "Waitlist":

                                        //open and begin create waitlist party pop-up activity
                                        Intent pop2 = new Intent(getApplicationContext(), PopupCreateWaitlist.class);
                                        startActivityForResult(pop2, isfinished);
                                        break;

                                    case "Walk-In":

                                        //open and begin seat walk-in activity
                                        Intent pop3 = new Intent(getApplicationContext(), SeatWalkInParty.class);
                                        startActivityForResult(pop3, isfinished);
                                        Toast.makeText(MainInterface.this, "Select a table", Toast.LENGTH_LONG).show();
                                        cancelSeatingView.setVisibility(View.VISIBLE);
                                        View.OnClickListener seatingListener = new View.OnClickListener() {

                                            //method for which actions are taken when a button is clicked
                                            @Override
                                            public void onClick(View view) {

                                                for (int i = 0; i<11;i++) {
                                                    if (view.getId() == buttons[i].getId()) {
                                                        Tables[i].setTableName("Walk-in");
                                                        Tables[i].setTableStatus("Seated"); // set the value of tableStatus in TableClass to the selected name
                                                        tdb.updateTableInfo(Tables[i]);
                                                        buttons[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.pink));
                                                        recreate();
                                                    }
                                                }

                                                if (view.getId() == cancelSeating.getId()) {
                                                    cancelSeating.setVisibility(View.INVISIBLE);
                                                    recreate();
                                                }
                                            }
                                        };
                                        //set listeners after the seating option is selected
                                        for (int l = 0; l<11; l++) {
                                            buttons[l].setOnClickListener(seatingListener);
                                        }
                                        cancelSeating.setOnClickListener(seatingListener);

                                        break;
                                }

                                return true;
                            }
                        });

                        selectPartyTypeMenu.show();
                }

                if(view.getId() == R.id.access_calendar){
                    //this is where the date picker will occur and the future or past popup will happen after date selected with the onDismiss method
                    showDatePickerDialog(view);
                    showPastOrFuture = true;
                }
            }
        };

        //set listeners for each table on the interface
        for(int i=0;i<11;i++){
            buttons[i].setOnClickListener(listener);
        }

        //set listeners for top bar layout
        addPartyButton.setOnClickListener(listener);
        accessCalendar.setOnClickListener(listener);

        try {
            //Array of elements in the reservation listview
            resListView = (ListView) findViewById(R.id.reservationListView);
            resPartyArrayList = wdb.getReservationList();

            //adapter for the listview
            rAdapter = new ResPartyAdapter(this, resPartyArrayList);
            resListView.setAdapter(rAdapter);
            //display a message when empty
            resListView.setEmptyView(findViewById(R.id.emptyElement));


            //Array of elements in the waitlist listview
            waitListView = (ListView) findViewById(R.id.waitlistListView);
            waitPartyArrayList = wdb.getWaitlistList();

            //adapter for the listview
            wAdapter = new WaitPartyAdapter(this, waitPartyArrayList);
            waitListView.setAdapter(wAdapter);
            //display a message when empty
            waitListView.setEmptyView(findViewById(R.id.emptyElement2));
            }
        catch(Exception e) { System.out.println(e);}

        resListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Create pop-up for reservation
                PopupMenu resPartyActionMenu = new PopupMenu(view.getContext(), view);
                resPartyActionMenu.getMenuInflater().inflate(R.menu.reservation_action_menu, resPartyActionMenu.getMenu());
                final int pos = position;
                //just need to fix where the menu pops up

                resPartyActionMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        System.out.println("position selected: "+pos);
                        //use dbId to get the WaitlistEntry object wdb.getWaitlistEntry(dbId);
                        int dbId = resPartyArrayList.get(pos).getId();
                        WaitlistEntry selectedEntry = wdb.getWaitlistEntry(dbId);
                        System.out.println("entry with contents: " + selectedEntry.contents());
                        final WaitlistEntry selectedEntryTemp = selectedEntry;

                        switch(item.toString()){
                            case "Seat":
                                //call method / activity to seat the reservation party to a table
                                Toast.makeText(MainInterface.this, "Select a table", Toast.LENGTH_LONG).show();
                                cancelSeatingView.setVisibility(View.VISIBLE);
                                View.OnClickListener seatingListener = new View.OnClickListener() {

                                    //method for which actions are taken when a button is clicked
                                    @Override
                                    public void onClick(View view) {

                                        for (int i = 0; i<11;i++) {
                                            if (view.getId() == buttons[i].getId()) {
                                                Tables[i].setTableName(resPartyArrayList.get(pos).getName());
                                                Tables[i].setTableStatus("Seated"); // set the value of tableStatus in TableClass to the selected name
                                                tdb.updateTableInfo(Tables[i]);
                                                buttons[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.pink));

                                                //countCover returns int of rows deleted;
                                                wdb.countCover(selectedEntryTemp);
                                                recreate();
                                            }
                                        }

                                        if (view.getId() == cancelSeating.getId()) {
                                            cancelSeating.setVisibility(View.INVISIBLE);
                                            recreate();
                                            }

                                    }
                                };
                                //set listeners after the seating option is selected
                                for (int l = 0; l<11; l++) {
                                 buttons[l].setOnClickListener(seatingListener);
                                }
                                cancelSeating.setOnClickListener(seatingListener);

                                break;
                            case "View":
                                //call method / activity to view or edit the reservation party's information
                                //the the selectedEntry must be modified in the PopupViewReservation activity
                                Intent viewRes = new Intent(getApplicationContext(), ViewReservationPopup.class);
                                viewRes.putExtra("DB_ID", dbId); //pass database ID for selected entry to the activity
                                startActivityForResult(viewRes, isfinished);
                                recreate();
                                break;
                            case "Cancel":
                                //call method / activity to cancel the reservation party
                                wdb.deleteWaitlistEntry(selectedEntry);
                                recreate();
                                break;
                            case "Here":
                                //update checkbox to mark the party as here
                                resPartyArrayList.get(pos).setCheckBoxOn();
                                selectedEntry.setCheckBoxOn();
                                wdb.updateWaitlistEntry(selectedEntry);

                                resPartyArrayList = wdb.getReservationList();
                                rAdapter = new ResPartyAdapter(MainInterface.this, resPartyArrayList);
                                resListView.setAdapter(rAdapter);
                                resListView.setEmptyView(findViewById(R.id.emptyElement));
                                break;
                        }

                        return true;
                    }
                });

                resPartyActionMenu.show();
            }
        });

        waitListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int position, long id) {
                //Create pop-up for waitlist
                PopupMenu waitPartyActionMenu = new PopupMenu(view.getContext(), view);
                waitPartyActionMenu.getMenuInflater().inflate(R.menu.waitlist_action_menu, waitPartyActionMenu.getMenu());
                //just need to fix where the menu pops up
                final int pos = position;
                waitPartyActionMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        System.out.println("position selected: "+pos);
                        //use dbId to get the WaitlistEntry object wdb.getWaitlistEntry(dbId);
                        int dbId = waitPartyArrayList.get(pos).getId();
                        WaitlistEntry selectedEntry = wdb.getWaitlistEntry(dbId);
                        System.out.println("entry with contents: " + selectedEntry.contents());
                        final WaitlistEntry selectedEntryTemp = selectedEntry;

                        switch(item.toString()){
                            case "Seat":
                                //call method / activity to seat the waitlist party to a table
                                Toast.makeText(MainInterface.this, "Select a table", Toast.LENGTH_LONG).show();
                                cancelSeatingView.setVisibility(View.VISIBLE);
                                View.OnClickListener seatingListener = new View.OnClickListener() {

                                    //method for which actions are taken when a button is clicked
                                    @Override
                                    public void onClick(View view) {

                                        for (int i = 0; i<11;i++) {
                                            if (view.getId() == buttons[i].getId()) {
                                                Tables[i].setTableName(waitPartyArrayList.get(pos).getName());
                                                Tables[i].setTableStatus("Seated"); // set the value of tableStatus in TableClass to the selected name
                                                tdb.updateTableInfo(Tables[i]);
                                                buttons[i].setBackgroundDrawable(getResources().getDrawable(R.drawable.pink));

                                                //countCover returns int of rows deleted;
                                                wdb.countCover(selectedEntryTemp);
                                                recreate();
                                            }
                                        }

                                        if (view.getId() == cancelSeating.getId()) {
                                            cancelSeating.setVisibility(View.INVISIBLE);
                                            recreate();
                                        }

                                    }
                                };
                                //set listeners after the seating option is selected
                                for (int l = 0; l<11; l++) {
                                    buttons[l].setOnClickListener(seatingListener);
                                }
                                cancelSeating.setOnClickListener(seatingListener);

                                break;
                            case "View":
                                //call method / activity to view or edit the waitlist party's information
                                Intent viewWait = new Intent(getApplicationContext(), ViewWaitlistPopup.class);
                                viewWait.putExtra("DB_ID", dbId); //pass database ID for selected entry to the activity
                                startActivityForResult(viewWait, isfinished);
                                //startActivity(viewWait);
                                recreate();
                                break;
                            case "Cancel":
                                //call method / activity to cancel the waitlist party
                                wdb.deleteWaitlistEntry(selectedEntry);
                                recreate();
                                break;
                            case "Text":
                                //here is where we will text the party at pos
                                //update the checkbox to checked
                                String phoneNum = selectedEntryTemp.getTelephone();
                                String msg = "Your Table is ready, please call if you want to cancel.";
                                try {
                                    SmsManager smsManager = SmsManager.getDefault();
                                    smsManager.sendTextMessage(phoneNum,null,msg,null,null);
                                    Toast.makeText(getApplicationContext(),"Message Sent to "+ selectedEntryTemp.getName(),Toast.LENGTH_LONG).show();
                                } catch (Exception ex) {
                                    Toast.makeText(getApplicationContext(),ex.getMessage().toString(),Toast.LENGTH_LONG).show();
                                    ex.printStackTrace();
                                }

                                waitPartyArrayList.get(pos).setCheckBoxOn();
                                selectedEntry.setCheckBoxOn();
                                wdb.updateWaitlistEntry(selectedEntry);

                                //resPartyArrayList = wdb.getReservationList();
                                //rAdapter = new ResPartyAdapter(MainInterface.this, resPartyArrayList);
                                //resListView.setAdapter(rAdapter);
                                //resListView.setEmptyView(findViewById(R.id.emptyElement));
                                wAdapter = new WaitPartyAdapter(MainInterface.this, waitPartyArrayList);
                                waitListView.setAdapter(wAdapter);
                                waitListView.setEmptyView(findViewById(R.id.emptyElement2));
                                waitPartyArrayList = wdb.getWaitlistList();
                                
                                break;
                        }

                        return true;
                    }
                });

                waitPartyActionMenu.show();

            }
        });
        //initialize the checkbox state x

        //update database checkbox state on click checkbox

        //ensure the value stays when changing activity
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            //for some reason the request code isn't returning RESULT_OK the else statement will make
            // it do it anyways but it makes the original code redundant, but it wont work if i just have the refresh code alone
            if(resultCode == RESULT_OK){
               // String result=data.getStringExtra("result");

                resPartyArrayList = wdb.getReservationList();
                rAdapter = new ResPartyAdapter(MainInterface.this, resPartyArrayList);
                resListView.setAdapter(rAdapter);
                resListView.setEmptyView(findViewById(R.id.emptyElement));
                wAdapter = new WaitPartyAdapter(MainInterface.this, waitPartyArrayList);
                waitListView.setAdapter(wAdapter);
                waitListView.setEmptyView(findViewById(R.id.emptyElement2));
                waitPartyArrayList = wdb.getWaitlistList();
            }
            if (resultCode == RESULT_CANCELED) {
                // if walk-in seating is cancelled this is called and gets rid of the seating option
                recreate();
            }
            else //refresh the list anyways (the waitlist would not update without this even though it was made the same way
            {
                resPartyArrayList = wdb.getReservationList();
                rAdapter = new ResPartyAdapter(MainInterface.this, resPartyArrayList);
                resListView.setAdapter(rAdapter);
                resListView.setEmptyView(findViewById(R.id.emptyElement));
                wAdapter = new WaitPartyAdapter(MainInterface.this, waitPartyArrayList);
                waitListView.setAdapter(wAdapter);
                waitListView.setEmptyView(findViewById(R.id.emptyElement2));
                waitPartyArrayList = wdb.getWaitlistList();
            }
        }
    }
    @Override
    public void onDismiss(final DialogInterface dialog) {
        //once the Datepicker fragment dialog had been dismissed call future or past popup depending on selection
        if (showPastOrFuture) {
            //get current day to compare
            LocalDate today = LocalDate.now();
            int year = today.getYear();
            int month = today.getMonthValue();
            int dayOfMonth = today.getDayOfMonth();
            String currentDay = year + "-" + (month < 10 ? ("0" + month) : (month)) + "-" + (dayOfMonth < 10 ? ("0" + dayOfMonth) : (dayOfMonth));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            LocalDate dateSelectedParsed = LocalDate.parse(dateSelectedPastOrFuture, formatter);
            LocalDate currentDayParsed = LocalDate.parse(currentDay, formatter);

            if (dateSelectedParsed.equals(today)) {
                Toast.makeText(MainInterface.this, "Current Day", Toast.LENGTH_LONG).show();
            } else if (dateSelectedParsed.isAfter(currentDayParsed)) {
                //call future date popup and pass the date selected in form YYYY-MM-DD as a String EXTRA
                Intent futurePop = new Intent(getApplicationContext(), FutureDatePopup.class);
                futurePop.putExtra("DATE_SELECTED", dateSelectedPastOrFuture);
                startActivityForResult(futurePop, isfinished);
                recreate();
            } else if (dateSelectedParsed.isBefore(currentDayParsed)) {
                //call the past date popup and pass the date selected in the form YYYY-MM-DD as a String EXTRA
                Intent pastPop = new Intent(getApplicationContext(), PastDatePopup.class);
                pastPop.putExtra("DATE_SELECTED", dateSelectedPastOrFuture);
                startActivity(pastPop);
                recreate();
            }
            showPastOrFuture = false;
        }
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        month = month + 1;

        String dateSelected = year + "-" +(month<10?("0"+month):(month)) + "-" + (dayOfMonth<10?("0"+dayOfMonth):(dayOfMonth));
        dateSelectedPastOrFuture = dateSelected;

    }
    public void showDatePickerDialog(View v){
        DialogFragment datePicker = new DatePickerActivity();
        datePicker.show(getSupportFragmentManager(), "date picker");
    }
}



