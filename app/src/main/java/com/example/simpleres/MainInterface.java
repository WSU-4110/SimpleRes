package com.example.simpleres;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ListView;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.List;

import static android.R.id.empty;


public class MainInterface extends AppCompatActivity {
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
                tdb.addTableClass(Tables[0]);
                tdb.addTableClass(Tables[1]);
                tdb.addTableClass(Tables[2]);
                tdb.addTableClass(Tables[3]);
                tdb.addTableClass(Tables[4]);
                tdb.addTableClass(Tables[5]);
                tdb.addTableClass(Tables[6]);
                tdb.addTableClass(Tables[7]);
                tdb.addTableClass(Tables[8]);
                tdb.addTableClass(Tables[9]);
                tdb.addTableClass(Tables[10]);
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
        for (int i = 0; i<10;i++)
        {
            switch (Tables[i].getTableStatus()) {
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

        //top bar 'refresh' list button
        final ImageButton refreshList = findViewById(R.id.refreshList);

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
                                        startActivity(pop1);
                                        break;
                                    case "Waitlist":

                                        //open and begin create waitlist party pop-up activity
                                        Intent pop2 = new Intent(getApplicationContext(), PopupCreateWaitlist.class);
                                        startActivity(pop2);
                                        break;
                                }

                                return true;
                            }
                        });

                        selectPartyTypeMenu.show();
                }

                if(view.getId() == R.id.refreshList){
                    resPartyArrayList = wdb.getReservationList();
                    rAdapter = new ResPartyAdapter(MainInterface.this, resPartyArrayList);
                    resListView.setAdapter(rAdapter);
                    resListView.setEmptyView(findViewById(R.id.emptyElement));
                    wAdapter = new WaitPartyAdapter(MainInterface.this, waitPartyArrayList);
                    waitListView.setAdapter(wAdapter);
                    waitListView.setEmptyView(findViewById(R.id.emptyElement));
                    waitPartyArrayList = wdb.getWaitlistList();
                }

            }
        };

        //set listeners for each table on the interface
        for(int i=0;i<11;i++){
            buttons[i].setOnClickListener(listener);
        }

        //set listeners for top bar layout
        addPartyButton.setOnClickListener(listener);
        refreshList.setOnClickListener(listener);


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
                resPartyActionMenu.getMenuInflater().inflate(R.menu.party_action_menu, resPartyActionMenu.getMenu());
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
                        switch(item.toString()){
                            case "Seat":
                                //call method / activity to seat the reservation party to a table
                                //countCover returns int of rows deleted;
                                wdb.countCover(selectedEntry);
                                break;
                            case "View":
                                //call method / activity to view or edit the reservation party's information
                                //the the selectedEntry must be modified in the PopupViewReservation activity
                                Intent viewRes = new Intent(getApplicationContext(), ViewReservationPopup.class);
                                startActivity(viewRes);
                                wdb.updateWaitlistEntry(selectedEntry);
                                break;
                            case "Cancel":
                                //call method / activity to cancel the reservation party
                                wdb.deleteWaitlistEntry(selectedEntry);
                                break;
                        }
                        recreate();

                        return true;
                    }
                });

                resPartyActionMenu.show();
            }
        });

        waitListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //Create pop-up for waitlist
                PopupMenu waitPartyActionMenu = new PopupMenu(view.getContext(), view);
                waitPartyActionMenu.getMenuInflater().inflate(R.menu.party_action_menu, waitPartyActionMenu.getMenu());
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
                        switch(item.toString()){
                            case "Seat":
                                //call method / activity to seat the waitlist party to a table
                                //countCover returns int of rows deleted;
                                wdb.countCover(selectedEntry);
                                break;
                            case "View":
                                //call method / activity to view or edit the waitlist party's information
                                //the the selectedEntry must be modified in the PopupViewReservation activity
                                wdb.updateWaitlistEntry(selectedEntry);
                                //uncomment following when the new empty activty is made (it is named PopupViewReservation)
                                //Intent viewRes = new Intent(getApplicationContext(), PopupViewReservation.class);

                                //pass database ID for selected party to the activity

                                //I think by saving the database ID to in int and then passing that to the activity like this:

                                //int dbID = ???;
                                //viewRes.putExtra("key", dbID);

                                //startActivity(viewRes); //see in activty where this information is pulled

                                Intent viewWait = new Intent(getApplicationContext(), ViewWaitlistPopup.class);
                                startActivity(viewWait);
                                break;
                            case "Cancel":
                                //call method / activity to cancel the waitlist party
                                wdb.deleteWaitlistEntry(selectedEntry);
                                break;
                        }
                        recreate();

                        return true;
                    }
                });

                waitPartyActionMenu.show();

            }
        });

    }
}



