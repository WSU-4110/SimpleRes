package com.example.simpleres;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
    WaitlistDatabaseHelper wdb = new WaitlistDatabaseHelper(this); //these objects act as a link an open link to the database
    TableDatabaseHelper tdb = new TableDatabaseHelper(this);
    CoverDatabaseHelper cdb = new CoverDatabaseHelper(this);
    ArrayList<WaitlistEntry> resPartyArrayList = new ArrayList<>();
    ArrayList<WaitlistEntry> waitPartyArrayList = new ArrayList<>();

    //instantiating database/database tables
    //initializing the ListView and adapter for the list items
    private ListView resListView;
    private ListView waitListView;
    private ResPartyAdapter rAdapter;
    private WaitPartyAdapter wAdapter;

    static final int isFinished = 1;

    private int walkInResult = SeatWalkInParty.getWalkInResult();

    boolean showPastOrFuture;

    LocalDate today = LocalDate.now();
    int year = today.getYear();
    int month = today.getMonthValue();
    int dayOfMonth = today.getDayOfMonth();
    String currentDay = year + "-" +(month<10?("0"+month):(month)) + "-" + (dayOfMonth<10?("0"+dayOfMonth):(dayOfMonth));
    String dateSelectedPastOrFuture = currentDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        //initialize today's cover class
        Cover todaysCover = new Cover(0,LocalDate.now());

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

        try {
            todaysCover = cdb.getCover(LocalDate.now().toString());
        }
        catch(Exception e)
        {
            System.out.println("error getting Cover info from database");
            System.out.println("adding Cover in nested try/catch block");
            try {
                cdb.addCover(todaysCover);
            }
            catch(Exception x) {
                System.out.println("error adding Cover to database");
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
                    buttons[i].setBackgroundResource(R.drawable.pink);
                    break;
                case "Entree":
                    buttons[i].setBackgroundResource(R.drawable.blue);
                    break;
                case "Dessert":
                    buttons[i].setBackgroundResource(R.drawable.orange);
                    break;
                case "Paid":
                    buttons[i].setBackgroundResource(R.drawable.green);
                    break;
                case "Dirty":
                    buttons[i].setBackgroundResource(R.drawable.red);
                    break;
                case "Empty":
                    buttons[i].setBackgroundResource(R.drawable.purple);
                    break;
            }
        }

        //final of today for use in nested functions
        final Cover tempCover = todaysCover;

        //set text view of the completed cover count
        final TextView completedCover = findViewById(R.id.displayCurrentCovers);
        String completedCovers = Integer.toString(tempCover.getDailyCover());
        completedCover.setText(completedCovers);

        //top bar 'Add party (+)' button
        final ImageButton addPartyButton = findViewById(R.id.addPartyButton);

        //top bar 'Access Calendar' button
        final ImageButton accessCalendar = findViewById(R.id.access_calendar);

        //set date at top to the current day
        String displayDate = FutureDatePopup.getMonthString(currentDay) + " " + FutureDatePopup.getDayString(currentDay)
                + ", " + FutureDatePopup.getYearString(currentDay);
        final TextView currentDisplayDate = findViewById(R.id.selected_date);
        currentDisplayDate.setText(displayDate);

        //for seating walk-in parties
        final Button cancelSeating = this.findViewById(R.id.cancel_seating);
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
                                        buttons[j].setBackgroundResource(R.drawable.pink);
                                        break;
                                    case "Entree":
                                        buttons[j].setBackgroundResource(R.drawable.blue);
                                        break;
                                    case "Dessert":
                                        buttons[j].setBackgroundResource(R.drawable.orange);
                                        break;
                                    case "Paid":
                                        buttons[j].setBackgroundResource(R.drawable.green);
                                        break;
                                    case "Dirty":
                                        buttons[j].setBackgroundResource(R.drawable.red);
                                        break;
                                    case "Empty":
                                        buttons[j].setBackgroundResource(R.drawable.purple);
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
                                        Intent pop1 = new Intent(getApplicationContext(), PopupCreateReservation.class);
                                        startActivityForResult(pop1, isFinished);
                                        break;
                                    case "Waitlist":
                                        Intent pop2 = new Intent(getApplicationContext(), PopupCreateWaitlist.class);
                                        startActivityForResult(pop2, isFinished);
                                        break;
                                    case "Walk-In":
                                        Intent pop3 = new Intent(getApplicationContext(), SeatWalkInParty.class);

                                        startActivityForResult(pop3, isFinished);
                                        Toast.makeText(MainInterface.this, "Select a table", Toast.LENGTH_SHORT).show();
                                        cancelSeatingView.setVisibility(View.VISIBLE);
                                        View.OnClickListener seatingListener = new View.OnClickListener() {

                                            @Override
                                            public void onClick(View view) {

                                                for (int i = 0; i<11;i++) {
                                                    if (view.getId() == buttons[i].getId()) {
                                                        Tables[i].setTableName("Walk-in");
                                                        Tables[i].setTableStatus("Seated"); // set the value of tableStatus in TableClass to the selected name
                                                        tdb.updateTableInfo(Tables[i]);

                                                        buttons[i].setBackgroundResource(R.drawable.pink);
                                                        walkInResult = SeatWalkInParty.getWalkInResult();
                                                        tempCover.addToCover(walkInResult);

                                                        cdb.updateCover(tempCover);
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
            //Array of elements in the reservation ListView
            resListView = findViewById(R.id.reservationListView);
            resPartyArrayList = wdb.getDateReservationList(currentDay);

            //adapter for the ListView
            rAdapter = new ResPartyAdapter(this, resPartyArrayList);
            resListView.setAdapter(rAdapter);

            //display a message when empty
            resListView.setEmptyView(findViewById(R.id.emptyElement));

            //Array of elements in the waitlist ListView
            waitListView = findViewById(R.id.waitlistListView);
            waitPartyArrayList = wdb.getWaitlistList();

            //adapter for the ListView
            wAdapter = new WaitPartyAdapter(this, waitPartyArrayList);
            waitListView.setAdapter(wAdapter);

            //display a message when empty
            waitListView.setEmptyView(findViewById(R.id.emptyElement2));
            }
        catch(Exception e) {
            System.out.println("Error populating lists");
        }

        resListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final PopupMenu resPartyActionMenu = new PopupMenu(view.getContext(), view);
                resPartyActionMenu.getMenuInflater().inflate(R.menu.reservation_action_menu, resPartyActionMenu.getMenu());
                final int pos = position;

                resPartyActionMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        System.out.println("position selected: "+ pos);
                        int dbId = resPartyArrayList.get(pos).getId();
                        WaitlistEntry selectedEntry = wdb.getWaitlistEntry(dbId);
                        System.out.println("entry with contents: " + selectedEntry.contents());
                        final WaitlistEntry selectedEntryTemp = selectedEntry;

                        switch(item.toString()){
                            case "Seat":
                                Toast.makeText(MainInterface.this, "Select a table", Toast.LENGTH_SHORT).show();
                                cancelSeatingView.setVisibility(View.VISIBLE);
                                View.OnClickListener seatingListener = new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        for (int i = 0; i<11;i++) {
                                            if (view.getId() == buttons[i].getId()) {
                                                Tables[i].setTableName(resPartyArrayList.get(pos).getName());
                                                Tables[i].setTableStatus("Seated"); // set the value of tableStatus in TableClass to the selected name
                                                tdb.updateTableInfo(Tables[i]);
                                                buttons[i].setBackgroundResource(R.drawable.pink);

                                                //countCover returns int of party size;
                                                tempCover.addToCover(wdb.countCover(selectedEntryTemp));
                                                cdb.updateCover(tempCover);
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
                                Intent viewRes = new Intent(getApplicationContext(), ViewReservationPopup.class);
                                viewRes.putExtra("DB_ID", dbId); //pass database ID for selected entry to the activity
                                startActivityForResult(viewRes, isFinished);
                                recreate();
                                break;
                            case "Cancel":
                                wdb.deleteWaitlistEntry(selectedEntry);
                                recreate();
                                break;
                            case "Here":
                                int checkStatus = resPartyArrayList.get(pos).getCheckBox();

                                if(checkStatus == 0) {
                                    resPartyArrayList.get(pos).setCheckBoxOn();
                                    selectedEntry.setCheckBoxOn();
                                    wdb.updateWaitlistEntry(selectedEntry);
                                } else {
                                    resPartyArrayList.get(pos).setCheckBoxOff();
                                    selectedEntry.setCheckBoxOff();
                                    wdb.updateWaitlistEntry(selectedEntry);
                                }
                                resPartyArrayList = wdb.getDateReservationList(LocalDate.now().toString());
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
                PopupMenu waitPartyActionMenu = new PopupMenu(view.getContext(), view);
                waitPartyActionMenu.getMenuInflater().inflate(R.menu.waitlist_action_menu, waitPartyActionMenu.getMenu());
                final int pos = position;
                waitPartyActionMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        System.out.println("position selected: "+pos);
                        int dbId = waitPartyArrayList.get(pos).getId();
                        WaitlistEntry selectedEntry = wdb.getWaitlistEntry(dbId);
                        System.out.println("entry with contents: " + selectedEntry.contents());
                        final WaitlistEntry selectedEntryTemp = selectedEntry;

                        switch(item.toString()){
                            case "Seat":
                                Toast.makeText(MainInterface.this, "Select a table", Toast.LENGTH_SHORT).show();
                                cancelSeatingView.setVisibility(View.VISIBLE);
                                View.OnClickListener seatingListener = new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        for (int i = 0; i<11;i++) {
                                            if (view.getId() == buttons[i].getId()) {
                                                Tables[i].setTableName(waitPartyArrayList.get(pos).getName());
                                                Tables[i].setTableStatus("Seated");
                                                tdb.updateTableInfo(Tables[i]);
                                                buttons[i].setBackgroundResource(R.drawable.pink);

                                                //countCover returns int of party size;
                                                tempCover.addToCover(wdb.countCover(selectedEntryTemp));
                                                cdb.updateCover(tempCover);
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
                                Intent viewWait = new Intent(getApplicationContext(), ViewWaitlistPopup.class);
                                viewWait.putExtra("DB_ID", dbId);
                                startActivityForResult(viewWait, isFinished);
                                recreate();
                                break;
                            case "Cancel":
                                wdb.deleteWaitlistEntry(selectedEntry);
                                recreate();
                                break;
                            case "Text":
                                String phoneNum = selectedEntryTemp.getTelephone();
                                String msg = "Your table is ready, please call the restaurant if you would like to cancel.";
                                try {
                                    SmsManager smsManager = SmsManager.getDefault();
                                    smsManager.sendTextMessage(phoneNum,null,msg,null,null);
                                    Toast.makeText(getApplicationContext(),"Message Sent to "+ selectedEntryTemp.getName(),Toast.LENGTH_SHORT).show();
                                } catch (Exception ex) {
                                    Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
                                    ex.printStackTrace();
                                }
                                waitPartyArrayList.get(pos).setCheckBoxOn();
                                selectedEntry.setCheckBoxOn();
                                wdb.updateWaitlistEntry(selectedEntry);
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
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
               // String result=data.getStringExtra("result");

                resPartyArrayList = wdb.getDateReservationList(currentDay);
                rAdapter = new ResPartyAdapter(MainInterface.this, resPartyArrayList);
                resListView.setAdapter(rAdapter);
                resListView.setEmptyView(findViewById(R.id.emptyElement));
                wAdapter = new WaitPartyAdapter(MainInterface.this, waitPartyArrayList);
                waitListView.setAdapter(wAdapter);
                waitListView.setEmptyView(findViewById(R.id.emptyElement2));
                waitPartyArrayList = wdb.getWaitlistList();
            }
            if (resultCode == RESULT_CANCELED) {
                recreate();
            } else {
                resPartyArrayList = wdb.getDateReservationList(currentDay);
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
        //once the DatePicker fragment dialog had been dismissed call future or past popup depending on selection
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
                Toast.makeText(MainInterface.this, "Current Day", Toast.LENGTH_SHORT).show();
            } else if (dateSelectedParsed.isAfter(currentDayParsed)) {
                //call future date popup and pass the date selected in form YYYY-MM-DD as a String EXTRA
                Intent futurePop = new Intent(getApplicationContext(), FutureDatePopup.class);
                futurePop.putExtra("DATE_SELECTED", dateSelectedPastOrFuture);
                startActivityForResult(futurePop, isFinished);
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

        dateSelectedPastOrFuture = year + "-" +(month<10?("0"+month):(month)) + "-" + (dayOfMonth<10?("0"+dayOfMonth):(dayOfMonth));
    }

    public void showDatePickerDialog(View v){
        DialogFragment datePicker = new DatePickerActivity();
        datePicker.show(getSupportFragmentManager(), "date picker");
    }
}