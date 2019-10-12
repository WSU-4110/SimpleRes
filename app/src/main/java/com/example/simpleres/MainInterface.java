package com.example.simpleres;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupMenu;

public class MainInterface extends AppCompatActivity {

    //creating new table objects
    TableClass Table101 = new TableClass(101, "Empty", "None" );
    TableClass Table102 = new TableClass(102, "Empty", "None" );
    TableClass Table103 = new TableClass(103, "Empty", "None" );
    TableClass Table104 = new TableClass(104, "Empty", "None" );
    TableClass Table105 = new TableClass(105, "Empty", "None" );
    TableClass Table106 = new TableClass(106, "Empty", "None" );
    TableClass Table107 = new TableClass(107, "Empty", "None" );
    TableClass Table108 = new TableClass(108, "Empty", "None" );
    TableClass Table109 = new TableClass(109, "Empty", "None" );
    TableClass Table201 = new TableClass(201, "Empty", "None" );
    TableClass Table202 = new TableClass(202, "Empty", "None" );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);

        //table buttons on main interface layout
        final Button button1 = findViewById(R.id.button1);
        final Button button2 = findViewById(R.id.button2);
        final Button button3 = findViewById(R.id.button3);
        final Button button4 = findViewById(R.id.button4);
        final Button button5 = findViewById(R.id.button5);
        final Button button6 = findViewById(R.id.button6);
        final Button button7 = findViewById(R.id.button7);
        final Button button8 = findViewById(R.id.button8);
        final Button button9 = findViewById(R.id.button9);
        final Button button10 = findViewById(R.id.button10);
        final Button button11 = findViewById(R.id.button11);

        //top bar 'Add party (+)' button
        final ImageButton addPartyButton = findViewById(R.id.addPartyButton);

        View.OnClickListener listener = new View.OnClickListener() {

            //method for which actions are taken when a button is clicked
            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    //table buttons in main interface -> table view

                    case R.id.button1:
                        PopupMenu dropdownMenu = new PopupMenu(MainInterface.this, button1);
                        dropdownMenu.getMenuInflater().inflate(R.menu.dropdown_menu, dropdownMenu.getMenu());

                        dropdownMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                Table101.setTableStatus(menuItem.toString()); // set the value of tableStatus in TableClass to the selected name

                                //set background of button based on the choice from the dropdown menu
                                switch (menuItem.toString()) {
                                    case "Entree":
                                        button1.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue));
                                        break;
                                    case "Dessert":
                                        button1.setBackgroundDrawable(getResources().getDrawable(R.drawable.orange));
                                        break;
                                    case "Paid":
                                        button1.setBackgroundDrawable(getResources().getDrawable(R.drawable.green));
                                        break;
                                    case "Dirty":
                                        button1.setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
                                        break;
                                    case "Empty":
                                        button1.setBackgroundDrawable(getResources().getDrawable(R.drawable.purple));
                                        break;
                                }

                                return true;
                            }
                        });

                        dropdownMenu.show();
                        break;
                    case R.id.button2:
                        PopupMenu dropdownMenu2 = new PopupMenu(MainInterface.this, button2);
                        dropdownMenu2.getMenuInflater().inflate(R.menu.dropdown_menu, dropdownMenu2.getMenu());

                        dropdownMenu2.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                Table102.setTableStatus(menuItem.toString()); // set the value of tableStatus in TableClass to the selected name

                                //set background of button based on the choice from the dropdown menu
                                switch (menuItem.toString()) {
                                    case "Entree":
                                        button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue));
                                        break;
                                    case "Dessert":
                                        button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.orange));
                                        break;
                                    case "Paid":
                                        button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.green));
                                        break;
                                    case "Dirty":
                                        button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
                                        break;
                                    case "Empty":
                                        button2.setBackgroundDrawable(getResources().getDrawable(R.drawable.purple));
                                        break;
                                }

                                return true;
                            }
                        });

                        dropdownMenu2.show();
                        break;
                    case R.id.button3:
                        PopupMenu dropdownMenu3 = new PopupMenu(MainInterface.this, button3);
                        dropdownMenu3.getMenuInflater().inflate(R.menu.dropdown_menu, dropdownMenu3.getMenu());

                        dropdownMenu3.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                Table103.setTableStatus(menuItem.toString()); // set the value of tableStatus in TableClass to the selected name

                                //set background of button based on the choice from the dropdown menu
                                switch (menuItem.toString()) {
                                    case "Entree":
                                        button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue));
                                        break;
                                    case "Dessert":
                                        button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.orange));
                                        break;
                                    case "Paid":
                                        button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.green));
                                        break;
                                    case "Dirty":
                                        button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
                                        break;
                                    case "Empty":
                                        button3.setBackgroundDrawable(getResources().getDrawable(R.drawable.purple));
                                        break;
                                }

                                return true;
                            }
                        });

                        dropdownMenu3.show();
                        break;
                    case R.id.button4:
                        PopupMenu dropdownMenu4 = new PopupMenu(MainInterface.this, button4);
                        dropdownMenu4.getMenuInflater().inflate(R.menu.dropdown_menu, dropdownMenu4.getMenu());

                        dropdownMenu4.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                Table104.setTableStatus(menuItem.toString()); // set the value of tableStatus in TableClass to the selected name

                                //set background of button based on the choice from the dropdown menu
                                switch (menuItem.toString()) {
                                    case "Entree":
                                        button4.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue));
                                        break;
                                    case "Dessert":
                                        button4.setBackgroundDrawable(getResources().getDrawable(R.drawable.orange));
                                        break;
                                    case "Paid":
                                        button4.setBackgroundDrawable(getResources().getDrawable(R.drawable.green));
                                        break;
                                    case "Dirty":
                                        button4.setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
                                        break;
                                    case "Empty":
                                        button4.setBackgroundDrawable(getResources().getDrawable(R.drawable.purple));
                                        break;
                                }

                                return true;
                            }
                        });

                        dropdownMenu4.show();
                        break;
                    case R.id.button5:
                        PopupMenu dropdownMenu5 = new PopupMenu(MainInterface.this, button5);
                        dropdownMenu5.getMenuInflater().inflate(R.menu.dropdown_menu, dropdownMenu5.getMenu());

                        dropdownMenu5.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                Table105.setTableStatus(menuItem.toString()); // set the value of tableStatus in TableClass to the selected name

                                //set background of button based on the choice from the dropdown menu
                                switch (menuItem.toString()) {
                                    case "Entree":
                                        button5.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue));
                                        break;
                                    case "Dessert":
                                        button5.setBackgroundDrawable(getResources().getDrawable(R.drawable.orange));
                                        break;
                                    case "Paid":
                                        button5.setBackgroundDrawable(getResources().getDrawable(R.drawable.green));
                                        break;
                                    case "Dirty":
                                        button5.setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
                                        break;
                                    case "Empty":
                                        button5.setBackgroundDrawable(getResources().getDrawable(R.drawable.purple));
                                        break;
                                }

                                return true;
                            }
                        });

                        dropdownMenu5.show();
                        break;
                    case R.id.button6:
                        PopupMenu dropdownMenu6 = new PopupMenu(MainInterface.this, button6);
                        dropdownMenu6.getMenuInflater().inflate(R.menu.dropdown_menu, dropdownMenu6.getMenu());

                        dropdownMenu6.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                Table106.setTableStatus(menuItem.toString()); // set the value of tableStatus in TableClass to the selected name

                                //set background of button based on the choice from the dropdown menu
                                switch (menuItem.toString()) {
                                    case "Entree":
                                        button6.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue));
                                        break;
                                    case "Dessert":
                                        button6.setBackgroundDrawable(getResources().getDrawable(R.drawable.orange));
                                        break;
                                    case "Paid":
                                        button6.setBackgroundDrawable(getResources().getDrawable(R.drawable.green));
                                        break;
                                    case "Dirty":
                                        button6.setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
                                        break;
                                    case "Empty":
                                        button6.setBackgroundDrawable(getResources().getDrawable(R.drawable.purple));
                                        break;
                                }

                                return true;
                            }
                        });

                        dropdownMenu6.show();
                        break;
                    case R.id.button7:
                        PopupMenu dropdownMenu7 = new PopupMenu(MainInterface.this, button7);
                        dropdownMenu7.getMenuInflater().inflate(R.menu.dropdown_menu, dropdownMenu7.getMenu());

                        dropdownMenu7.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                Table107.setTableStatus(menuItem.toString()); // set the value of tableStatus in TableClass to the selected name

                                //set background of button based on the choice from the dropdown menu
                                switch (menuItem.toString()) {
                                    case "Entree":
                                        button7.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue));
                                        break;
                                    case "Dessert":
                                        button7.setBackgroundDrawable(getResources().getDrawable(R.drawable.orange));
                                        break;
                                    case "Paid":
                                        button7.setBackgroundDrawable(getResources().getDrawable(R.drawable.green));
                                        break;
                                    case "Dirty":
                                        button7.setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
                                        break;
                                    case "Empty":
                                        button7.setBackgroundDrawable(getResources().getDrawable(R.drawable.purple));
                                        break;
                                }

                                return true;
                            }
                        });

                        dropdownMenu7.show();
                        break;
                    case R.id.button8:
                        PopupMenu dropdownMenu8 = new PopupMenu(MainInterface.this, button8);
                        dropdownMenu8.getMenuInflater().inflate(R.menu.dropdown_menu, dropdownMenu8.getMenu());

                        dropdownMenu8.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                Table108.setTableStatus(menuItem.toString()); // set the value of tableStatus in TableClass to the selected name

                                //set background of button based on the choice from the dropdown menu
                                switch (menuItem.toString()) {
                                    case "Entree":
                                        button8.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue));
                                        break;
                                    case "Dessert":
                                        button8.setBackgroundDrawable(getResources().getDrawable(R.drawable.orange));
                                        break;
                                    case "Paid":
                                        button8.setBackgroundDrawable(getResources().getDrawable(R.drawable.green));
                                        break;
                                    case "Dirty":
                                        button8.setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
                                        break;
                                    case "Empty":
                                        button8.setBackgroundDrawable(getResources().getDrawable(R.drawable.purple));
                                        break;
                                }

                                return true;
                            }
                        });

                        dropdownMenu8.show();
                        break;
                    case R.id.button9:
                        PopupMenu dropdownMenu9 = new PopupMenu(MainInterface.this, button9);
                        dropdownMenu9.getMenuInflater().inflate(R.menu.dropdown_menu, dropdownMenu9.getMenu());

                        dropdownMenu9.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                Table109.setTableStatus(menuItem.toString()); // set the value of tableStatus in TableClass to the selected name

                                //set background of button based on the choice from the dropdown menu
                                switch (menuItem.toString()) {
                                    case "Entree":
                                        button9.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue));
                                        break;
                                    case "Dessert":
                                        button9.setBackgroundDrawable(getResources().getDrawable(R.drawable.orange));
                                        break;
                                    case "Paid":
                                        button9.setBackgroundDrawable(getResources().getDrawable(R.drawable.green));
                                        break;
                                    case "Dirty":
                                        button9.setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
                                        break;
                                    case "Empty":
                                        button9.setBackgroundDrawable(getResources().getDrawable(R.drawable.purple));
                                        break;
                                }

                                return true;
                            }
                        });

                        dropdownMenu9.show();
                        break;
                    case R.id.button10:
                        PopupMenu dropdownMenu10 = new PopupMenu(MainInterface.this, button10);
                        dropdownMenu10.getMenuInflater().inflate(R.menu.dropdown_menu, dropdownMenu10.getMenu());

                        dropdownMenu10.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                Table201.setTableStatus(menuItem.toString()); // set the value of tableStatus in TableClass to the selected name

                                //set background of button based on the choice from the dropdown menu
                                switch (menuItem.toString()) {
                                    case "Entree":
                                        button10.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue));
                                        break;
                                    case "Dessert":
                                        button10.setBackgroundDrawable(getResources().getDrawable(R.drawable.orange));
                                        break;
                                    case "Paid":
                                        button10.setBackgroundDrawable(getResources().getDrawable(R.drawable.green));
                                        break;
                                    case "Dirty":
                                        button10.setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
                                        break;
                                    case "Empty":
                                        button10.setBackgroundDrawable(getResources().getDrawable(R.drawable.purple));
                                        break;
                                }

                                return true;
                            }
                        });

                        dropdownMenu10.show();
                        break;
                    case R.id.button11:
                        PopupMenu dropdownMenu11 = new PopupMenu(MainInterface.this, button11);
                        dropdownMenu11.getMenuInflater().inflate(R.menu.dropdown_menu, dropdownMenu11.getMenu());

                        dropdownMenu11.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem menuItem) {
                                Table202.setTableStatus(menuItem.toString()); // set the value of tableStatus in TableClass to the selected name

                                //set background of button based on the choice from the dropdown menu
                                switch (menuItem.toString()) {
                                    case "Entree":
                                        button11.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue));
                                        break;
                                    case "Dessert":
                                        button11.setBackgroundDrawable(getResources().getDrawable(R.drawable.orange));
                                        break;
                                    case "Paid":
                                        button11.setBackgroundDrawable(getResources().getDrawable(R.drawable.green));
                                        break;
                                    case "Dirty":
                                        button11.setBackgroundDrawable(getResources().getDrawable(R.drawable.red));
                                        break;
                                    case "Empty":
                                        button11.setBackgroundDrawable(getResources().getDrawable(R.drawable.purple));
                                        break;
                                }

                                return true;
                            }
                        });

                        dropdownMenu11.show();
                        break;

                    //Add party (+) button in main interface -> top bar
                    case R.id.addPartyButton:
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
                        break;
                }

            }
        };

        //tables layout
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        button5.setOnClickListener(listener);
        button6.setOnClickListener(listener);
        button7.setOnClickListener(listener);
        button8.setOnClickListener(listener);
        button9.setOnClickListener(listener);
        button10.setOnClickListener(listener);
        button11.setOnClickListener(listener);

        //top bar layout
        addPartyButton.setOnClickListener(listener);

    }
}
