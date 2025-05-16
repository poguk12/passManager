package com.example.parolmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class addPasswd extends AppCompatActivity {
    //обьявление переменныыых
    private EditText siteName;
    private EditText login;
    private EditText pass;
    private EditText opisanie;

    private String Name;
    private String Login;
    private String Pass;
    private String Opisanie;

    //обьявление бд
    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //подлкючени к хмл файлу
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpasswd_activity);

        //краткое имя
        dbHelper = new DataBaseHelper(this);

        //краткое имя
        siteName = findViewById(R.id.EditName);
        login = findViewById(R.id.EditLogin);
        pass = findViewById(R.id.EditPass);
        opisanie = findViewById(R.id.EditZametka);

        //обработка нажатия
        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //функция получение данных
                giveDate();

                //если Нейма нет, то говорит нету и не дает продолжить
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
                //если все номрально сохарянет в БД
                else {
                    //Функция шифра
                    shifr password = new shifr(Pass);
                    String Script = password.encrypt();

                    //Эмплойи
                    Employee newEmployee = new Employee(Name, Login, Script, Opisanie);

                    //Если рабоате то возвартит тру если нет то фалс
                    boolean isInserted = dbHelper.insertEmployee(newEmployee);

                    if(isInserted) {
                        Toast.makeText(getApplicationContext(), "Данные успешно сохранены", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Ошибка при сохранении данных", Toast.LENGTH_SHORT).show();
                    }

                    //переход к новому окну
                    Intent intent = new Intent(addPasswd.this, Entrance.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        //обработка нажатия и переход
        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addPasswd.this, Entrance.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //получнеие данных
    private void giveDate(){
        Name = siteName.getText().toString().trim();
        Login = login.getText().toString().trim();
        Pass = pass.getText().toString().trim();
        Opisanie = opisanie.getText().toString().trim();
    }

    //выключение БД
    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}