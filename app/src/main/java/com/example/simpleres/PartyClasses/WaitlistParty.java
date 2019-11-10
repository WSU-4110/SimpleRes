package com.example.simpleres.PartyClasses;

import java.time.LocalDateTime;

public class WaitlistParty extends Party {
    //class members
    private long quoteTime;
    private static int reservationFlag = 1; //will be used by database

    //construct waitlist object
    WaitlistParty(int partyId, String partyName, String partyTelephone, int partyNumberOfPeople,
                  String partyNotes, long waitlistQuoteTime){

        super(partyId, partyName, partyTelephone, partyNumberOfPeople, partyNotes);
        this.quoteTime = waitlistQuoteTime;
        setPartyTimeAndDate(); //set the party time and date for party class
    }

    //default
    WaitlistParty(){
        super();
    }

    //setters
    public void setQuoteTime(long newQuoteTime){
        this.quoteTime = newQuoteTime;
    }

    //getters
    public long getQuoteTime(){
        return this.quoteTime;
    }

    //implement abstract method from interface
    public void setPartyTimeAndDate(){
        //PartyTimeAndDate is stored as "YYYY/MM/DD HH:MM"
        //for waitlist, the date will be the current date and time will be current time + quoted time
        long year = LocalDateTime.now().getYear();
        long month = LocalDateTime.now().getMonthValue();
        long day = LocalDateTime.now().getDayOfMonth();
        long hours = LocalDateTime.now().getHour();
        long minutes = LocalDateTime.now().getMinute();

        long quoteHours = 0;
        long quoteMinutes = 0;

        //max quote time is 60 min
        if(this.quoteTime == 60){
            quoteHours = 1;
            quoteMinutes = 0;
        } else {
            quoteMinutes = this.quoteTime;
        }

        hours += quoteHours;
        minutes += quoteMinutes;

        this.partyTimeAndDate = year + "/" + month + "/" + day + " " + hours + ":" + minutes;
    }
}
