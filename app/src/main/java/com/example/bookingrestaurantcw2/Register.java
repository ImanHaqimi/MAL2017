package com.example.bookingrestaurantcw2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText username, email, phone, password;
    private TextView usernameError, emailError, phoneError, passwordError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        databaseHelper = new DatabaseHelper(this);

        // Initialize Views
        username = findViewById(R.id.editTextUsername);
        email = findViewById(R.id.editTextEmail);
        phone = findViewById(R.id.editTextPhone);
        password = findViewById(R.id.editTextPassword);
        usernameError = findViewById(R.id.wrongUsername);
        emailError = findViewById(R.id.wrongEmail);
        phoneError = findViewById(R.id.wrongPhone);
        passwordError = findViewById(R.id.wrongPassword);

        Button registerButton = findViewById(R.id.buttonRegister);
        Button loginButton = findViewById(R.id.buttonLogin);

        registerButton.setOnClickListener(v -> {
            clearErrorMessages();

            String uname = username.getText().toString().trim();
            String uemail = email.getText().toString().trim();
            String uphone = phone.getText().toString().trim();
            String upass = password.getText().toString().trim();

            if (validateInput(uname, uemail, uphone, upass)) {
                if (storeUserData(uname, uemail, uphone, upass)) {
                    Toast.makeText(Register.this, "Registration successful!", Toast.LENGTH_SHORT).show();
                    redirectToLoginPage();
                } else {
                    Toast.makeText(Register.this, "Username or email already exists!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        loginButton.setOnClickListener(view -> {
            Intent intent = new Intent(Register.this, Login.class);
            startActivity(intent);
        });
    }

    private void clearErrorMessages() {
        usernameError.setText("");
        emailError.setText("");
        phoneError.setText("");
        passwordError.setText("");
    }

    private boolean validateInput(String uname, String uemail, String uphone, String upass) {
        boolean isValid = true;

        if (uname.isEmpty()) {
            usernameError.setText("Please enter a username");
            isValid = false;
        }

        if (uemail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(uemail).matches()) {
            emailError.setText("Please enter a valid email address");
            isValid = false;
        }

        if (uphone.isEmpty() || !uphone.matches("\\d{10}")) {
            phoneError.setText("Please enter a valid 10-digit phone number");
            isValid = false;
        }

        if (upass.isEmpty()) {
            passwordError.setText("Password cannot be blank");
            isValid = false;
        }

        if (isUserExists(uname)) {
            usernameError.setText("Username already exists");
            isValid = false;
        }

        if (isEmailExists(uemail)) {
            emailError.setText("Email already exists");
            isValid = false;
        }

        return isValid;
    }

    private boolean isUserExists(String username) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_USER,
                new String[]{DatabaseHelper.COLUMN_USERNAME},
                DatabaseHelper.COLUMN_USERNAME + " = ?",
                new String[]{username}, null, null, null);

        boolean exists = cursor.moveToFirst();
        cursor.close();
        db.close();
        return exists;
    }

    private boolean isEmailExists(String email) {
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_USER,
                new String[]{DatabaseHelper.COLUMN_EMAIL},
                DatabaseHelper.COLUMN_EMAIL + " = ?",
                new String[]{email}, null, null, null);

        boolean exists = cursor.moveToFirst();
        cursor.close();
        db.close();
        return exists;
    }

    private boolean storeUserData(String uname, String uemail, String uphone, String upass) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, uname);
        values.put(DatabaseHelper.COLUMN_EMAIL, uemail);
        values.put(DatabaseHelper.COLUMN_PHONE, uphone);
        values.put(DatabaseHelper.COLUMN_PASSWORD, upass);

        long rowId = db.insert(DatabaseHelper.TABLE_USER, null, values);
        db.close();
        return rowId != -1;
    }

    private void redirectToLoginPage() {
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
        finish();
    }
}
