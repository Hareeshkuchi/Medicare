package com.example.medicare;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MedicalRecordsActivity extends AppCompatActivity {

    private ListView medicalRecordsListView;
    private ArrayAdapter<String> medicalRecordsAdapter;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medical_records);

        medicalRecordsListView = findViewById(R.id.medicalRecordsListView); // Replace with the actual ListView ID from your XML
        medicalRecordsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        database = new Database(this);


        ArrayList<String> medicalRecordsList = getMedicalRecordsFromDatabase();
        medicalRecordsAdapter.addAll(medicalRecordsList);

        medicalRecordsListView.setAdapter(medicalRecordsAdapter);
    }

    // Method to fetch medical records from the database
    private ArrayList<String> getMedicalRecordsFromDatabase() {
        ArrayList<String> medicalRecordsList = new ArrayList<>();

        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT record_name FROM medical_records", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String recordName = cursor.getString(cursor.getColumnIndex("record_name"));
                medicalRecordsList.add(recordName);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return medicalRecordsList;
    }
}
