package com.example.parolmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataBaseHelper {
    // SQL-запрос для создания таблицы
    public static final int version = 1;
    public  static String dbName="Passw.db";
    public static final String TABLE_NAME ="password";
    public static final String COL1 = "id";
    public static final String COL2 = "siteName";
    public static final String COL3 = "login";
    public static final String COL4 = "pass";
    public static final String COL5 = "description";

    private static final String CREATE_TABLE="create table if not exists "+ TABLE_NAME + "(" + COL1 + " INTEGER PRIMARY KEY AUTOINCREMENT,"+COL2+" TEXT NOT NULL,"
            + COL3 + " TEXT, " +COL4 + " TEXT, " +  COL5 + " TEXT);";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+ TABLE_NAME;

    private Context context;

    public DataBaseHelper(Context context) {
        super(context,dbName,null,version);
        context = this.context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    public void deleteById(SQLiteDatabase db, int id) {
        // Удаляем запись с указанным ID
        db.delete(TABLE_NAME, "_id=?", new String[] {String.valueOf(id)});
    }

    public boolean InsertEmployee(Employee objEmp)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL2,objEmp.getTaskName());
        cv.put(COL3,objEmp.getTaskDescription());
        cv.put(COL4,objEmp.getTaskDate());
        cv.put(COL5,objEmp.getTaskPriority());

        long result = db.insert(TABLE_NAME,null,cv);
        if (result == -1)

            return false;
        else
            return true;
    }


}
