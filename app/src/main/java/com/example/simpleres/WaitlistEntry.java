package com.example.simpleres;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class WaitlistEntry {
    private int id;
    private String name;
    private String telephone;
    private int numberOfPeople;
    private String formattedDateTime;
    private int reservationFlag = 0;
    private String notes;
    private LocalDateTime reservationTime;
    private int checkBox = 0;

    // database constructor
    WaitlistEntry(int Id, String Name, String Telephone, int NumberOfPeople, String FormattedDateTime,int ReservationFlag, String Notes) {
        this.id = Id;
        this.name = Name;
        this.telephone = Telephone;
        this.numberOfPeople = NumberOfPeople;
        this.formattedDateTime = FormattedDateTime;
        this.reservationFlag = ReservationFlag;
        this.notes = Notes;
    }

    WaitlistEntry(int Id, String Name, String Telephone, int NumberOfPeople, String FormattedDateTime, int ReservationFlag, String Notes, int CheckBox) {
        this.id = Id;
        this.name = Name;
        this.telephone = Telephone;
        this.numberOfPeople = NumberOfPeople;
        this.formattedDateTime = FormattedDateTime;
        this.reservationFlag = ReservationFlag;
        this.notes = Notes;
        this.checkBox = CheckBox;
    }

    // constructor for waitlist
    WaitlistEntry(String Name, String Telephone, int NumberOfPeople, long QuotedTime, String Notes) {
        this.name = Name;
        this.telephone = Telephone;
        this.numberOfPeople = NumberOfPeople;
        this.reservationTime = LocalDateTime.now().plusMinutes(QuotedTime); //this adds quoted time to current time
        this.formattedDateTime = formatDate(reservationTime);
        this.notes = Notes;
    }

    // constructor for reservation
    WaitlistEntry(String Name, String Telephone, int NumberOfPeople, LocalDateTime ReservationTime, int ReservationFlag, String Notes) {
        this.name = Name;
        this.telephone = Telephone;
        this.numberOfPeople = NumberOfPeople;
        this.reservationTime = ReservationTime;
        this.reservationFlag = ReservationFlag;
        this.formattedDateTime = formatDate(reservationTime);
        this.notes = Notes;
    }

    public WaitlistEntry() {
    }

    public int getId() { return this.id; }
    public void setId(int Id) { this.id = Id; }
    void createId(WaitlistDatabaseHelper wdb) {
        this.id = wdb.idCreation(this);
    }
    public String getName() {
        return this.name;
    }
    public void setName(String Name) {this.name=Name;}

    String getTelephone() { return this.telephone; }
    void setTelephone(String Telephone) {this.telephone=Telephone;}

    int getNumberOfPeople() { return this.numberOfPeople; }
    void setNumberOfPeople(int NumberOfPeople) {this.numberOfPeople = NumberOfPeople;}

    String getFormattedDateTime(){return this.formattedDateTime;}
    void setFormattedDateTime(String FormattedDateTime) {this.formattedDateTime=FormattedDateTime;}

    int getReservationFlag(){return this.reservationFlag;}
    void setReservationFlag(int ReservationFlag){this.reservationFlag=ReservationFlag;}

    int getCheckBox(){return this.checkBox;}
    void setCheckBox(int CheckBox){this.checkBox=CheckBox;}
    void setCheckBoxOn(){if(getCheckBox()==0) setCheckBox(1);}
    void setCheckBoxOff(){if(getCheckBox()==1) setCheckBox(0);}

    String getNotes(){return this.notes;}
    void setNotes(String ReservationNotes){this.notes =ReservationNotes;}

    //date format for easy sorting year-month-day hours:minutes:seconds
    static String formatDate(LocalDateTime myDateTimeObj) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String formattedDate = myDateTimeObj.format(myFormatObj);
        System.out.println("Date formatted from " + myDateTimeObj.toString() + " to " + formattedDate);
        return formattedDate;
    }

    // returns time in HH:mmtt format//HH:MMam or HH:MMpm (converted from military time)
    String parseTime(){
        if(this.formattedDateTime.equals(""))
            return "";
        String time = this.getFormattedDateTime().substring(11, 16);
        String[] values =  time.split(":");
        if (Integer.parseInt(values[0])==0) time = 12 + ":"+values[1] + "am";
        else if(Integer.parseInt(values[0])>12) time = (Integer.parseInt(values[0])-12) + ":"+values[1] + "pm";
        else time = Integer.parseInt(values[0]) + ":"+values[1] + "am";
        return time;
    }

    String contents(){
        return ("id:"+this.getId()+", "+""+this.getName()+", "+""+this.getTelephone()+", "+""+this.getNumberOfPeople()+", "+""+this.getFormattedDateTime()+", "+""+this.getReservationFlag());
    }

    String parseDate(){
        if(this.formattedDateTime.equals(""))
            return "";
        String date = this.getFormattedDateTime().substring(0,10);
        String [] values = date.split("-");//splits time into 3 values that are stings representing year month and day in that order
        date = values[1]+"/"+values[2]+"/"+values[0];
        return date;
    }

    static int getSpinnerPos(int hours, int minutes)
    {
        int first_time = 4;
        if (minutes==30)
            return ((hours-first_time)*2)+1;
        return (hours-first_time)*2;

    }
}