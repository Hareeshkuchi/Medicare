package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class signupActivity extends AppCompatActivity {
    EditText email,phone,username,password,passwordCnfrm;
    Button signup;
    TextView signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3);

        email=findViewById(R.id.editTextTextEmailAddress);
        phone=findViewById(R.id.editTextPhoe);
        username=findViewById(R.id.editTextText);
        password=findViewById(R.id.editTextTextPassword);
        passwordCnfrm=findViewById(R.id.editTextTextPasswor2);
        signup=findViewById(R.id.signupbn);
        signin=findViewById(R.id.textView8);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signupActivity.this,loginActivity.class));
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = email.getText().toString();
                String ph = phone.getText().toString();
                String un = username.getText().toString();
                String pass = password.getText().toString();
                String passcnfrm = passwordCnfrm.getText().toString();
                Database db = new Database(getApplicationContext());

                if (em.length() == 0 || ph.length() == 0 || un.length() == 0 || pass.length() == 0 || passcnfrm.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.compareTo(passcnfrm) == 0) {
                        if (isPasswordValid(pass)) {
                            db.signup(ph, em, un, pass);
                            Toast.makeText(getApplicationContext(), "You are registered", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(signupActivity.this, loginActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Password must contain 8 characters, have at least 1 letter, digit, and special character", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Password and Confirm Password did not match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }


        public static boolean isPasswordValid(String password) {
            if (password == null || password.isEmpty()) {
                return false;
            }

            // Check the length of the password
            if (password.length() < 8) {
                return false;
            }


            boolean hasUppercase = !password.equals(password.toLowerCase());


            boolean hasLowercase = !password.equals(password.toUpperCase());

            // Check if the password contains at least one digit
            boolean hasDigit = false;
            for (char c : password.toCharArray()) {
                if (Character.isDigit(c)) {
                    hasDigit = true;
                    break;
                }
            }

            // Check if the password contains at least one special character
            boolean hasSpecialCharacter = !password.matches("[A-Za-z0-9]*");

            // All conditions must be true for the password to be valid
            return hasUppercase && hasLowercase && hasDigit && hasSpecialCharacter;
        }


    }

