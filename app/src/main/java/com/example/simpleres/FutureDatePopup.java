package com.example.simpleres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.Future;

import static com.example.simpleres.MainInterface.isfinished;

public class FutureDatePopup extends AppCompatActivity {

    //initialize the listView and the Adapter
    WaitlistDatabaseHelper wdb = new WaitlistDatabaseHelper(this);//these objects act as a link an open link to the database
    ArrayList<WaitlistEntry> FuturePartyArrayList = new ArrayList<>();
    private ListView FutureList;
    private FutureListAdapter FAdaptar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_future_date_popup);

        //setting the size of the pop-up window
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.8), (int)(height*0.8));

        //fields
        final TextView displayDate = findViewById(R.id.displayFutureDate);
        final ImageButton closePopup = findViewById(R.id.closeFuturePopup);
        //anything for the reservation list - Will be just like it is in Main Interface, just with no waitlist parties

        //PASSED EXTRA containing the date selected in form YYYY-MM-DD
        String dateSelected = getIntent().getStringExtra("DATE_SELECTED");

        //reformat dateSelected to display for example November 14, 2019 for 2019-11-14
        //store values in following variables
        String month = getMonthString(dateSelected);
        String day = getDayString(dateSelected);
        String year = getYearString(dateSelected);

        //display the formatted date
        String formattedDate = month + " " + day + ", " + year;
        displayDate.setText(formattedDate);

        //Array of elements in the Future listview
        FutureList = (ListView) findViewById(R.id.FutureList);
        FuturePartyArrayList = wdb.getDateReservationList(dateSelected);

        //adapter for the listview
        FAdaptar = new FutureListAdapter(FutureDatePopup.this, FuturePartyArrayList);
        FutureList.setAdapter(FAdaptar);
        //display a message when empty
        FutureList.setEmptyView(findViewById(R.id.emptyElement));

        FutureList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Create pop-up for reservation
                PopupMenu FutureListActionMenu = new PopupMenu(view.getContext(), view);
                FutureListActionMenu.getMenuInflater().inflate(R.menu.future_action_menu, FutureListActionMenu.getMenu());
                final int pos = position;

                FutureListActionMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        System.out.println("position selected: "+pos);
                        //use dbId to get the WaitlistEntry object wdb.getWaitlistEntry(dbId);
                        int dbId = FuturePartyArrayList.get(pos).getId();
                        WaitlistEntry selectedEntry = wdb.getWaitlistEntry(dbId);
                        System.out.println("entry with contents: " + selectedEntry.contents());
                        final WaitlistEntry selectedEntryTemp = selectedEntry;

                        switch(item.toString()){
                            case "View":
                                //call method / activity to view or edit the reservation party's information
                                //the the selectedEntry must be modified in the PopupViewReservation activity
                                Intent viewRes = new Intent(getApplicationContext(), ViewReservationPopup.class);
                                viewRes.putExtra("DB_ID", dbId); //pass database ID for selected entry to the activity
                                startActivityForResult(viewRes, isfinished);
                                recreate();
                                Intent returnIntent = new Intent();
                                returnIntent.putExtra("result",1);
                                setResult(RESULT_OK,returnIntent);
                                finish();
                                break;
                            case "Cancel":
                                //call method / activity to cancel the reservation party
                                wdb.deleteWaitlistEntry(selectedEntry);
                                recreate();
                                break;
                        }

                        return true;
                    }
                });

                FutureListActionMenu.show();
            }
        });


        //used to end the activity
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.closeFuturePopup){
                    finish();
                }
            }
        };

        closePopup.setOnClickListener(listener);
    }

    public static String getMonthString(String dateSelected){
        //dateSelected is formatted as YYYY-MM-DD 0123 4 56 7 89
        char[] monthInts = new char[2];
        monthInts[0] = dateSelected.charAt(5);
        monthInts[1] = dateSelected.charAt(6);

        int monthInt = Integer.parseInt(new String(monthInts));

        switch(monthInt){
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
        }
        return "ERROR";
    }

    public static String getDayString(String dateSelected){
        //dateSelected is formatted as YYYY-MM-DD 0123 4 56 7 89
        char[] dayInts = new char[2];
        dayInts[0] = dateSelected.charAt(8);
        dayInts[1] = dateSelected.charAt(9);

        return new String(dayInts);
    }

    public static String getYearString(String dateSelected){
        //dateSelected is formatted as YYYY-MM-DD 0123 4 56 7 89
        char[] yearInts = new char[4];
        yearInts[0] = dateSelected.charAt(0);
        yearInts[1] = dateSelected.charAt(1);
        yearInts[2] = dateSelected.charAt(2);
        yearInts[3] = dateSelected.charAt(3);

        return new String(yearInts);
    }

}
