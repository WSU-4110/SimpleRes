package com.example.simpleres;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static java.lang.Integer.parseInt;

public class CoverDatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Cover.db";
    private static final String COVER_TABLE_INFO = "cover";
    private static final String KEY_DATE = "date"; //AKA TABLE NUMBER
    private static final String KEY_COVER = "coverAmount";

    CoverDatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE_TABLE = "CREATE TABLE IF NOT EXISTS " + COVER_TABLE_INFO + "("
                + KEY_DATE + " TEXT PRIMARY KEY UNIQUE,"
                + KEY_COVER + " INTEGER"
                + ")";
        System.out.println("Executing SQLite: \n"+CREATE_TABLE_TABLE);
        db.execSQL(CREATE_TABLE_TABLE);
        System.out.println("Table "+ COVER_TABLE_INFO +" Created" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + COVER_TABLE_INFO);

        onCreate(db);
    }

    //add an entry to database
    void addCover (Cover cover){
        System.out.println(DATABASE_NAME+" connection opened");

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, cover.getDateAsString());
        values.put(KEY_COVER, cover.getDailyCover());
        try {
            db.insert(COVER_TABLE_INFO, null, values);
        }
        catch (Exception e){
            System.out.println("Cover Already Exists in Database");
        }
        db.close();
        System.out.println(DATABASE_NAME+" connection closed");

    }

    //retrieves cover info from database from the "date" sorts entries by date in list in ascending order
    Cover getCover(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.query(COVER_TABLE_INFO, new String[]{KEY_DATE, KEY_COVER}, KEY_DATE + "=?",
                new String[]{String.valueOf(date)},null,null,KEY_DATE +" ASC",null);

        if (cursor!=null)
            cursor.moveToFirst();

        assert cursor != null;
        Cover cover = new Cover(parseInt(cursor.getString(1)), cursor.getString(0));
        db.close();
        return cover;
    }

    //used to change values of existing entries in the database
    void updateCover(Cover cover){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, cover.getDateAsString());
        values.put(KEY_COVER, cover.getDailyCover());
        db.update(COVER_TABLE_INFO, values, KEY_DATE + "=?",
                new String[]{String.valueOf(cover.getDateAsString())});
    }

    /* Unused methods
    //returns list of all waitlist entries
    public ArrayList<Cover> getAllCovers(){
        ArrayList<Cover> coverList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + COVER_TABLE_INFO;
        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                Cover cover = new Cover(Integer.parseInt(cursor.getString(1)),cursor.getString(0));
                coverList.add(cover);
            } while (cursor.moveToNext());
            }
        db.close();
        return coverList;
    }

    //deletes an existing entry from the database
    public void deleteCover(Cover cover) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(COVER_TABLE_INFO, KEY_DATE + "=?",
                new String[]{String.valueOf(cover.getDateAsString())});
        db.close();
    }

    //returns integer value of the count of entries
    public int getCoverCount() {
        String countQuery = "SELECT  * FROM " + COVER_TABLE_INFO;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery,null);
        cursor.close();

        return cursor.getCount();
    }
    */
}

