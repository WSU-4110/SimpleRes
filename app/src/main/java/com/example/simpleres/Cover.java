package com.example.simpleres;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Cover {
    private int dailyCover;
    private LocalDate date;

    Cover(int DailyCover, LocalDate LocalDate){
        this.date=LocalDate;
        this.dailyCover=DailyCover;
    }

    Cover(){
    }

    Cover(int DailyCover, String dateAsString) {
        this.dailyCover = DailyCover;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.date = LocalDate.parse(dateAsString, format);
    }

    int getDailyCover(){
        return this.dailyCover;
    }

    public void setDate(LocalDate LocalDate){
        this.date=LocalDate;
    }

    public LocalDate getDate(){
        return this.date;
    }

    String getDateAsString(){
        return this.date.toString();
    }

    void addToCover(int adder){
        this.dailyCover += adder;
    }
}