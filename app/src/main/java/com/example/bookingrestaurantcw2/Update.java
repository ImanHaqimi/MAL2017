package com.example.bookingrestaurantcw2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Update extends AppCompatActivity {
    private EditText customerIDEditText;
    private EditText customerNameEditText;
    private EditText mealtimeEditText;
    private EditText areaEditText;
    private EditText tablesizeEditText;
    private EditText dateEditText;
    private Button updateButton;

    private ReservationService apiService;
    private Reservation reservationDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update);

        // Initialize views
        customerIDEditText = findViewById(R.id.customerIDEditText);
        customerNameEditText = findViewById(R.id.customerNameEditText);
        mealtimeEditText = findViewById(R.id.mealtimeEditText);
        areaEditText = findViewById(R.id.areaEditText);
        tablesizeEditText = findViewById(R.id.tablesizeEditText);
        dateEditText = findViewById(R.id.dateEditText);
        updateButton = findViewById(R.id.updateButton);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://web.socem.plymouth.ac.uk/COMP2000/ReservationApi/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ReservationService.class);

        // Retrieve reservation details from intent
        if (getIntent().hasExtra("reservation")) {
            reservationDetails = (Reservation) getIntent().getSerializableExtra("reservation");
            populateFields(reservationDetails);
        }

        // Set click listener for update button
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateReservation();
            }
        });
    }

    private void populateFields(Reservation reservation) {
        customerIDEditText.setText(String.valueOf(reservation.getId())); // Display Customer ID
        customerNameEditText.setText(reservation.getCustomerName());
        mealtimeEditText.setText(reservation.getMeal());
        areaEditText.setText(reservation.getSeatingArea());
        tablesizeEditText.setText(String.valueOf(reservation.getTableSize()));
        dateEditText.setText(reservation.getDate());
    }

    private void updateReservation() {
        try {
            int customerID = Integer.parseInt(customerIDEditText.getText().toString().trim());
            String updatedName = customerNameEditText.getText().toString().trim();
            String updatedMealTime = mealtimeEditText.getText().toString().trim();
            String updatedArea = areaEditText.getText().toString().trim();
            int updatedTableSize = Integer.parseInt(tablesizeEditText.getText().toString().trim());
            String updatedDate = dateEditText.getText().toString().trim();

            // Validation check
            if (updatedName.isEmpty() || updatedMealTime.isEmpty() || updatedArea.isEmpty() || updatedDate.isEmpty()) {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Update reservation object
            Reservation updatedReservation = new Reservation();
            updatedReservation.setId(customerID);
            updatedReservation.setCustomerName(updatedName);
            updatedReservation.setMeal(updatedMealTime);
            updatedReservation.setSeatingArea(updatedArea);
            updatedReservation.setTableSize(updatedTableSize);
            updatedReservation.setDate(updatedDate);

            // API Call to update reservation
            Call<Void> call = apiService.updateHistoryItem(customerID, updatedReservation);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(Update.this, "Reservation updated successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Log.e("UpdateReservation", "Update failed: " + response.code());
                        Toast.makeText(Update.this, "Update failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("UpdateReservation", "Network Error", t);
                    Toast.makeText(Update.this, "Network error occurred", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid table size", Toast.LENGTH_SHORT).show();
        }
    }
}
