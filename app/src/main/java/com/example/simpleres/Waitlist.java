package com.example.simpleres;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WaitlistEntry extends MainActivity {
    private int numberOfPeople;
    private String name;
    private String telephone;

    private LocalDateTime reservationTime;
    private String formattedDateTime;

    WaitlistEntry(int NumberOfPeople, String Name, String Telephone,LocalDateTime ReservationTime) {
        int numberOfPeople = NumberOfPeople;
        String name = Name;
        String telephone = Telephone;

        LocalDateTime reservationTime = ReservationTime;
        String formattedDateTime = FormatDate(reservationTime);

    }

    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    public String getName() {
        return this.name;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public LocalDateTime getReservationTime() {
        return this.reservationTime;
    }
//date format for easy sorting year-month-day hours:minutes:seconds
    public String FormatDate(LocalDateTime myDateTimeObj) {
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        String FormattedDate = myDateTimeObj.format(myFormatObj);
        System.out.println("Date formatted from " + myDateTimeObj.toString() + " to " + FormattedDate);
        return FormattedDate;
    }
}
