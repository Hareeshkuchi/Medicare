package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private TextView user;
    private CardView records;
    private CardView appointments;
    private CardView buymedicine;
    private CardView tips;
    private CardView articles;
    private CardView emergency;
    private CardView doctors;
    private CardView symptomschecker;


    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user = findViewById(R.id.name);
        records = findViewById(R.id.records);
        appointments = findViewById(R.id.appointments);
        buymedicine = findViewById(R.id.buymedicine);
        tips = findViewById(R.id.healthtips);
        articles = findViewById(R.id.articles);
        emergency = findViewById(R.id.emergencyContacts);
        doctors = findViewById(R.id.doctors);
        symptomschecker=findViewById(R.id.symptomschecker);
        /*bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;

            if (item.getItemId() == R.id.home) {
                fragment = new homeFragment();
            } else if (item.getItemId() == R.id.records) {
                fragment = new recordsFragment();
            } else if (item.getItemId() == R.id.doctors) {
                fragment = new doctorsFragment();
            } else if (item.getItemId() == R.id.buymedicine) {
                fragment = new buyMedicineFragment();

            }else {
                return false;
            }

            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
            return true;
        });
*/
        SharedPreferences sp =getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username=sp.getString("username","").toString();
        user.setText(
                "        "+username
        );



        doctors.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           startActivity(new Intent(HomeActivity.this, DoctorsActivity.class));
        }
    });
    records.setOnClickListener((new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(HomeActivity.this,MedicalRecordsActivity.class));
        }
    }));
        appointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AppointmentsActivity.class));
            }
        });
        buymedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, BuyMedicineActivity.class));
            }
        });
        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HealthTipsActivity.class));
            }
        });
        articles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, HealthArticlesActivity.class));
            }
        });
        emergency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, EmergencyContactsActivity.class));
            }
        });
        symptomschecker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SymptomsCheckerActivity.class));
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.settings) {
            startActivity(new Intent(HomeActivity.this,settingsActivity.class));
            return true;
        }
        else if (id == R.id.logout) {
            Toast.makeText(this,"successfully logged out",Toast.LENGTH_SHORT);
            startActivity(new Intent(HomeActivity.this,loginActivity.class));
            return true;
        }
        else{
            startActivity(new Intent(HomeActivity.this,ProfileActivity.class));
            return true;
        }

    }




}