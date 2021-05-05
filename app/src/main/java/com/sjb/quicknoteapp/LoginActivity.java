package com.sjb.quicknoteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText EmailLOGIN, PasswordLOGIN;
    Button login, signup;
    DatabaseHelper MyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Login From");

        EmailLOGIN = findViewById(R.id.emailEdit1);
        PasswordLOGIN = findViewById(R.id.passwordEdit1);
        login = findViewById(R.id.btn_login);
        signup = findViewById(R.id.btn_signup);
        MyDB = new DatabaseHelper(this);

        login.setOnClickListener(v -> {

            boolean var = MyDB.checkUser(EmailLOGIN.getText().toString(), PasswordLOGIN.getText().toString());
            if (var) {
                Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                finish();
            } else
                Toast.makeText(LoginActivity.this, "Login Error", Toast.LENGTH_SHORT).show();

        });
    }

    public void open_Signup(View view) {
        startActivity(new Intent(getApplicationContext(), SignupActivity.class));
    }


}