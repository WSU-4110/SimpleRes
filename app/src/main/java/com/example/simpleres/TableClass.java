package com.example.simpleres;

public class TableClass {

    //variables
    private String tableStatus;     //current status of the table
    private int tableNumber;        //Number of the table
    private String tableName;       //Name of the group sitting there


    TableClass(int Num, String Status, String Name) {
        this.tableNumber = Num;
        this.tableStatus = Status;
        this.tableName = Name;



    }
    public TableClass(){}
    public String getTableStatus() {
        return this.tableStatus;
    }

    public int getTableNumber() {
        return this.tableNumber;
    }

    public void setTableNumber(int newTableNumber) { this.tableNumber = newTableNumber; }

    public void setTableStatus(String newTableStatus) {
        this.tableStatus = newTableStatus;
    }

    public void setTableName(String newTableName) {
        this.tableName = newTableName;
    }

    public String getTableName(){ return this.tableName;}



}

