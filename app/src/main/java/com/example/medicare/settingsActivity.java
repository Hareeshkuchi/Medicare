package com.example.medicare;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.Toolbar;

public class settingsActivity extends AppCompatActivity {
    TextView settings,darkmode;
    ToggleButton toggleButton;
    LinearLayout layout;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        settings=findViewById(R.id.setings);
        darkmode=findViewById(R.id.darkmode);
        toggleButton=findViewById(R.id.toggleButton);
        layout=findViewById(R.id.layout);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Setting dark mode
                    layout.setBackgroundColor(getResources().getColor(R.color.black));
                    settings.setTextColor(getResources().getColor(R.color.white));
                    darkmode.setBackgroundColor(getResources().getColor(R.color.black));
                    darkmode.setTextColor(getResources().getColor(R.color.white));
                } else {
                    // Setting light mode
                    layout.setBackgroundColor(getResources().getColor(R.color.white));
                    settings.setTextColor(getResources().getColor(R.color.black));
                    darkmode.setTextColor(getResources().getColor(R.color.black));
                    darkmode.setBackgroundColor(getResources().getColor(R.color.white));
                }
            }
        });
    }
}