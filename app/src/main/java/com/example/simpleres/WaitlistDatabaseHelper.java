package com.example.simpleres;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class WaitlistDatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Waitlist.db";
    private static final String TABLE_WAITLIST_ENTRY = "waitlist";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_PEOPLE = "people";
    private static final String KEY_TIME = "expectedTime";
    private static final String KEY_RESERVATION = "reservationFlag";



    public WaitlistDatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_WAITLIST_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_WAITLIST_ENTRY + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_NAME + " TEXT,"
                + KEY_PHONE + " TEXT,"
                + KEY_PEOPLE + " TEXT,"
                + KEY_TIME + " TEXT,"
                + KEY_RESERVATION + " TEXT"
                + ")";
        System.out.println("Executing SQLite: \n"+CREATE_WAITLIST_TABLE);
        db.execSQL(CREATE_WAITLIST_TABLE);
        System.out.println("Table "+TABLE_WAITLIST_ENTRY+" Created" );
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WAITLIST_ENTRY);

        onCreate(db);
    }

    //add an entry to database
    void addWaitlistEntry (WaitlistEntry waitlistEntry){
        SQLiteDatabase db = this.getWritableDatabase();
        System.out.println(DATABASE_NAME+"connection opened");
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, waitlistEntry.getName());
        values.put(KEY_PHONE, waitlistEntry.getTelephone());
        values.put(KEY_PEOPLE, waitlistEntry.getNumberOfPeople());
        values.put(KEY_TIME, waitlistEntry.getFormattedDateTime());
        values.put(KEY_RESERVATION, waitlistEntry.getReservationFlag());
        db.insert(TABLE_WAITLIST_ENTRY,null, values);
        System.out.println(DATABASE_NAME+"connection closed");
    }
    //retrieves waitlist entry from database/ sorts entries by date and time in list in ascending order
    WaitlistEntry getWaitlistEntry(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_WAITLIST_ENTRY, new String[]{KEY_ID, KEY_NAME, KEY_PHONE, KEY_PEOPLE, KEY_TIME, KEY_RESERVATION}, KEY_ID + "=?",
                new String[]{String.valueOf(id)},null,null,KEY_TIME +" ASC",null);

        if (cursor!=null)
            cursor.moveToFirst();

        WaitlistEntry waitlistEntry = new WaitlistEntry(parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2),parseInt(cursor.getString(3)),cursor.getString(4),parseInt(cursor.getString(5)));

        return waitlistEntry;
    }
    //returns list of all waitlist entries
    public List<WaitlistEntry> getAllWaitlistEntries(){
        List<WaitlistEntry> waitlistEntryList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_WAITLIST_ENTRY + " ORDER BY "+KEY_TIME +" ASC";//Sort by time
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                WaitlistEntry waitlistEntry = new WaitlistEntry();

                waitlistEntry.setId(Integer.parseInt(cursor.getString(0)));
                waitlistEntry.setName(cursor.getString(1));
                waitlistEntry.setTelephone(cursor.getString(2));
                waitlistEntry.setNumberOfPeople(Integer.parseInt(cursor.getString(3)));
                waitlistEntry.setFormattedDateTime(cursor.getString(4));
                waitlistEntry.setReservedTime(LocalDateTime.parse(cursor.getString(5)));
                waitlistEntry.setReservationFlag(Integer.parseInt(cursor.getString(6)));

                waitlistEntryList.add(waitlistEntry);
            } while (cursor.moveToNext());
        }
        return waitlistEntryList;
    }
    //populates waitlist List
    public ArrayList<WaitlistEntry> getWaitlistList(){
        ArrayList<WaitlistEntry> waitlistEntryList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_WAITLIST_ENTRY + " WHERE " + KEY_RESERVATION+" = 0 ORDER BY " + KEY_TIME + " ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                WaitlistEntry waitlistEntry = new WaitlistEntry();

                waitlistEntry.setId(Integer.parseInt(cursor.getString(0)));
                waitlistEntry.setName(cursor.getString(1));
                waitlistEntry.setTelephone(cursor.getString(2));
                waitlistEntry.setNumberOfPeople(Integer.parseInt(cursor.getString(3)));
                waitlistEntry.setFormattedDateTime(cursor.getString(4));
                waitlistEntry.setReservationFlag(Integer.parseInt(cursor.getString(5)));

                waitlistEntryList.add(waitlistEntry);
            } while (cursor.moveToNext());
        }
        return waitlistEntryList;
    }
    //populates Reservation List
    public ArrayList<WaitlistEntry> getReservationList(){
        ArrayList<WaitlistEntry> waitlistEntryList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_WAITLIST_ENTRY + " WHERE " + KEY_RESERVATION+" = 1 ORDER BY " + KEY_TIME + " ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                WaitlistEntry waitlistEntry = new WaitlistEntry();

                waitlistEntry.setId(Integer.parseInt(cursor.getString(0)));
                waitlistEntry.setName(cursor.getString(1));
                waitlistEntry.setTelephone(cursor.getString(2));
                waitlistEntry.setNumberOfPeople(Integer.parseInt(cursor.getString(3)));
                waitlistEntry.setFormattedDateTime(cursor.getString(4));
                waitlistEntry.setReservationFlag(Integer.parseInt(cursor.getString(5)));

                waitlistEntryList.add(waitlistEntry);
            } while (cursor.moveToNext());
        }
        return waitlistEntryList;
    }
    //used to change values of existing entries in the database
    public int updateWaitlistEntry(WaitlistEntry waitlistEntry){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, waitlistEntry.getName());
        values.put(KEY_PHONE, waitlistEntry.getTelephone());
        values.put(KEY_PEOPLE, waitlistEntry.getNumberOfPeople());
        values.put(KEY_TIME, waitlistEntry.getFormattedDateTime());
        values.put(KEY_RESERVATION, waitlistEntry.getReservationFlag());
        return db.update(TABLE_WAITLIST_ENTRY, values, KEY_ID + "=?",
                new String []{String.valueOf(waitlistEntry.getId())});
    }
    //deletes an existing entry from the database
    public void deleteWaitlistEntry(WaitlistEntry waitlistEntry) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_WAITLIST_ENTRY, KEY_ID + "=?",
                new String[]{String.valueOf(waitlistEntry.getId())});
        db.close();
    }
    //returns integer value of the count of entries
    public int getEntryCount() {
        String countQuery = "SELECT  * FROM " + TABLE_WAITLIST_ENTRY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        cursor.close();

        return cursor.getCount();
    }
