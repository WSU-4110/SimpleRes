package com.example.simpleres;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cover {
    private int dailyCover;
    private LocalDate date;
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    Cover(int DailyCover, LocalDate LocalDate){
        this.date=LocalDate;
        this.dailyCover=DailyCover;
    }

    Cover(LocalDate LocalDate){
        this.date=LocalDate;
        this.dailyCover=0;
    }
    Cover(String dateAsString)
    {
        this.date =LocalDate.parse(dateAsString, format);
    }
    Cover(int DailyCover, String dateAsString)
    {
        this.dailyCover=DailyCover;
        this.date =LocalDate.parse(dateAsString, format);
    }

    public void setDailyCover(int DailyCover){
        this.dailyCover=DailyCover;
    }
    public int getDailyCover(){
        return this.dailyCover;
    }
    public void setDate(LocalDate LocalDate){
        this.date=LocalDate;
    }
    public LocalDate getDate(){
        return this.date;
    }
    public String getDateAsString(){
        return this.date.toString();
    }
    public void addToCover(int adder){
        this.dailyCover = dailyCover+adder;
    }







}



