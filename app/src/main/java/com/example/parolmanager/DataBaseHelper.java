package com.example.parolmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    // Константы для БД
    public static final int version = 1;
    public static String dbName = "Passw.db";
    public static final String TABLE_NAME = "password";
    public static final String COL1 = "id";
    public static final String COL2 = "siteName";
    public static final String COL3 = "login";
    public static final String COL4 = "pass";
    public static final String COL5 = "description";

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL2 + " TEXT NOT NULL, " +
            COL3 + " TEXT, " +
            COL4 + " TEXT, " +
            COL5 + " TEXT);";

    public DataBaseHelper(Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertEmployee(Employee employee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL2, employee.getSiteName());
        cv.put(COL3, employee.getLogin());
        cv.put(COL4, employee.getPass());
        cv.put(COL5, employee.getDescription());

        long result = db.insert(TABLE_NAME, null, cv);
        db.close();
        return result != -1;
    }

    public void deleteById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL1 + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}