//countCover functions as delete while returning the count of affected rows
    public int countCover(WaitlistEntry waitlistEntry){
        SQLiteDatabase db = this.getWritableDatabase();
        int val = db.delete(TABLE_WAITLIST_ENTRY, KEY_ID + "=?",
                new String[]{String.valueOf(waitlistEntry.getId())});
        System.out.println("Counting cover for WaitlistEntry with contents: "+waitlistEntry.contents());
        System.out.println("Number of rows affected: " + val);
        db.close();
        return val;
    }
    //returns id that's generated in the sqlite database, must add to database first
    public int idCreation(WaitlistEntry waitlistEntry){
        //query to get the entry in database that matches class members
        String selectQuery = "SELECT  " + KEY_ID + " FROM " + TABLE_WAITLIST_ENTRY + " WHERE " + KEY_NAME +" = ? "+
                "AND " + KEY_PHONE + " = ? "+
                "AND " + KEY_PEOPLE + " = ? "+
                "AND " + KEY_TIME + " = ? "+
                "AND " + KEY_RESERVATION + " = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,new String[] {waitlistEntry.getName(),
                waitlistEntry.getTelephone(),
                Integer.toString(waitlistEntry.getNumberOfPeople()),
                waitlistEntry.getFormattedDateTime(),
                Integer.toString(waitlistEntry.getReservationFlag())});
        if (cursor!=null)
            cursor.moveToFirst();
        int id = Integer.parseInt(cursor.getString(0));
        cursor.close();
        return id;
    }

}

