package com.example.medicare;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private ListView cartListView;
    private Button clearAllButton;
    private Button buyAllButton;

    private ArrayList<String> cartItems;
    private ArrayAdapter<String> cartAdapter;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartListView = findViewById(R.id.cartListView);
        clearAllButton = findViewById(R.id.clearAllButton);
        buyAllButton = findViewById(R.id.buyAllButton);

        cartItems = new ArrayList<>();
        cartAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cartItems);
        cartListView.setAdapter(cartAdapter);

        database = new Database(this); // Use the correct constructor here

        loadCartItems();

        clearAllButton.setOnClickListener(v -> showAlertWithTitleBar());
        buyAllButton.setOnClickListener(v -> buyAllItems());

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("MEDICINE_NAME")) {
            String medicineName = intent.getStringExtra("MEDICINE_NAME");
            if (medicineName != null && !medicineName.isEmpty()) {
                addToCartList(medicineName);
            }
        }
    }

    private void loadCartItems() {
        SQLiteDatabase db = database.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM cart", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String medicineName = cursor.getString(cursor.getColumnIndex("medicine_name"));
                cartItems.add(medicineName);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
    }

public void showAlertWithTitleBar() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setIcon(android.R.drawable.ic_dialog_alert);
    builder.setTitle("Erase all cart items??");
    builder.setMessage("You'll lose all items added to the cart!");

    builder.setPositiveButton("Erase", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            clearAllItems();
        }
    });

    builder.setNegativeButton("Cancel", null);

    builder.create().show();
}


    private void clearAllItems() {
        cartItems.clear();
        cartAdapter.notifyDataSetChanged();

        SQLiteDatabase db = database.getWritableDatabase();
        db.delete("cart", null, null);
        db.close();
    }

    private void buyAllItems() {
        startActivity(new Intent(CartActivity.this, payment.class));
    }

    private void addToCartList(String medicineName) {
        cartItems.add(medicineName);
        cartAdapter.notifyDataSetChanged();

        SQLiteDatabase db = database.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("medicine_name", medicineName);
        db.insert("cart", null, values);
        db.close();
    }
}
