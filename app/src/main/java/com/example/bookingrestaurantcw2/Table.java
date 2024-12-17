package com.example.bookingrestaurantcw2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Table extends AppCompatActivity {
    private CardView cardView;
    private Spinner spinnerNumberOfPax;
    private Button nextButton;
    private Button buttonG01, buttonS03, buttonI02;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);

        // Initialize views
        cardView = findViewById(R.id.cardView);
        spinnerNumberOfPax = findViewById(R.id.spinnerNumberOfPax);
        nextButton = findViewById(R.id.nextButton);
        buttonG01 = findViewById(R.id.button3);
        buttonS03 = findViewById(R.id.button2);
        buttonI02 = findViewById(R.id.button4);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set up back button functionality
        findViewById(R.id.iconBack).setOnClickListener(v -> onBackPressed());

        // Set up table selection buttons
        buttonG01.setOnClickListener(v -> handleTableSelection("G01"));
        buttonS03.setOnClickListener(v -> handleTableSelection("S03"));
        buttonI02.setOnClickListener(v -> handleTableSelection("I02"));

        // Handle next button click (confirm booking)
        nextButton.setOnClickListener(v -> {
            String pax = spinnerNumberOfPax.getSelectedItem().toString();
            Toast.makeText(Table.this, "Booking confirmed for " + pax + " pax.", Toast.LENGTH_SHORT).show();

            // Start the ReceiptActivity (Receipt page)
            Intent intent = new Intent(Table.this, Receipt.class);
            startActivity(intent); // This will navigate to the ReceiptActivity
        });

        // Set up bottom navigation listener (optional)
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                // Navigate to Home page
                startActivity(new Intent(Table.this, HomePage.class));
                return true;
            } else if (itemId == R.id.navigation_booking) {
                // Navigate to History page (Booking History)
                startActivity(new Intent(Table.this, History.class));
                return true;
            } else if (itemId == R.id.navigation_profile) {
                // Navigate to Profile page
                startActivity(new Intent(Table.this, Profile.class));
                return true;
            } else {
                return false;
            }
        });
    }

    private void handleTableSelection(String table) {
        // Logic for table selection (e.g., highlight selected table, save selection, etc.)
        Toast.makeText(this, "Table " + table + " selected", Toast.LENGTH_SHORT).show();
        // You can update UI or store the selected table here.
    }
}
