package com.school.ecallowa.nyobora.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.school.ecallowa.nyobora.entities.Person;

/**
 * Created by ecalloway on 11/23/2015.
 */
public class MyPersonDBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "persons.db";
    public static final String TABLE_PERSONS = "persons";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PERSONNAME = "personname";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_INTERESTS = "interests";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_CONNECT = "connect";
    public static final String COLUMN_NOTIFICATIONS = "notifications";
    public static final String COLUMN_AGE = "age";

    public MyPersonDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PERSONS + "( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_PERSONNAME + " TEXT, " +
                COLUMN_LOCATION + " TEXT, "+
                COLUMN_INTERESTS + " TEXT," +
                COLUMN_GENDER + " TEXT," +
                COLUMN_CONNECT + " TEXT," +
                COLUMN_NOTIFICATIONS + " TEXT," +
                COLUMN_AGE + " INT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONS);
        onCreate(db);
    }

    //Add the other person values
    public void addPerson(Person person){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PERSONNAME, person.getName());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PERSONS, null, values);
        db.close();
    }

    //Delete a row from the table
    public void deleteProduct(String personName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PERSONS + " WHERE " + COLUMN_PERSONNAME + "=\"" + personName + "\";");
    }

    //Print out the database as a string
    public String databaseToString(){
        String dbString  = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PERSONS + " WHERE 1";

        //Cursor point to a location in your results
        Cursor cursor = db.rawQuery(query,null);

        //Move the cursor to the first row in your results
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            if(cursor.getString(cursor.getColumnIndex("personname")) != null){
                dbString += cursor.getString(cursor.getColumnIndex("personname"));
                dbString += ",";
                dbString += cursor.getString(cursor.getColumnIndex("location" ));
                dbString += ",";
                dbString += cursor.getString(cursor.getColumnIndex("interests"));
                dbString += ",";
                dbString += cursor.getString(cursor.getColumnIndex("gender"));
                dbString += ",";
                dbString += cursor.getString(cursor.getColumnIndex("connect"));
                dbString += ",";
                dbString += cursor.getString(cursor.getColumnIndex("notifications"));
                dbString += ",";
                dbString += cursor.getString(cursor.getColumnIndex("age"));
                dbString += "\n";
                cursor.moveToNext();
            }
        }
        db.close();
        return dbString;
    }
}
