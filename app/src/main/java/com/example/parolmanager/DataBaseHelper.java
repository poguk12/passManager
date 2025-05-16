package com.example.parolmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
    // Обьявление пепермнных
    public static final int version = 1;
    public static String dbName = "Passw.db";

    public static final String TABLE_NAME = "password";
    public static final String COL1 = "id";
    public static final String COL2 = "siteName";
    public static final String COL3 = "login";
    public static final String COL4 = "pass";
    public static final String COL5 = "description";

    public static final String TABLE_NAMES_LOGIN = "login";
    public static final String COL1_LOGIN = "id";
    public static final String COL2_LOGIN = "login";
    public static final String COL3_LOGIN = "pass";

    //создание первой таблицы
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
            COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL2 + " TEXT NOT NULL, " +
            COL3 + " TEXT, " +
            COL4 + " TEXT, " +
            COL5 + " TEXT);";

    //вторая
    private static final String CREATE_TABLE_LOGIN = "CREATE TABLE IF NOT EXISTS " + TABLE_NAMES_LOGIN + "(" +
            COL1_LOGIN + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL2_LOGIN + " TEXT NOT NULL, " +
            COL3_LOGIN + " TEXT NOT NULL);";

    //создание БД
    public DataBaseHelper(Context context) {
        super(context, dbName, null, version);
    }

    //выполнения запроса создания таблиц
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_LOGIN);
    }

    //обновления БД
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMES_LOGIN);
        onCreate(db);
    }

    //обращение к эплойии
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

    //обращение к эплойии
    public boolean insertLogin(Emloyee_login employeeLogin) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COL2_LOGIN, employeeLogin.getLogin());
        cv.put(COL3_LOGIN, employeeLogin.getPass());

        long result = db.insert(TABLE_NAMES_LOGIN, null, cv);
        db.close();
        return result != -1;
    }

    //удаление по айдйи
    public void deleteById(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL1 + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}