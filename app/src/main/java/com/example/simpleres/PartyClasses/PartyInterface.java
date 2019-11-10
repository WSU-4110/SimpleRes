package com.example.simpleres.PartyClasses;

import com.example.simpleres.WaitlistDatabaseHelper;

public interface PartyInterface {
    //methods that will be used by abstract class Party (to be extended to both WaitlistParty and ReservationParty
    //setters
    void setName(String name);
    void setNumberOfPeople(int numberOfPeople);
    void setNotes(String notes);
    void setTelephone(String telephone);
    void setId(int id);
    void createId(WaitlistDatabaseHelper wdb);


    //getters
    String getName();
    int getNumberOfPeople();
    String getNotes();
    String getTelephone();
    int getId();
    String getPartyTimeAndDate();

    //methods that will be abstract for Party class
    void setPartyTimeAndDate();


}
