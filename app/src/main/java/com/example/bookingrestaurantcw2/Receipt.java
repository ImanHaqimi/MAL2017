package com.example.bookingrestaurantcw2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Receipt extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.managebooking1);

        // Retrieve reservation and user details from the intent
        Intent intent = getIntent();
        Reservation reservation = (intent != null) ? (Reservation) intent.getSerializableExtra("reservation") : null;
        User userDetails = (intent != null) ? (User) intent.getSerializableExtra("userDetails") : null;

        // Display reservation details in TextViews
        if (reservation != null && userDetails != null) {
            ((TextView) findViewById(R.id.name)).setText(userDetails.getUsername());
            ((TextView) findViewById(R.id.id)).setText(String.valueOf(userDetails.getId()));
            ((TextView) findViewById(R.id.time)).setText(reservation.getMeal());
            ((TextView) findViewById(R.id.meal)).setText(reservation.getSeatingArea());
            ((TextView) findViewById(R.id.size)).setText(String.valueOf(reservation.getTableSize()));
            ((TextView) findViewById(R.id.date)).setText(reservation.getDate());
        }

        // Back to Homepage Button (Top Button)
        findViewById(R.id.home1).setOnClickListener(v -> navigateToHomepage(userDetails));

        // Back to Homepage Button (Bottom Button)
        findViewById(R.id.home2).setOnClickListener(v -> navigateToHomepage(userDetails));

        // View Booking History Button
        findViewById(R.id.history).setOnClickListener(v -> {
            Intent historyIntent = new Intent(Receipt.this, viewbooking.class);
            if (userDetails != null) {
                historyIntent.putExtra("userDetails", userDetails);
            }
            startActivity(historyIntent);
        });
    }

    /**
     * Navigate to the Homepage.
     *
     * @param userDetails The user details to pass to the next activity.
     */
    private void navigateToHomepage(User userDetails) {
        Intent mainIntent = new Intent(Receipt.this, HomePage.class);
        if (userDetails != null) {
            mainIntent.putExtra("userDetails", userDetails);
        }
        startActivity(mainIntent);
        finish();
    }
}
