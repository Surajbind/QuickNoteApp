package com.sjb.quicknoteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fab;
    Adapter adapter;
    List<Note> noteList;
    NoteDatabase mynotdb;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddNotes.class);
            startActivity(intent);
        });


        noteList = new ArrayList<>();

        mynotdb = new NoteDatabase(this);
        fetchAllNotesfromDatabases();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new Adapter(this, MainActivity.this, noteList);
        recyclerView.setAdapter(adapter);

    }

    private void fetchAllNotesfromDatabases() {
        Cursor cursor = mynotdb.readAllData();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data To Show", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                noteList.add(new Note(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.deleteNotes) {
            deleteAllNotes();
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteAllNotes() {
        NoteDatabase noteDatabase = new NoteDatabase(MainActivity.this);
        noteDatabase.deleteAllNotes();
        recreate();
    }
}