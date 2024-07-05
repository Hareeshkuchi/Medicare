package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends AppCompatActivity {
    EditText username,password;
    Button login;
    TextView signup,forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.editTextText);
        password=findViewById(R.id.editTextTextPassword);
        login=findViewById(R.id.loginbtn);
        signup=findViewById(R.id.Signup);
        forgotPassword=findViewById(R.id.textView6);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String u = username.getText().toString();
                String p = password.getText().toString();
                Database db = new Database(getApplicationContext());

                if (username.length() == 0 || password.length() == 0) {
                    Toast.makeText(loginActivity.this, "Please fill all the details", Toast.LENGTH_SHORT).show();
                } else {
                    // Disable the signup button temporarily
                    signup.setEnabled(false);


                        if (db.login(u, p) == 1) {
                            Toast.makeText(loginActivity.this, "Successfully logged In", Toast.LENGTH_SHORT).show();
                            SharedPreferences sp = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("username", u);
                            editor.apply();
                            startActivity(new Intent(loginActivity.this, HomeActivity.class));
                        } else {
                            Toast.makeText(loginActivity.this, "Enter valid username and password", Toast.LENGTH_SHORT).show();
                        }
                    }


                    signup.setEnabled(true);
                }
            });



         signup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(loginActivity.this, signupActivity.class));
        }
    });
    }}
