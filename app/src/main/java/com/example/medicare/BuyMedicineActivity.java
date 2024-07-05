package com.example.medicare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class BuyMedicineActivity extends AppCompatActivity {

    private EditText medicineNameEditText;
    private Button buyButton, cartButton;
    private Database database;
    private ListView medicineListView;
    private String[] medicineNames = {
            "Paracetamol",
            "Dolo 50",
            "Ibuprofen",
            "Aspirin",
            "Amoxicillin",
            "Ciprofloxacin",
            "Lisinopril",
            "Atorvastatin",
            "Metformin",
            "Levothyroxine",
            "Albuterol",
            "Omeprazole",
            "Losartan",
            "Amlodipine",
            "Simvastatin",
            "Warfarin",
            "Prednisone",
            "Azithromycin",
            "Furosemide",
            "Metoprolol",
            "Hydrochlorothiazide", "Gabapentin",
            "Fluoxetine","Tramadol",
            "Citalopram","Sertraline",
            "Lorazepam","Propranolol",
            "Ranitidine","Trazodone"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        database = new Database(this);

        medicineNameEditText = findViewById(R.id.medicineNameEditText);
        buyButton = findViewById(R.id.buyButton);
        cartButton = findViewById(R.id.Cart);
         medicineListView = findViewById(R.id.medicineListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, medicineNames);

        medicineListView.setAdapter(adapter);

        medicineListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = medicineNames[position];
                medicineNameEditText.setText(selectedItem);
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String medicineName = medicineNameEditText.getText().toString().trim();
                if (!medicineName.isEmpty()) {
                    addToCartAndShowMessage(medicineName);
                } else {
                    Toast.makeText(BuyMedicineActivity.this, "Please enter medicine name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCartActivity();
            }
        });
    }

    private void addToCartAndShowMessage(String medicineName) {
        database.addToCart(medicineName);
        Toast.makeText(this, "Added to cart: " + medicineName, Toast.LENGTH_SHORT).show();
    }

    private void openCartActivity() {
        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}
