package com.example.bookingrestaurantcw2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class Receipt extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receipt);

        // Manually set reservation details
        String customerName = "John Brown";
        String meal = "Breakfast";
        String seatingArea = "Outside";
        int tableSize = 5;
        String date = "18/12/2024";
        int id = 0;

        // Set the values in the corresponding TextViews
        setTextView(R.id.name, customerName);
        setTextView(R.id.id, String.valueOf(id));
        setTextView(R.id.meal, meal);
        setTextView(R.id.area_label, seatingArea);
        setTextView(R.id.size, String.valueOf(tableSize));
        setTextView(R.id.date, date);
        setTextView(R.id.cancellation_policy_flexible, "Flexible");

        // Buttons setup
        Button backToMenuButton = findViewById(R.id.home1);
        Button backToMenuButton1 = findViewById(R.id.home2);
        Button historyButton = findViewById(R.id.history);

        backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToHome();
            }
        });

        backToMenuButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToHome();
            }
        });

        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyIntent = new Intent(Receipt.this, viewbooking.class);
                startActivity(historyIntent);
            }
        });
    }

    // Utility method for setting TextView text
    private void setTextView(int viewId, String value) {
        TextView textView = findViewById(viewId);
        if (textView != null) {
            textView.setText(value);
        }
    }

    // Navigation back to MainActivity
    private void navigateToHome() {
        Intent intent = new Intent(Receipt.this, HomePage.class);
        startActivity(intent);
        finish();
    }
}
