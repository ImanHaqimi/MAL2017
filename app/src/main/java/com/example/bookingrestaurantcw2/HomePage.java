package com.example.bookingrestaurantcw2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        // Initialize UI components
        ImageView iconExit = findViewById(R.id.iconExit);
        TextView textWelcome = findViewById(R.id.textWelcome);
        TextView textSubtitle = findViewById(R.id.textSubtitle);
        EditText editTextSearch = findViewById(R.id.editTextSearch);
        Button buttonMenu = findViewById(R.id.buttonMenu);
        Button buttonBookNow = findViewById(R.id.buttonBookNow);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Set Welcome Text
        textWelcome.setText("Welcome Back!!");
        textSubtitle.setText("Book your table now");

        // Set up Exit button click listener
        iconExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePage.this, "Exiting app", Toast.LENGTH_SHORT).show();
                finish(); // Close the activity
            }
        });

        // Set up Menu button click listener
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePage.this, "Opening Menu...", Toast.LENGTH_SHORT).show();
                // Example: Navigate to MenuActivity
                // startActivity(new Intent(HomePage.this, MenuActivity.class));
            }
        });

        // Set up Book Now button click listener
        buttonBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePage.this, "Booking your table...", Toast.LENGTH_SHORT).show();
                // Example: Navigate to BookingActivity
                // startActivity(new Intent(HomePage.this, BookingActivity.class));
            }
        });

        // Handle BottomNavigationView item selection
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            Toast.makeText(HomePage.this, "Home selected", Toast.LENGTH_SHORT).show();
                            return true;
                        case R.id.navigation_booking:
                            Toast.makeText(HomePage.this, "Bookings selected", Toast.LENGTH_SHORT).show();
                            // Example: Navigate to BookingsActivity
                            // startActivity(new Intent(HomePage.this, BookingsActivity.class));
                            return true;
                        case R.id.navigation_profile:
                            Toast.makeText(HomePage.this, "Profile selected", Toast.LENGTH_SHORT).show();
                            // Example: Navigate to ProfileActivity
                            // startActivity(new Intent(HomePage.this, ProfileActivity.class));
                            return true;
                        default:
                            return false;
                    }
                }
        );

        // Example: Search functionality when the user types something
        editTextSearch.setOnEditorActionListener((v, actionId, event) -> {
            String query = editTextSearch.getText().toString();
            if (!query.isEmpty()) {
                Toast.makeText(HomePage.this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
                // Perform search operation
                return true;
            }
            return false;
        });
    }
}
