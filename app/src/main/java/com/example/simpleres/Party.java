package com.example.simpleres;

public class Party {
    // Store the time for reservation
    private int time;
    // Store the name of the Party
    private String Pname;
    // Store the size of the party
    private int partySize;

    // Constructor that is used to create an instance of the Party object
    public Party(int time, String Pname, int partySize) {
        this.time = time;
        this.Pname = Pname;
        this.partySize = partySize;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String Pname) {
        this.Pname = Pname;
    }

    public int getPartySize() {
        return partySize;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }
}
