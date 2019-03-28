package com.abdihakim.sqlitelogin.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.abdihakim.sqlitelogin.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UserManager.db";
    private static final String TABLE_USER = "user";
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";


    // Querry to create table
    private String CREATE_USER_TABLE = " CREATE TABLE "+TABLE_USER+"("
            +COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +COLUMN_USER_NAME +" TEXT , "
            +COLUMN_USER_EMAIL+" TEXT , "
            +COLUMN_USER_PASSWORD+" TEXT "+" )";
 // Now our table is fully created and a created table must have a corresponding
    private String DROP_USER_TABLE = " DROP TABLE IF EXISTS " + TABLE_USER;

    // we will have a construsctor of the DatabaseHelper

    public DatabaseHelper(Context context){
super(context,DATABASE_NAME,null, DATABASE_VERSION);

    }
    @Override
    public void onCreate (SQLiteDatabase db){
        db.execSQL(CREATE_USER_TABLE);


    }
    @Override

    public void onUpgrade(SQLiteDatabase db , int oldVersion , int newVersion){
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }
    public void addUser(User user){

      // We get the user from the user class in the model directory
      SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME , user.getName());
        values.put(COLUMN_USER_EMAIL , user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // now we call the db to insert into the table user
        db.insert(TABLE_USER, null, values);
        db.close();

    }

    public boolean checkUser(String email){

// This will help in the validation
String columns [] = {
        COLUMN_USER_ID
};
SQLiteDatabase db = this.getWritableDatabase();
String selection = COLUMN_USER_EMAIL+" =?";
String [] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_USER, columns,selection,selectionArgs,null, null, null);


        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0){


            return  true;


        }
return false;
    }

    public boolean checkUser (String email , String password){

        String [] columns = {

                COLUMN_USER_ID
        };

        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " =?" + "AND" +COLUMN_USER_PASSWORD +" =?";
        String [] selctionArgs = {email, password};
Cursor cursor = db.query(TABLE_USER,
        columns,
        selection,
        selctionArgs,
        null,
        null,
        null);

int cusorCount = cursor.getCount();

if(cusorCount > 0){

    return true;
}
return false;
    }
}
