package com.example.simpleres;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WaitlistEntry extends MainActivity {
    private int id;
    private String name;
    private String telephone;
    private int numberOfPeople;
    private String formattedDateTime;

    private LocalDateTime reservationTime;

    WaitlistEntry(int Id, String Name, String Telephone,int NumberOfPeople, String FormattedDateTime, LocalDateTime ReservationTime) {
        int id = Id;
        String name = Name;
        String telephone = Telephone;
        int numberOfPeople = NumberOfPeople;

        LocalDateTime reservationTime = ReservationTime;

        String formattedDateTime = FormatDate(reservationTime);
    }

    public WaitlistEntry() {

    }

    public int getId() { return this.id; }
    public void setId(int Id) { this.id = Id; }

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


//date format for easy sorting year-month-day hours:minutes:seconds
    public String FormatDate(LocalDateTime myDateTimeObj) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String FormattedDate = myDateTimeObj.format(myFormatObj);
        System.out.println("Date formatted from " + myDateTimeObj.toString() + " to " + FormattedDate);
        return FormattedDate;
    }
// TODO: add contructors for reservation and for walk-in function calls.
//  (WaitlistEntry(int Id, String Name,int NumberOfPeople, LocalDateTime ReservationTime = now())) --walk-in
//  (WaitlistEntry(int Id, String Name, String Telephone,int NumberOfPeople, LocalDateTime ReservationTime))--reservation

}
