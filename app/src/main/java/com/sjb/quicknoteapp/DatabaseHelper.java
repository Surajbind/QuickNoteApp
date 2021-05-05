package com.sjb.quicknoteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "USERS";
    private static final String TABLE_NAME = "USER_DATA";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "USERNAME";
    private static final String COL_3 = "EMAIL";
    private static final String COL_4 = "MOBILE";
    private static final String COL_5 = "PASSWORD";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "USERNAME TEXT,EMAIL TEXT,MOBILE INTEGER,PASSWORD,TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean registerUser(String usename, String email, String mobile, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, usename);
        contentValues.put(COL_3, email);
        contentValues.put(COL_4, mobile);
        contentValues.put(COL_5, password);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean checkUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] column = {COL_1};
        String selection = COL_3 + "=?" + "and " + COL_5 + "=?";
        String[] selectionargs = {email, password};
        Cursor cursor = db.query(TABLE_NAME, column, selection, selectionargs, null, null, null);

        int count = cursor.getCount();
        db.close();
        cursor.close();

        return count > 0;
    }

}
