package com.example.medicare;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class AppointmentsActivity extends AppCompatActivity {

    private ListView appointmentsListView;
    private Button clearButton;
    private ArrayAdapter<String> appointmentsAdapter;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

        appointmentsListView = findViewById(R.id.appointmentsListView);
        clearButton = findViewById(R.id.clear);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAppointments();
            }
        });

        appointmentsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        database = new Database(this);

        ArrayList<String> appointmentsList = getAppointmentsFromDatabase();
        appointmentsAdapter.addAll(appointmentsList);
        appointmentsListView.setAdapter(appointmentsAdapter);

        if (getIntent().hasExtra("selected_doctor")) {
            String selectedDoctor = getIntent().getStringExtra("selected_doctor");
            addAppointment(selectedDoctor);
        }
    }

    private void clearAppointments() {
        SQLiteDatabase db = database.getWritableDatabase();
        db.delete("appointments", null, null);
        db.close();

        appointmentsAdapter.clear();
        appointmentsAdapter.notifyDataSetChanged();
    }

    private void addAppointment(String doctorName) {
        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("appointment_name", doctorName);
        db.insert("appointments", null, values);
        db.close();

        appointmentsAdapter.add(doctorName);
        appointmentsAdapter.notifyDataSetChanged();
    }

    private ArrayList<String> getAppointmentsFromDatabase() {
        ArrayList<String> appointmentsList = new ArrayList<>();

        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT appointment_name FROM appointments", null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String appointmentName = cursor.getString(cursor.getColumnIndex("appointment_name"));
                appointmentsList.add(appointmentName);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return appointmentsList;
    }
}
