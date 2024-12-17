package com.example.bookingrestaurantcw2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class viewbooking extends AppCompatActivity {

    // Declare Views
    ImageView iconBack;
    Button modifyButton, cancelButton;
    TextView pendingId, pendingName, pendingMeal, pendingArea, pendingTable, pendingDate;
    TextView approvedId, approvedName, approvedMeal, approvedArea, approvedTable, approvedDate;
    BottomNavigationView bottomNavigationView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewbooking); // Connect to XML layout

        // Initialize Views
        iconBack = findViewById(R.id.iconBack);

        pendingId = findViewById(R.id.id);
        pendingName = findViewById(R.id.pending_name);
        pendingMeal = findViewById(R.id.pending_meal);
        pendingArea = findViewById(R.id.pending_area);
        pendingTable = findViewById(R.id.pending_table);
        pendingDate = findViewById(R.id.pending_date);

        approvedId = findViewById(R.id.approved_id);
        approvedName = findViewById(R.id.approved_name);
        approvedMeal = findViewById(R.id.approved_meal);
        approvedArea = findViewById(R.id.approved_area);
        approvedTable = findViewById(R.id.approved_table);
        approvedDate = findViewById(R.id.approved_date);

        modifyButton = findViewById(R.id.modify_button);
        cancelButton = findViewById(R.id.cancel_button); // Make sure the ID matches in the XML

        bottomNavigationView = findViewById(R.id.bottomNavigationView); // Initialize the BottomNavigationView

        // Set Click Listeners
        iconBack.setOnClickListener(view -> finish()); // Close Activity on Back Icon

        modifyButton.setOnClickListener(view -> modifyBooking());
        cancelButton.setOnClickListener(view -> cancelBooking());

        // Example: Load Booking Data
        loadPendingBookingData();
        loadApprovedBookingData();

        // Set Bottom Navigation Listener
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                // Navigate to Home page
                startActivity(new Intent(viewbooking.this, HomePage.class));
                return true;
            } else if (itemId == R.id.navigation_booking) {
                // Navigate to History page (Booking History)
                startActivity(new Intent(viewbooking.this, History.class));
                return true;
            } else if (itemId == R.id.navigation_profile) {
                // Navigate to Profile page
                startActivity(new Intent(viewbooking.this, Profile.class));
                return true;
            } else {
                return false;
            }
        });
    }

    // Function to Load Pending Booking Data
    private void loadPendingBookingData() {
        pendingId.setText("0");
        pendingName.setText("John Brown");
        pendingMeal.setText("Breakfast");
        pendingArea.setText("Outside");
        pendingTable.setText("5");
        pendingDate.setText("18/12/2024");
    }

    // Function to Load Approved Booking Data
    private void loadApprovedBookingData() {
        approvedId.setText("1");
        approvedName.setText("Helen Smith");
        approvedMeal.setText("Dinner");
        approvedArea.setText("Inside");
        approvedTable.setText("7");
        approvedDate.setText("20/12/2024");
    }

    // Function to Modify Booking
    private void modifyBooking() {
        // Add code to navigate to a "Modify Booking" screen or show a dialog
        // Example:
        // startActivity(new Intent(this, ModifyBookingActivity.class));
    }

    // Function to Cancel Booking
    private void cancelBooking() {
        // Add code to cancel the booking (e.g., show a confirmation dialog)
        // Example:
        // Toast.makeText(this, "Booking Cancelled", Toast.LENGTH_SHORT).show();
    }
}
