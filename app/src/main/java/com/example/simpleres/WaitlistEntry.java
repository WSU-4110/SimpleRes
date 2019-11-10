package com.example.simpleres;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WaitlistEntry {
    private int id;
    private String name;
    private String telephone;
    private int numberOfPeople;
    private String formattedDateTime;
    private int reservationFlag = 0;
    private LocalDateTime reservationTime;


// database constructor
    WaitlistEntry(int Id, String Name, String Telephone, int NumberOfPeople, String FormattedDateTime,int ReservationFlag) {
        this.id = Id;
        this.name = Name;
        this.telephone = Telephone;
        this.numberOfPeople = NumberOfPeople;
        this.formattedDateTime = FormattedDateTime;
        this.reservationFlag = ReservationFlag;
    }
    // constructor for walk-in
    WaitlistEntry(String Name, String Telephone,int NumberOfPeople, String FormattedDateTime, long QuotedTime) {
        this.name = Name;
        this.telephone = Telephone;
        this.numberOfPeople = NumberOfPeople;
        this.reservationTime = LocalDateTime.now().plusMinutes(QuotedTime); //this adds quoted time to current time
        this.formattedDateTime = FormatDate(reservationTime);
    }
    // constructor for reservation
    WaitlistEntry(String Name, String Telephone,int NumberOfPeople, String FormattedDateTime, LocalDateTime ReservationTime, int ReservationFlag) {
        this.name = Name;
        this.telephone = Telephone;
        this.numberOfPeople = NumberOfPeople;

        this.reservationTime = ReservationTime;
        this.reservationFlag = ReservationFlag;
        this.formattedDateTime = FormatDate(reservationTime);
    }
    public WaitlistEntry() {

    }

    public int getId() { return this.id; }
    public void setId(int Id) { this.id = Id; }
    public void createId(WaitlistDatabaseHelper wdb) {
        this.id = wdb.idCreation(this);
    }
    public String getName() {
        return this.name;
    }
    public void setName(String Name) {this.name=Name;}


    public String getTelephone() { return this.telephone; }
    public void setTelephone(String Telephone) {this.telephone=Telephone;}

    public int getNumberOfPeople() { return this.numberOfPeople; }
    public void setNumberOfPeople(int NumberOfPeople) {this.numberOfPeople = NumberOfPeople;}

    public LocalDateTime getReservationTime() {
        return this.reservationTime;
    }
    public void setReservedTime(LocalDateTime ReservationTime){this.reservationTime=ReservationTime;}

    public String getFormattedDateTime(){return this.formattedDateTime;}
    public void setFormattedDateTime(String FormattedDateTime) {this.formattedDateTime=FormattedDateTime;}

    public int getReservationFlag(){return this.reservationFlag;}
    public void setReservationFlag(int ReservationFlag){this.reservationFlag=ReservationFlag;}

//date format for easy sorting year-month-day hours:minutes:seconds
    public static String FormatDate(LocalDateTime myDateTimeObj) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String FormattedDate = myDateTimeObj.format(myFormatObj);
        System.out.println("Date formatted from " + myDateTimeObj.toString() + " to " + FormattedDate);
        return FormattedDate;
    }
    // returns time in HH:mmtt format//HH:MMam or HH:MMpm (converted from military time)
    public String ParseTime(){
        if (this.formattedDateTime=="")
            return "";
        String time = this.getFormattedDateTime().substring(11, 16);
        String[] values =  time.split(":");
        if (Integer.parseInt(values[0])==0) time = 12 + ":"+values[1] + "am";
        else if(Integer.parseInt(values[0])>12) time = (Integer.parseInt(values[0])-12) + ":"+values[1] + "pm";
        else time = Integer.parseInt(values[0]) + ":"+values[1] + "am";
        return time;
    }
    public String contents (){
        return ("id:"+this.getId()+", "+""+this.getName()+", "+""+this.getTelephone()+", "+""+this.getNumberOfPeople()+", "+""+this.getFormattedDateTime()+", "+""+this.getReservationFlag());
    }
    public String ParseDate(){
        if (this.formattedDateTime=="")
            return "";
        String date = this.getFormattedDateTime().substring(0,10);
        String [] values = date.split("-");//splits time into 3 values that are stings representing year month and day in that order
        date = values[2]+"/"+values[1]+"/"+values[0];
        return date;
    }
}
