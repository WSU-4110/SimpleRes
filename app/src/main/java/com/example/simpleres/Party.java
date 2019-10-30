package com.example.simpleres;

public class Party {
    // Store the time for reservation
    private String time;
    // Store the name of the Party
    private String Pname;
    // Store the size of the party
    private String partySize;

    // Constructor that is used to create an instance of the Party object
    public Party(String time, String Pname, String partySize) {
        this.time = time;
        this.Pname = Pname;
        this.partySize = partySize;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String Pname) {
        this.Pname = Pname;
    }

    public String getPartySize() {
        return partySize;
    }

    public void setPartySize(String partySize) {
        this.partySize = partySize;
    }
}
