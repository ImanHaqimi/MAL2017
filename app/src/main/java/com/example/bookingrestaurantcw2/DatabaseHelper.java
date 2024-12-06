package com.example.bookingrestaurantcw2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "reservationdb2";
    private static final int DATABASE_VERSION = 2;  // Increment for schema updates

    // Table and column names
    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID = "id";                 // User ID (Primary Key)
    public static final String COLUMN_USERNAME = "username";     // Username
    public static final String COLUMN_EMAIL = "email";           // Email Address
    public static final String COLUMN_PHONE = "phone";           // User Phone Number
    public static final String COLUMN_PASSWORD = "password";     // User Password

    // Create table SQL query
    private static final String CREATE_USER_TABLE =
            "CREATE TABLE " + TABLE_USER + " ("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_USERNAME + " TEXT UNIQUE NOT NULL, "
                    + COLUMN_EMAIL + " TEXT UNIQUE NOT NULL, "
                    + COLUMN_PHONE + " TEXT UNIQUE NOT NULL, "
                    + COLUMN_PASSWORD + " TEXT NOT NULL"
                    + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the user table
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            // Schema change: Adding the COLUMN_PHONE during upgrade
            db.execSQL("ALTER TABLE " + TABLE_USER + " ADD COLUMN " + COLUMN_PHONE + " TEXT UNIQUE NOT NULL");
        }
    }
}
