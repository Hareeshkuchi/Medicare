package com.example.medicare;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;

public class DoctorsActivity extends AppCompatActivity {

    private ListView doctorsListView;
    private ArrayAdapter<String> doctorsAdapter;
    private SearchView searchView;
    private Database database;
    private ArrayList<String> originalDoctorsList;
    private String[] doctorsnames = {
            "Dr. hareesh(Ophthalmologist)",
            "Dr. nimitha(Anesthesiologist)",
            "Dr. vaseem(Surgeon)",
            "Dr. pandu(Nurse)",
            "Dr. bablu(Neurologist)",
            "Dr. gokul(Orthopedic Surgeon)",
            "Dr. ravi(Pediatrician)",
            "Dr. praveen(Dermatologist)",
            "Dr. kishore(General Practitioner)",
            "Dr. bharat(cardialagist)",
             };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);


        doctorsListView = findViewById(R.id.doctorsListView);
        searchView = findViewById(R.id.searchView);



        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, doctorsnames);

        doctorsListView.setAdapter(adapter);
        doctorsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String selectedDoctor = doctorsnames[position];

                AlertDialog.Builder builder = new AlertDialog.Builder(DoctorsActivity.this);
                builder.setTitle("Make an Appointment")
                        .setMessage("Do you want to make an appointment with " + selectedDoctor + "?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Add the selectedDoctor to appointments
                                handleAppointment(selectedDoctor);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

    }

    private void handleAppointment(String doctorName) {
        Intent intent = new Intent(DoctorsActivity.this, AppointmentsActivity.class);
        intent.putExtra("selected_doctor", doctorName);
        startActivity(intent);
    }





}
