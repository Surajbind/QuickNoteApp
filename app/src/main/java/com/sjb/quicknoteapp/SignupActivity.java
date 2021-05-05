package com.sjb.quicknoteapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    EditText Username, Email, Mobile, Password;
    Button register;
    private DatabaseHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setTitle("Singup Form");


        Username = findViewById(R.id.nameEditsignup);
        Email = findViewById(R.id.emailEditsignup);
        Mobile = findViewById(R.id.mobileEditsignup);
        Password = findViewById(R.id.passwordEditsignup);
        register = findViewById(R.id.btn_register);
        myDB = new DatabaseHelper(this);


        Mobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validateMobile(Mobile.getText().toString())) {
                    register.setEnabled(true);
                } else {
                    register.setEnabled(false);
                    Mobile.setError("Enter 10 Digit Indian Mobile");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ((validateEmail(Email.getText().toString())) || (!Patterns.EMAIL_ADDRESS.matcher(Email.getText().toString()).matches())) {
                    register.setEnabled(true);

                } else {
                    register.setEnabled(false);
                    Email.setError("Enter A Valid Email");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!Username.getText().toString().matches("[a-z,A-Z]*")) {
                    register.setEnabled(true);

                } else {
                    register.setEnabled(false);
                    Username.setError("Invalid Username");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (validatePass(Password.getText().toString())) {
                    register.setEnabled(true);

                } else {
                    register.setEnabled(false);
                    Username.setError("Password Should Be Minimum 8 to 15 char ");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        register.setOnClickListener((View v) -> {
            boolean var = myDB.registerUser(Username.getText().toString(), Email.getText().toString(), Mobile.getText().toString(), Password.getText().toString());
            if (var) {
                Toast.makeText(SignupActivity.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            } else
                Toast.makeText(SignupActivity.this, "Registeration Error", Toast.LENGTH_SHORT).show();

            if ((Email.length() == 0) && (Mobile.length() == 0) && (Password.length() == 0) && (register.length() == 0)) {
                Toast.makeText(SignupActivity.this, "* All Fields Are Required", Toast.LENGTH_SHORT).show();
            }
        });


    }


    boolean validateMobile(String input) {
        Pattern p = Pattern.compile("[6-9][0-9]{10}");
        Matcher m = p.matcher(input);

        return m.matches();
    }

    boolean validateEmail(String input) {
        Pattern p = Pattern.compile("[a-z][A-Z]{4,25}$");
        Matcher m = p.matcher(input);

        return m.matches();
    }

    boolean validatePass(String input) {
        int c = 0;

        if (8 < input.length() && input.length() <= 15) {
            if (input.matches(".*\\d.*")) {
                c++;
                if (input.matches(".*[A-Z].*")) {
                    c++;
                    if (input.matches(".*[a-z].*")) {
                        c++;
                        if (input.matches(".*[*.!@#$%^&()|:;<>,?/`].*")) {
                            c++;
                        }
                    }

                }
            }
        }
        return c >= 3;
    }
}

