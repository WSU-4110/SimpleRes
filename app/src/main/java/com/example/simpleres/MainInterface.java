package com.example.simpleres;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.ListView;
import java.util.ArrayList;
import java.time.LocalDateTime;

import static android.R.id.empty;

public class MainInterface extends AppCompatActivity {
    WaitlistDatabaseHelper wdb = new WaitlistDatabaseHelper(this);//these objects act as a link an open link to the database
    TableDatabaseHelper tdb = new TableDatabaseHelper(this);

    //WaitlistEntry testEntry = new WaitlistEntry(2,"Jimmy","888-8888",1,WaitlistEntry.FormatDate(LocalDateTime.now()), LocalDateTime.now());
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
    System.out.println("error getting table info from database");}
//add tables
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
    catch(Exception e) {
        System.out.println("error adding tables to database");
    }
        //wdb.addWaitlistEntry(testEntry);//this is how you enter data into the database

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

        View.OnClickListener listener = new View.OnClickListener() {

            //method for which actions are taken when a button is clicked
            @Override
            public void onClick(View view) {

                for (int i = 0; i<11;i++){
                    if (view.getId() == buttons[i].getId())
                    {
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
                      //  break;
                }

            }
        };

        //set listeners for each table on the interface
        for(int i=0;i<11;i++){
            buttons[i].setOnClickListener(listener);
        }


        //top bar layout
        addPartyButton.setOnClickListener(listener);



        //Array of elements in the reservation listview
        resListView = (ListView) findViewById(R.id.reservationListView);
        ArrayList<Party> resPartyList = new ArrayList<>();
        resPartyList.add(new Party("1:30", "Human" , "4"));
        resPartyList.add(new Party("8:30", "Trump" , "1"));
        resPartyList.add(new Party("4:30", "OG Obama" , "10"));
        resPartyList.add(new Party("3:30", "HEEY Yo" , "4"));
        resPartyList.add(new Party("6:30", "Selena gomez" , "4"));
        resPartyList.add(new Party("4:30", "Drake" , "4"));
        resPartyList.add(new Party("4:54", "Michael" , "1"));
        resPartyList.add(new Party("4:30", "Bella" , "8"));
        resPartyList.add(new Party("4:30", "Ben 10" , "10"));
        resPartyList.add(new Party("7:30", "Frank Ocean" , "4"));
        resPartyList.add(new Party("4:30", "Grandpa" , "4"));
        resPartyList.add(new Party("8:30", "Hadi Elamin" , "4"));
        resPartyList.add(new Party("4:30", "Hadi Elamin" , "4"));

        //adapter for the listview
        rAdapter = new ResPartyAdapter(this,resPartyList);
        resListView.setAdapter(rAdapter);
        //display a message when empty
        resListView.setEmptyView(findViewById(R.id.emptyElement));


        //Array of elements in the reservation listview
        waitListView = (ListView) findViewById(R.id.waitlistListView);
        ArrayList<Party> waitPartyList = new ArrayList<>();
        waitPartyList.add(new Party("4:30", "Hadi Elamin" , "4"));
        waitPartyList.add(new Party("4:00", "Fernando" , "7"));
        waitPartyList.add(new Party("5:00", "Bella" , "1"));
        waitPartyList.add(new Party("6:00", "Michael" , "4"));
        waitPartyList.add(new Party("1:20", "Ben Franklin" , "7"));
        waitPartyList.add(new Party("3:30", "Dumb Nigga" , "2"));
        waitPartyList.add(new Party("7:40", "Frank Ocean" , "5"));
        waitPartyList.add(new Party("8:00", "Travis" , "4"));
        waitPartyList.add(new Party("4:30", "Hadi Elamin" , "4"));
        waitPartyList.add(new Party("5:00", "Bella" , "1"));
        waitPartyList.add(new Party("6:00", "Michael" , "4"));
        waitPartyList.add(new Party("1:20", "Ben Franklin" , "7"));
        waitPartyList.add(new Party("3:30", "Dumb Nigga" , "2"));

        //adapter for the listview
        wAdapter = new WaitPartyAdapter(this,waitPartyList);
        waitListView.setAdapter(wAdapter);
        //display a message when empty
        waitListView.setEmptyView(findViewById(R.id.emptyElement2));


    }
}
