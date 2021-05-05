package com.sjb.quicknoteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateNotes extends AppCompatActivity {


    EditText title, description;
    Button btnUpdate;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_notes);

        title = findViewById(R.id.titleaddNotes);
        description = findViewById(R.id.descreptionaddNotes);
        btnUpdate = findViewById(R.id.update_notes_btn);

        Intent i = getIntent();
        title.setText(i.getStringExtra("title"));
        description.setText(i.getStringExtra("description"));
        id = i.getStringExtra("id");

        btnUpdate.setOnClickListener(v -> {
            if (!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString())) {
                NoteDatabase nb = new NoteDatabase(UpdateNotes.this);
                nb.updateNotes(title.getText().toString(),
                        description.getText().toString(), id);

                Intent i1 = new Intent(UpdateNotes.this, MainActivity.class);
                i1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i1);
                finish();

            } else {
                Toast.makeText(UpdateNotes.this, "Done", Toast.LENGTH_SHORT).show();

            }
        });

    }
}