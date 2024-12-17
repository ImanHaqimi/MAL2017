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

        // Get the passed User object
        User user = (User) getIntent().getSerializableExtra("USER");

        // Initialize UI components
        ImageView iconExit = findViewById(R.id.iconExit);
        TextView textWelcome = findViewById(R.id.textWelcome);
        TextView textSubtitle = findViewById(R.id.textSubtitle);
        EditText editTextSearch = findViewById(R.id.editTextSearch);
        Button buttonMenu = findViewById(R.id.buttonMenu);
        Button buttonBookNow = findViewById(R.id.buttonBookNow);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Check if the user object is not null
        if (user != null) {
            // Set a personalized welcome message
            textWelcome.setText("Welcome Back, " + user.getUsername() + "!");
        }

        // Set Subtitle
        textSubtitle.setText("Book your table now");

        // Set up Exit button click listener
        iconExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to LoginActivity
                Intent intent = new Intent(HomePage.this, Login.class);
                startActivity(intent);
                finish(); // Close the current activity
            }
        });

        // Set up Menu button click listener
        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomePage.this, "Booking your table...", Toast.LENGTH_SHORT).show();
                // Navigate to BookingActivity (Booking Page)
                Intent intent = new Intent(HomePage.this, Booking.class);
                startActivity(intent);
            }
        });

        // Set up Book Now button click listener
        buttonBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Debugging Toast
                Toast.makeText(HomePage.this, "Navigating to Booking page...", Toast.LENGTH_SHORT).show();
                // Navigate to BookingActivity (Booking Page)
                Intent intent = new Intent(HomePage.this, Booking.class);
                startActivity(intent);
            }
        });

        // Handle BottomNavigationView item selection
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.navigation_home) {
                // Navigate to HomePage
                Intent intent = new Intent(HomePage.this, HomePage.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.navigation_booking) {
                // Navigate to HistoryActivity
                Intent intent = new Intent(HomePage.this, History.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.navigation_profile) {
                // Navigate to ProfileActivity
                Intent intent = new Intent(HomePage.this, Profile.class);
                startActivity(intent);
                return true;
            } else {
                return false;
            }
        });

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
