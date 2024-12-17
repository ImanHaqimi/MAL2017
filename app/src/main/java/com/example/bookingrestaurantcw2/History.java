package com.example.bookingrestaurantcw2;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history); // Replace with your layout file name

        // Initialize Views
        ImageView iconBack = findViewById(R.id.iconBack);
        TextView header = findViewById(R.id.header);
        TextView subtitle = findViewById(R.id.subtitle);
        Button homeButton = findViewById(R.id.home2);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Example Card Details: Approved Booking
        TextView approvedId = findViewById(R.id.approved_id);
        TextView approvedName = findViewById(R.id.approved_name);
        TextView approvedMeal = findViewById(R.id.approved_meal);
        TextView approvedArea = findViewById(R.id.approved_area);
        TextView approvedTable = findViewById(R.id.approved_table);
        TextView approvedDate = findViewById(R.id.approved_date);

        // Back Button Logic
        iconBack.setOnClickListener(v -> finish()); // Goes back to the previous activity

        // Return to Homepage Button
        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(History.this, HomePage.class); // Replace with your Homepage Activity
            startActivity(intent);
        });

        // Set Bottom Navigation Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                // Navigate to Home page
                startActivity(new Intent(History.this, HomePage.class));
                return true;
            } else if (itemId == R.id.navigation_booking) {
                // Navigate to History page (Booking History)
                startActivity(new Intent(History.this, History.class));
                return true;
            } else if (itemId == R.id.navigation_profile) {
                // Navigate to Profile page
                startActivity(new Intent(History.this, Profile.class));
                return true;
            } else {
                return false;
            }
        });

        // Example Data for Approved Card
        approvedId.setText("1234");
        approvedName.setText("John Doe");
        approvedMeal.setText("Lunch");
        approvedArea.setText("Hall A");
        approvedTable.setText("4-Seater");
        approvedDate.setText("12th June 2024");

        // Similar setup for Pending Card if needed
    }
}
