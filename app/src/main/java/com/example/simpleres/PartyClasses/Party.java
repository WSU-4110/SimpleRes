package com.example.simpleres.PartyClasses;

import com.example.simpleres.WaitlistDatabaseHelper;

import java.time.LocalDateTime;

public abstract class Party implements PartyInterface {
    //class variables
    private int id;
    private String name;
    private String telephone;
    private int numberOfPeople;
    private String notes;
    protected String partyTimeAndDate;

    //constructors
    public Party(int partyId, String partyName, String partyTelephone, int partyNumberOfPeople, String partyNotes){
        this.id = partyId;
        this.name = partyName;
        this.telephone = partyTelephone;
        this.numberOfPeople = partyNumberOfPeople;
        this.notes = partyNotes;
    }

    public Party(){

    }

    //setters - same for both children
    public void setName(String name){
        this.name = name;
    }

    public void setNumberOfPeople(int numberOfPeople){
        this.numberOfPeople = numberOfPeople;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }

    public void setTelephone(String telephone){
        this.telephone = telephone;
    }

    public void setId(int id){
        this.id = id;
    }
    public void createId(WaitlistDatabaseHelper wdb){
        //this is error bc wbd idCreation method is expecting WaitlistEntry obj, not Party obj...
        //entire database would need to be restructured to implement this pattern
        //this.id = wdb.idCreation(this);
    }

    //getters - same for both children
    public String getName(){
        return this.name;
    }
    public int getNumberOfPeople(){
        return this.numberOfPeople;
    }
    public String getNotes(){
        return this.notes;
    }
    //String getTime(); worry about time shit later
    public String getTelephone(){
        return this.telephone;
    }
    public int getId(){
        return this.id;
    }
    public String getPartyTimeAndDate(){
        return this.partyTimeAndDate;
    }

    //abstract method from Party interface - implemented differently by both children
    public abstract void setPartyTimeAndDate();

}
