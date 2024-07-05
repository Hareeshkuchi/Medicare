package com.example.medicare;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;

            if (item.getItemId() == R.id.home) {
                fragment = new homeFragment();
            } else if (item.getItemId() == R.id.records) {
                fragment = new recordsFragment();
            } else if (item.getItemId() == R.id.doctors) {
                fragment = new doctorsFragment();
            } else {
                return false;
            }

            fragmentManager.beginTransaction().replace(R.id.container, fragment).commit();
            return true;
        });


        // Load the initial fragment
        fragmentManager.beginTransaction().replace(R.id.container, new homeFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

   /* @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings) {
            // Handle option 1 click
            return true;
        } else if (item.getItemId() == R.id.logout) {
            // Handle option 2 click
            return true;
        } else if (item.getItemId() == R.id.profile) {
            // Handle option 3 click
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }*/
}
