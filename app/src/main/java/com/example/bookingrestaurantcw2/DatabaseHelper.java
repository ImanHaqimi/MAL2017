package com.example.bookingrestaurantcw2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database constants
    private static final String DATABASE_NAME = "reservationdb2"; // Database name
    private static final int DATABASE_VERSION = 3;               // Incremented to 3 for schema updates

    // Table and column names
    public static final String TABLE_USER = "user";
    public static final String COLUMN_ID = "id";                 // User ID (Primary Key)
    public static final String COLUMN_USERNAME = "username";     // Username
    public static final String COLUMN_EMAIL = "email";           // Email Address
    public static final String COLUMN_PHONE = "phone";           // User Phone Number
    public static final String COLUMN_PASSWORD = "password";     // User Password

    // SQL query to create the user table
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
        Log.d("DatabaseHelper", "Database created with table: " + TABLE_USER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("DatabaseHelper", "Upgrading database from version " + oldVersion + " to " + newVersion);
        // Drop and recreate the table to ensure a clean schema
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

    /**
     * Logs the current schema for debugging purposes.
     */
    public void logDatabaseSchema(SQLiteDatabase db) {
        Log.d("DatabaseHelper", "Logging table schema:");
        Cursor cursor = db.rawQuery("PRAGMA table_info(" + TABLE_USER + ")", null);
        while (cursor.moveToNext()) {
            String columnName = cursor.getString(1);
            String columnType = cursor.getString(2);
            Log.d("DatabaseHelper", "Column: " + columnName + " | Type: " + columnType);
        }
        cursor.close();
    }
}
