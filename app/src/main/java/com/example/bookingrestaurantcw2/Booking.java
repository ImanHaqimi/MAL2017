package com.example.bookingrestaurantcw2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Booking extends AppCompatActivity {

    private CalendarView calendarView;
    private CheckBox checkBoxBreakfast, checkBoxLunch, checkBoxDinner;
    private Button buttonChooseTable;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking);

        // Initialize Views
        calendarView = findViewById(R.id.calendarView);
        checkBoxBreakfast = findViewById(R.id.checkBoxBreakfast);
        checkBoxLunch = findViewById(R.id.checkBoxLunch);
        checkBoxDinner = findViewById(R.id.checkBoxDinner);
        buttonChooseTable = findViewById(R.id.buttonChooseTable);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        ImageView iconBack = findViewById(R.id.iconExit); // Back Icon

        // Set default selected date to an empty string
        selectedDate = "";

        // CalendarView Date Selection Listener
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                Toast.makeText(Booking.this, "Selected Date: " + selectedDate, Toast.LENGTH_SHORT).show();
            }
        });

        // Button Click Listener for choosing a table
        buttonChooseTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder selectedOptions = new StringBuilder("Selected Options:\n");

                if (checkBoxBreakfast.isChecked()) {
                    selectedOptions.append("- Breakfast\n");
                }
                if (checkBoxLunch.isChecked()) {
                    selectedOptions.append("- Lunch\n");
                }
                if (checkBoxDinner.isChecked()) {
                    selectedOptions.append("- Dinner\n");
                }

                if (selectedDate.isEmpty()) {
                    Toast.makeText(Booking.this, "Please select a date.", Toast.LENGTH_SHORT).show();
                } else if (selectedOptions.length() == 17) { // "Selected Options:\n" length
                    Toast.makeText(Booking.this, "Please select at least one meal.", Toast.LENGTH_SHORT).show();
                } else {
                    // Navigate to Table page
                    Intent intent = new Intent(Booking.this, Table.class);
                    intent.putExtra("date", selectedDate);
                    intent.putExtra("options", selectedOptions.toString());
                    startActivity(intent);
                }
            }
        });

        // Back Icon Click Listener
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to HomePage
                Intent intent = new Intent(Booking.this, HomePage.class);
                startActivity(intent);
                finish(); // Close current activity
            }
        });

        // Bottom Navigation Item Selection Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                // Navigate to Home page
                startActivity(new Intent(Booking.this, HomePage.class));
                return true;
            } else if (itemId == R.id.navigation_booking) {
                // Navigate to History page (Booking History)
                startActivity(new Intent(Booking.this, History.class));
                return true;
            } else if (itemId == R.id.navigation_profile) {
                // Navigate to Profile page
                startActivity(new Intent(Booking.this, Profile.class));
                return true;
            } else {
                return false;
            }
        });
    }
}
