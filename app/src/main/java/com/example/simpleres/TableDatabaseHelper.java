package com.example.simpleres;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class TableDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Table.db";
    private static final String TABLE_TABLE_INFO = "tableClass";
    private static final String KEY_ID = "id"; //AKA TABLE NUMBER
    private static final String KEY_STATUS = "status";
    private static final String KEY_NAME = "name";




    public TableDatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_TABLE_INFO + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_STATUS + " TEXT,"
                + KEY_NAME + " TEXT"
                + ")";
        db.execSQL(CREATE_TABLE_TABLE);



    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TABLE_INFO);

        onCreate(db);
    }

    //add an entry to database
    void addTableClass (TableClass tableClass){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("KEY_STATUS", tableClass.getTableStatus());
        values.put("KEY_NAME", tableClass.getTableName());

        db.insert(TABLE_TABLE_INFO,null,values);
        db.close();
    }
    //retrieves tableclass info from database from the table number or "id"/ sorts entries by TABLE NUMBER in list in ascending order
    TableClass getTableClass(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TABLE_INFO, new String[]{KEY_ID, KEY_STATUS, KEY_NAME}, KEY_ID + "=?",
                new String[]{String.valueOf(id)},null,null,KEY_ID +" ASC",null);

        if (cursor!=null)
            cursor.moveToFirst();

        TableClass tableClass = new TableClass(parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2));

        return tableClass;
    }
    //returns list of all waitlist entries
    public List<TableClass> getAllTablesList(){
        List<TableClass> tableClassList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_TABLE_INFO;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                TableClass tableClass = new TableClass();

                tableClass.setTableNumber(Integer.parseInt(cursor.getString(0)));
                tableClass.setTableStatus(cursor.getString(1));
                tableClass.setTableName(cursor.getString(2));
                tableClassList.add(tableClass);
            } while (cursor.moveToNext());
            }
        return tableClassList;
        }
    //used to change values of existing entries in the database
    public int udateTableInfo(TableClass tableClass){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("KEY_NAME", tableClass.getTableStatus());
        values.put("KEY_PHONE", tableClass.getTableName());
        return db.update(TABLE_TABLE_INFO, values, KEY_ID + "=?",
                new String []{String.valueOf(tableClass.getTableNumber())});
    }
    //deletes an existing entry from the database
    public void deleteTableInfo(TableClass tableClass) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TABLE_INFO, KEY_ID + "=?",
                new String[]{String.valueOf(tableClass.getTableNumber())});
        db.close();
    }
    //returns integer value of the count of entries
    public int getTableCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TABLE_INFO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        cursor.close();

        return cursor.getCount();
    }

}

