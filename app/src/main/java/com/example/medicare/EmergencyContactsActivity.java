package com.example.medicare;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.util.ArrayList;

public class EmergencyContactsActivity extends AppCompatActivity {


    private ListView emergencyContactsListView;
    private ArrayAdapter<String> emergencyContactsAdapter;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contacts);

        database = new Database(this); // Replace "medicare_db" with your database name

        emergencyContactsListView = findViewById(R.id.emergencyContactsListView);
        emergencyContactsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);


        ArrayList<String> emergencyContactsList = getEmergencyContactsFromDatabase();
        emergencyContactsAdapter.addAll(emergencyContactsList);

        emergencyContactsListView.setAdapter(emergencyContactsAdapter);



    }


    private ArrayList<String> getEmergencyContactsFromDatabase() {
        ArrayList<String> emergencyContactsList = new ArrayList<>();
        // Assuming you have a table "emergency_contacts" with a column "contact_number" that stores contact numbers
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT contact_number FROM emergency_contacts", null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String contactNumber = cursor.getString(cursor.getColumnIndex("contact_number"));
                emergencyContactsList.add(contactNumber);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return emergencyContactsList;
    }


    }

