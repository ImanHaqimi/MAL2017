package com.example.bookingrestaurantcw2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.concurrent.atomic.AtomicReference;

public class Profile extends AppCompatActivity {

    private ImageView imageView;
    private TextView userNameTextView;
    private Button signOutButton, myBookingsButton, feedbackButton;
    private Switch notificationSwitch;
    private BottomNavigationView bottomNavigationView;
    private User user; // Declare the User object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile); // Ensure your XML file is named correctly

        // Get the passed User object from Intent
        AtomicReference<Intent> intent = new AtomicReference<>(getIntent());
        if (intent.get() != null && intent.get().hasExtra("userDetails")) {
            user = (User) intent.get().getSerializableExtra("userDetails");
        }

        // Initialize views
        imageView = findViewById(R.id.imageView5);
        userNameTextView = findViewById(R.id.user);
        signOutButton = findViewById(R.id.button);
        myBookingsButton = findViewById(R.id.buttonMyBookings);
        feedbackButton = findViewById(R.id.buttonFeedback);
        notificationSwitch = findViewById(R.id.switch1);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        // Check if the user object is not null and update the UI
        if (user != null) {
            userNameTextView.setText(user.getUsername()); // Display the user's name
        }

        // Handle button clicks
        signOutButton.setOnClickListener(v -> {
            Toast.makeText(Profile.this, "Signed Out", Toast.LENGTH_SHORT).show();
            Intent intentSignOut = new Intent(Profile.this, Login.class);
            startActivity(intentSignOut);
            finish(); // Close the current activity
        });

        myBookingsButton.setOnClickListener(v -> {
            intent.set(new Intent(Profile.this, History.class));
            startActivity(intent.get());
        });

        notificationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(Profile.this, "Notifications Enabled", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Profile.this, "Notifications Disabled", Toast.LENGTH_SHORT).show();
            }
        });

        // BottomNavigationView item selection
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                startActivity(new Intent(Profile.this, HomePage.class));
                return true;
            } else if (itemId == R.id.navigation_booking) {
                startActivity(new Intent(Profile.this, History.class));
                return true;
            } else if (itemId == R.id.navigation_profile) {
                Toast.makeText(Profile.this, "Already on Profile", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                return false;
            }
        });
    }
}
