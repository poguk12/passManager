package com.example.parolmanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Entrance extends AppCompatActivity {

    private TextView passwordList;
    private DataBaseHelper dbHelper;

    private String parols;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrance_activity);

        dbHelper = new DataBaseHelper(this);

        passwordList = findViewById(R.id.textPasswd);



        Button btnAdd = findViewById(R.id.AddBtn);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Entrance.this, addPasswd.class);
                startActivity(intent);
            }
        });

        Button btnRefresh = findViewById(R.id.refresh);
        btnRefresh.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v) {
                passWDUpdate();
            }
        });

        Button btnBack = findViewById(R.id.AddBack);
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Entrance.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void passWDUpdate() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_NAME, null);

        parols = "";

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseHelper.COL1));
                String SiteName = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COL2));
                String Login = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COL3));
                String Pass = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COL4));
                String Description = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COL5));

                parols += "Имя сайта: " + SiteName + ". Логин: " + Login + ". Пароль: " + Pass + ". Заметки: " + Description + "\n";
            }
            while (cursor.moveToNext());
        }
        else {
            parols = ("НЕТУ ПАРОЛЕЙ");
        }

        passwordList.setText(parols.toString());
        cursor.close();
    }
    @Override
    protected void onResume() {
        super.onResume();
        passWDUpdate();
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
