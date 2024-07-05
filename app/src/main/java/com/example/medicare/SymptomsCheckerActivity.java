package com.example.medicare;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SymptomsCheckerActivity extends AppCompatActivity {

    private EditText symptomsEditText;
    private Button checkSymptomsButton;
    private TextView resultTextView;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms_checker);

        database = new Database(this);
        symptomsEditText = findViewById(R.id.symptomsEditText);
        checkSymptomsButton = findViewById(R.id.checkSymptomsButton);
        resultTextView = findViewById(R.id.resultTextView);

        checkSymptomsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String symptomsInput = symptomsEditText.getText().toString().trim();
                int result = checkSymptoms(symptomsInput);

                if (result == 1) {
                    resultTextView.setText("Based on your symptoms, you might have Fever.");
                } else {
                    resultTextView.setText("Your symptoms do not match any known conditions.");
                }
            }
        });
    }


    private int checkSymptoms(String symptomsInput) {

        if (symptomsInput.toLowerCase().contains("fever")) {
            return 1;
        } else {
            return 0;
        }
    }
}
