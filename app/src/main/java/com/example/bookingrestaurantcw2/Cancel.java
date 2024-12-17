package com.example.bookingrestaurantcw2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Cancel extends AppCompatActivity {

    // UI Components
    private ImageView iconBack;
    private TextView canceledTitle, id, name, meal, area, tableSize, date;
    private TextView cancelStatus;
    private Button returnHomeButton;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cancel); // Inflate the XML layout file

        // Initialize UI components
        iconBack = findViewById(R.id.iconBack);
        canceledTitle = findViewById(R.id.canceled_title);
        id = findViewById(R.id.id);
        name = findViewById(R.id.pending_name);
        meal = findViewById(R.id.pending_meal);
        area = findViewById(R.id.pending_area);
        tableSize = findViewById(R.id.pending_table);
        date = findViewById(R.id.pending_date);
        cancelStatus = findViewById(R.id.cancel);
        returnHomeButton = findViewById(R.id.home2);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Back button functionality: Navigate to ViewBooking activity
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ViewBooking activity
                Intent intent = new Intent(Cancel.this, viewbooking.class);  // Navigate to ViewBooking
                startActivity(intent);
                finish();  // Optional: finish the current activity to remove it from the stack
            }
        });

        // Return to Home button functionality
        returnHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the home page
                Intent intent = new Intent(Cancel.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        });

        // Example: Setting some dynamic data (you can modify this logic to fetch real data)
        id.setText("12345");
        name.setText("John Doe");
        meal.setText("Dinner");
        area.setText("Hall A");
        tableSize.setText("4");
        date.setText("2024-06-15");

        // Bottom Navigation Menu functionality
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                // Navigate to Home page
                startActivity(new Intent(Cancel.this, HomePage.class));
                return true;
            } else if (itemId == R.id.navigation_booking) {
                // Navigate to History page (Booking History)
                startActivity(new Intent(Cancel.this, History.class));
                return true;
            } else if (itemId == R.id.navigation_profile) {
                // Navigate to Profile page
                startActivity(new Intent(Cancel.this, Profile.class));
                return true;
            } else {
                return false;
            }
        });
    }
}
