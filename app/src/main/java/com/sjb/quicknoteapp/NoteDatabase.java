package com.sjb.quicknoteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class NoteDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "NOTES";
    private static final String TABLE_NAME = "NOTES_DATA";

    Context context;
    private static final String key_id = "ID";
    private static final String key_title = "TITLE";
    private static final String key_description = "DESCRIPTION";


    public NoteDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "TITLE TEXT,DESCRIPTION TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addNotes(String title, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(key_title, title);
        cv.put(key_description, description);

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1) {

            Toast.makeText(context, "Data Not Added", Toast.LENGTH_SHORT).show();
        } else if (result == 1) {
            {
                Toast.makeText(context, "Data Added", Toast.LENGTH_SHORT).show();
            }
        }


    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor cursor = null;
        if (database != null) {
            cursor = database.rawQuery(query, null);
        }
        return cursor;

    }

    public void deleteAllNotes() {

        SQLiteDatabase database = this.getWritableDatabase();
        String query = ("DELETE FROM " + TABLE_NAME);
        database.execSQL(query);

    }

    public void updateNotes(String title, String description, String id) {
        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(key_title, title);
        values.put(key_description, description);

        long result = database.update(TABLE_NAME, values, "id=?", new String[]{id});
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }

    }
}
