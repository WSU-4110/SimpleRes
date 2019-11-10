package com.example.simpleres.PartyClasses;

public class ReservationParty extends Party {
    //class members
    private String date;
    private String time;


    //constructors
    ReservationParty(int partyId, String partyName, String partyTelephone, int partyNumberOfPeople,
                     String partyNotes, String partyDate, String partyTime){

        super(partyId, partyName, partyTelephone, partyNumberOfPeople, partyNotes);
        this.date = partyDate;
        this.time = partyTime;
        setPartyTimeAndDate(); //set the party date and time for the party class
    }

    //default
    ReservationParty(){
        super();
    }

    //setters
    public void setDate(String newDate){
        this.date = newDate;
    }

    public void setTime(String newTime){
        this.time = newTime;
    }

    //getters
    public String getDate(){
        return this.date;
    }

    public String getTime(){
        return this.time;
    }

    //implement abstract method from Party interface
    public void setPartyTimeAndDate(){
        //PartyTimeAndDate is stored as "YYYY/MM/DD HH:MM"
        //for reservations, the date and time is passed in correct format
        this.partyTimeAndDate = this.date + " " + this.time;
    }

}
