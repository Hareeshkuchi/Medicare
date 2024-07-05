package com.example.medicare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "medicare_db";
    public static final int DATABASE_VERSION = 2;

    public static final String TABLE_USERS = "users";
    public static final String TABLE_CART = "cart";
    public static final String TABLE_DOCTORS = "doctors";
    public static final String COLUMN_DOCTOR_NAME = "doctor_name";
    public static final String TABLE_APPOINTMENTS = "appointments";
    public static final String COLUMN_MEDICINE_NAME = "medicine_name";
    public static final String COLUMN_APPOINTMENT_NAME = "appointment_name";

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUsersTable = "CREATE TABLE " + TABLE_USERS +
                " (phone TEXT, username TEXT, email TEXT, password TEXT)";
        db.execSQL(createUsersTable);

        String createCartTable = "CREATE TABLE " + TABLE_CART +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_MEDICINE_NAME + " TEXT)";
        db.execSQL(createCartTable);

        String createDoctorsTable = "CREATE TABLE " + TABLE_DOCTORS +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DOCTOR_NAME + " TEXT)";
        db.execSQL(createDoctorsTable);


        String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_APPOINTMENTS +
                " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_APPOINTMENT_NAME + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if (oldVersion < newVersion) {

            db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_DOCTORS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPOINTMENTS);


            onCreate(db);
        }
    }public void signup(String phone, String email, String username, String password) {
        ContentValues cv = new ContentValues();
        cv.put("phone", phone);
        cv.put("email", email);
        cv.put("username", username);
        cv.put("password", password);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USERS, null, cv);
        db.close();
    }

    public int login(String username, String password) {
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = password;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE username=? AND password=?", str);
        if (c.moveToFirst()) {
            result = 1;
        }
        c.close();
        db.close();
        return result;
    }

    public void addToCart(String medicineName) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_MEDICINE_NAME, medicineName);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_CART, null, cv);
        db.close();
    }
    /*public void addDoctor(String doctorName) {
        ContentValues cv = new ContentValues();
        cv.put("name", doctorName);
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_DOCTORS, null, cv);
        db.close();
    }
*/

}
