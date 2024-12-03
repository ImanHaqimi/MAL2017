package com.example.bookingrestaurantcw2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
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


public class Register extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText username, email, password, repassword;
    private TextView usernameError, emailError, passwordError, repasswordError;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        // Initialize the DatabaseHelper
        databaseHelper = new DatabaseHelper(this);

        // Find views
        username = findViewById(R.id.editTextUsername);
        email = findViewById(R.id.editTextEmail);
        password = findViewById(R.id.editTextPassword);
        repassword = findViewById(R.id.editTextRePassword);
        usernameError = findViewById(R.id.wrongUsername);
        emailError = findViewById(R.id.wrongEmail);
        passwordError = findViewById(R.id.wrongPassword);
        repasswordError = findViewById(R.id.wrongRePassword);

        // Register button click listener
        Button registerButton = findViewById(R.id.buttonRegister);
        Button loginButton = findViewById(R.id.buttonLogin);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reset error messages
                usernameError.setText("");
                emailError.setText("");
                passwordError.setText("");
                repasswordError.setText("");

                String uname = username.getText().toString();
                String uemail = email.getText().toString();
                String upass = password.getText().toString();
                String uphone = repassword.getText().toString();

                if (validateInput(uname, uemail, upass, uphone)) {
                    // Store user data into the local database
                    if (storeUserData(uname, uemail, upass, uphone)) {
                        // Show a toast message
                        Toast.makeText(Register.this, "Registration successful!", Toast.LENGTH_SHORT).show();

                        // Redirect to the login page
                        redirectToLoginPage();
                    } else {
                        // Show an error message if data already exists
                        Toast.makeText(Register.this, "Username, email, or phone already exists!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to navigate to the LoginActivity
                Intent intent = new Intent(Register.this, Login.class);

                // Start the LoginActivity
                startActivity(intent);
            }
        });
    }

    private boolean validateInput(String uname, String uemail, String upass, String urepassword) {
        boolean isValid = true;

        if (uname.isEmpty()) {
            usernameError.setText("Please enter your username");
            isValid = false;
        }

        if (uemail.isEmpty()) {
            emailError.setText("Please enter an email address");
            isValid = false;
        }

        if (upass.isEmpty()) {
            passwordError.setText("Cannot leave blank");
            isValid = false;
        }

        if (urepassword.isEmpty()) {
            repasswordError.setText("Cannot leave blank");
            isValid = false;
        }

        // Check if email, username, or phone already exists in the database
        if (isUserExists(uname)) {
            usernameError.setText("Username already exists!");
            isValid = false;
        }

        if (isEmailExists(uemail)) {
            emailError.setText("Email already exists!");
            isValid = false;
        }

        if (isPhoneExists(urepassword)) {
            repasswordError.setText("Phone already exists!");
            isValid = false;
        }

        return isValid;
    }

    private boolean isUserExists(String username) {
        // Get a readable database
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Define a projection that specifies which columns to query
        String[] projection = {DatabaseHelper.COLUMN_USERNAME};

        // Define the selection criteria
        String selection = DatabaseHelper.COLUMN_USERNAME + " = ? ";
        String[] selectionArgs = {username};

        // Perform the query
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_USER,   // The table to query
                projection,                  // The array of columns to return (null to return all)
                selection,                   // The columns for the WHERE clause
                selectionArgs,               // The values for the WHERE clause
                null,                        // don't group the rows
                null,                        // don't filter by row groups
                null                         // don't sort order
        );

        // Check if the cursor has any rows
        boolean userExists = cursor.moveToFirst();

        // Close the cursor and database
        cursor.close();
        db.close();

        return userExists;
    }

    private boolean isEmailExists(String email) {
        // Get a readable database
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Define a projection that specifies which columns to query
        String[] projection = {DatabaseHelper.COLUMN_EMAIL};

        // Define the selection criteria
        String selection = DatabaseHelper.COLUMN_EMAIL + " = ? ";
        String[] selectionArgs = {email};

        // Perform the query
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_USER,   // The table to query
                projection,                  // The array of columns to return (null to return all)
                selection,                   // The columns for the WHERE clause
                selectionArgs,               // The values for the WHERE clause
                null,                        // don't group the rows
                null,                        // don't filter by row groups
                null                         // don't sort order
        );

        // Check if the cursor has any rows
        boolean emailExists = cursor.moveToFirst();

        // Close the cursor and database
        cursor.close();
        db.close();

        return emailExists;
    }

    private boolean isPhoneExists(String phone) {
        // Get a readable database
        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        // Define a projection that specifies which columns to query
        String[] projection = {DatabaseHelper.COLUMN_REPASSWORD};

        // Define the selection criteria
        String selection = DatabaseHelper.COLUMN_REPASSWORD + " = ? ";
        String[] selectionArgs = {phone};

        // Perform the query
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_USER,   // The table to query
                projection,                  // The array of columns to return (null to return all)
                selection,                   // The columns for the WHERE clause
                selectionArgs,               // The values for the WHERE clause
                null,                        // don't group the rows
                null,                        // don't filter by row groups
                null                         // don't sort order
        );

        // Check if the cursor has any rows
        boolean phoneExists = cursor.moveToFirst();

        // Close the cursor and database
        cursor.close();
        db.close();

        return phoneExists;
    }

    private boolean storeUserData(String uname, String uemail, String upass, String urepassword) {
        // Get a writable database
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        // Create a ContentValues object to store data
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, uname);
        values.put(DatabaseHelper.COLUMN_EMAIL, uemail);
        values.put(DatabaseHelper.COLUMN_PASSWORD, upass);
        values.put(DatabaseHelper.COLUMN_REPASSWORD, urepassword);

        // Insert data into the "user" table
        long newRowId = db.insert(DatabaseHelper.TABLE_USER, null, values);

        // Close the database
        db.close();


        // Check if the insertion was successful
        return newRowId != -1;
    }

    private void redirectToLoginPage() {
        // Create an Intent to navigate to the LoginActivity
        Intent intent = new Intent(Register.this, Login.class);

        // Start the LoginActivity
        startActivity(intent);

        // Finish the current activity to prevent going back to the registration page
        finish();
    }

}

