package com.example.bookingrestaurantcw2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bookingrestaurantcw2.R;

public class MainActivity extends AppCompatActivity {

    // Declare your views
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewForgotPassword;
    private TextView textViewSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);  // Replace with your layout name if different

        // Initialize views
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword);
        textViewSignup = findViewById(R.id.textViewSignup);

        // Set up listeners for the buttons and text views
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLogin();
            }
        });

        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleForgotPassword();
            }
        });

        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSignUp();
            }
        });
    }

    private void handleLogin() {
        // Get the values from the username and password fields
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        // Simple validation
        if (username.isEmpty()) {
            showToast("Username is required");
            return;
        }

        if (password.isEmpty()) {
            showToast("Password is required");
            return;
        }

        // Handle login (For now, we'll just show a success message)
        showToast("Login Successful");

        // You can replace this with real login logic like API calls or database checks
    }

    private void handleForgotPassword() {
        // For now, show a toast. You can add logic to handle password recovery here
        showToast("Password recovery feature coming soon!");
    }

    private void handleSignUp() {
        // For now, show a toast. You can add logic to navigate to the sign-up screen
        showToast("Sign-up feature coming soon!");
    }

    // Helper function to show a toast
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
