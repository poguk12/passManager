package com.example.parolmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class addPasswd extends AppCompatActivity {

    private EditText siteName;
    private EditText login;
    private EditText pass;
    private EditText opisanie;

    private String Name;
    private String Login;
    private String Pass;
    private String Opisanie;

    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpasswd_activity);

        // Инициализация базы данных
        dbHelper = new DataBaseHelper(this);

        siteName = findViewById(R.id.EditName);
        login = findViewById(R.id.EditLogin);
        pass = findViewById(R.id.EditPass);
        opisanie = findViewById(R.id.EditZametka);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                giveDate();

                if(Name.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Нету имени сайта", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (Login.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Нету логина", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (Pass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Нету пароля", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    // Создаем объект Employee с введенными данными
                    Employee newEmployee = new Employee(Name, Login, Pass, Opisanie);

                    // Пытаемся добавить запись в базу данных
                    boolean isInserted = dbHelper.insertEmployee(newEmployee);

                    if(isInserted) {
                        Toast.makeText(getApplicationContext(), "Данные успешно сохранены", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Ошибка при сохранении данных", Toast.LENGTH_SHORT).show();
                    }

                    // Переходим обратно на главный экран
                    Intent intent = new Intent(addPasswd.this, Entrance.class);
                    startActivity(intent);
                    finish(); // Закрываем текущую активность
                }
            }
        });

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addPasswd.this, Entrance.class);
                startActivity(intent);
                finish(); // Закрываем текущую активность
            }
        });
    }

    private void giveDate(){
        Name = siteName.getText().toString().trim();
        Login = login.getText().toString().trim();
        Pass = pass.getText().toString().trim();
        Opisanie = opisanie.getText().toString().trim();
    }

    @Override
    protected void onDestroy() {
        dbHelper.close(); // Закрываем соединение с БД при уничтожении активности
        super.onDestroy();
    }
}