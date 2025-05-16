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

    //обьявление переменных
    private ListView passwordList;
    private DataBaseHelper dbHelper;

    private String parols;
    private List<String> tasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //подлючение к хмл
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrance_activity);

        //БД
        dbHelper = new DataBaseHelper(this);

        passwordList = findViewById(R.id.textPasswd);

        //настройка листвью
        tasks = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        passwordList.setAdapter(adapter);

        //обработка нажатия, переход в форму добавления
        Button btnAdd = findViewById(R.id.AddBtn);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Entrance.this, addPasswd.class);
                startActivity(intent);
                finish();
            }
        });

        //обновление
        Button btnRefresh = findViewById(R.id.refresh);
        btnRefresh.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                passWDUpdate();
            }
        });

        //назад
        Button btnBack = findViewById(R.id.AddBack);
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Entrance.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //при нажатии на листвью, открывается диалоговое окно
        passwordList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                //получение данных
                String selectedItem = tasks.get(position);

                //получение айди
                String idPart = selectedItem.substring(selectedItem.indexOf("ID: ") + 4);
                int idToDelete = Integer.parseInt(idPart.substring(0, idPart.indexOf(".")).trim());

                //Настройка вида вывода
                String detailedMessage = "Подробная информация:\n" +  "----------------------\n" + "Запись: " + selectedItem + "\n";

                //диалоговоее окно
                new AlertDialog.Builder(Entrance.this).setTitle("Подробная информация")
                        .setMessage(detailedMessage).setPositiveButton("OK", null)
                        .setNegativeButton("Удалить", (dialog, which) -> {
                            //удаление Строки
                    dbHelper.deleteById(idToDelete);
                    tasks.remove(position);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(Entrance.this, "Запись удалена", Toast.LENGTH_SHORT).show();
                }).show();
            }
        });
    }

    //обновление
    private void passWDUpdate() {
        //обращение к БД
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + DataBaseHelper.TABLE_NAME, null);

        //очистка
        parols = "";

        //перемещение на первую запись
        if (cursor.moveToFirst()) {
            do {
                //получение данных из таблицы
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseHelper.COL1));
                String SiteName = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COL2));
                String Login = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COL3));
                String Pass = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COL4));
                String Description = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COL5));

                //Дешифровка пароля
                shifr passs = new shifr(Pass);
                String passDesh = passs.decrypt();

                //оформление вида
                parols = "ID: " + id + ". Имя сайта: " + SiteName + ". Логин: " + Login + ". Пароль: " + passDesh + ". Заметки: " + Description + "\n";

                //добавление в листвью
                tasks.add(parols);
            }
            while (cursor.moveToNext());
        }
        else {
            parols = ("НЕТУ ПАРОЛЕЙ");
        }

        //закрытие
        cursor.close();
    }

    //При влючение обновляет бьлах
    @Override
    protected void onResume() {
        super.onResume();
        passWDUpdate();
    }

    //выключение БД
    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
