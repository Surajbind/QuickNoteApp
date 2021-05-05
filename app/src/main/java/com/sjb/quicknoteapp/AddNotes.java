package com.sjb.quicknoteapp;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddNotes extends AppCompatActivity {

    EditText title, description;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        title = findViewById(R.id.titleaddNotes);
        description = findViewById(R.id.descreptionaddNotes);
        btnAdd = findViewById(R.id.add_notes_btn);

        btnAdd.setOnClickListener(v -> {

            if (!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString())) {
                NoteDatabase nb = new NoteDatabase(AddNotes.this);
                nb.addNotes(title.getText().toString(), description.getText().toString());

                Intent intent = new Intent(AddNotes.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(AddNotes.this, "Both Fields Are Required", Toast.LENGTH_SHORT).show();

            }
        });
    }
}