package com.example.bookingrestaurantcw2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText usernameEditText, passwordEditText;
    private TextView usernameError, passwordError, forgotPasswordTextView, signupTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Initialize DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Initialize UI Elements
        usernameEditText = findViewById(R.id.editTextUsername);
        passwordEditText = findViewById(R.id.editTextPassword);
        usernameError = findViewById(R.id.wrongUsername);
        passwordError = findViewById(R.id.wrongPassword);

        Button loginButton = findViewById(R.id.buttonLogin);
        forgotPasswordTextView = findViewById(R.id.textViewForgotPassword);
        signupTextView = findViewById(R.id.textViewSignup);

        // Handle Login Button Click
        loginButton.setOnClickListener(this::onClick);

        // Handle Forgot Password Text Click
        forgotPasswordTextView.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });

        // Handle Sign Up Text Click
        signupTextView.setOnClickListener(view -> {
            Intent intent = new Intent(Login.this, Register.class);
            startActivity(intent);
        });
    }

    private boolean validateInput(String username, String password) {
        boolean isValid = true;

        if (username.isEmpty()) {
            usernameError.setText(getString(R.string.invalid_username));
            isValid = false;
        }

        if (password.isEmpty()) {
            passwordError.setText(getString(R.string.error_empty_password));
            isValid = false;
        }

        return isValid;
    }

    private boolean authenticateUser(String username, String password) {
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = databaseHelper.getReadableDatabase();

            String selection = DatabaseHelper.COLUMN_USERNAME + " = ? AND " + DatabaseHelper.COLUMN_PASSWORD + " = ?";
            String[] selectionArgs = {username, password};

            cursor = db.query(DatabaseHelper.TABLE_USER, null, selection, selectionArgs, null, null, null);

            return cursor.getCount() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;

        } finally {
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }
    }

    private User getUserDetails(String username) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        User userDetails = new User();

        try {
            db = databaseHelper.getReadableDatabase();

            String selection = DatabaseHelper.COLUMN_USERNAME + " = ?";
            String[] selectionArgs = {username};

            cursor = db.query(
                    DatabaseHelper.TABLE_USER,
                    new String[]{DatabaseHelper.COLUMN_USERNAME, DatabaseHelper.COLUMN_EMAIL,},
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            if (cursor.moveToFirst()) {
                userDetails.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_USERNAME)));
                userDetails.setEmail(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_EMAIL)));
            }
        } finally {
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }

        return userDetails;
    }

    private void onClick(View view) {
        usernameError.setText("");
        passwordError.setText("");

        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (validateInput(username, password)) {
            if (authenticateUser(username, password)) {
                Toast.makeText(this, getString(R.string.login_successful), Toast.LENGTH_SHORT).show();

                User userDetails = getUserDetails(username);

                Intent intent = new Intent(Login.this, HomePage.class);
                intent.putExtra("userDetails", userDetails);

                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, getString(R.string.invalid_credentials), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
