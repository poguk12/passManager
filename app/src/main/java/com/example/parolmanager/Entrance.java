package com.example.parolmanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Entrance extends AppCompatActivity {

    private ListView passwordList;
    private DataBaseHelper dbHelper;

    private String parols;
    private List<String> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrance_activity);

        dbHelper = new DataBaseHelper(this);

        passwordList = findViewById(R.id.textPasswd);

        tasks = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        passwordList.setAdapter(adapter);

        Button btnAdd = findViewById(R.id.AddBtn);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Entrance.this, addPasswd.class);
                startActivity(intent);
                finish();
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
                finish();
            }
        });

        passwordList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                // Получаем выбранный элемент по позиции
                String selectedItem = tasks.get(position);

                String detailedMessage = "Подробная информация:\n" +  "----------------------\n" + "Запись: " + selectedItem + "\n" +  "ID: " + position;

                new AlertDialog.Builder(Entrance.this).setTitle("Подробная информация").setMessage(detailedMessage).setPositiveButton("OK", null).show();
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

                parols = "Имя сайта: " + SiteName + ". Логин: " + Login + ". Пароль: " + Pass + ". Заметки: " + Description + "\n";

                tasks.add(parols);
            }
            while (cursor.moveToNext());
        }
        else {
            parols = ("НЕТУ ПАРОЛЕЙ");
        }

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
