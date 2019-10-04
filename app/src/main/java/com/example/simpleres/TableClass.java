package com.example.simpleres;

public class TableClass extends MainActivity{

    //variables
    private String tableStatus;     //current status of the table
    private int tableNumber;        //Number of the table
    private String tableName;       //Name of the group sitting there


    TableClass(int Num, String Status, String Name) {
        int tableNumber = Num;
        String tableStatus = Status;
        String tableName = Name;
    }

    public String getTableStatus() {
        return this.tableStatus;
    }

    public int getTableNumber() {
        return this.tableNumber;
    }

    public void setTableStatus(String newTableStatus) {
        this.tableStatus = newTableStatus;
    }

    public void setTableName(String newTableName) {
        this.tableName = newTableName;
    }

}

