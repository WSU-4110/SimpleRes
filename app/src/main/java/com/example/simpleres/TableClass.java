package com.example.simpleres;

class TableClass {
    private String tableStatus;     //current status of the table
    private final int tableNumber;        //Number of the table
    private String tableName;       //Name of the group sitting there

    TableClass(int Num, String Status, String Name) {
        this.tableNumber = Num;
        this.tableStatus = Status;
        this.tableName = Name;
    }

    String getTableStatus() {
        return this.tableStatus;
    }

    int getTableNumber() {
        return this.tableNumber;
    }

    void setTableStatus(String newTableStatus) {
        this.tableStatus = newTableStatus;
    }

    void setTableName(String newTableName) {
        this.tableName = newTableName;
    }

    String getTableName(){
        return this.tableName;
    }
}

