package com.example.parolmanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    private EditText edit_login;
    private EditText edit_pass;

    private DataBaseHelper dbHelper;

    private String addLogin;
    private String addPass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        dbHelper = new DataBaseHelper(this);

        edit_login = findViewById(R.id.editTextLogin);
        edit_pass = findViewById(R.id.editTextPasswd);

        Button btnAddNewPass = findViewById(R.id.buttonRegister);
        btnAddNewPass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                giveDate();

                if(addLogin.isEmpty() ) {
                    Toast.makeText(getApplicationContext(), "Нету логина", Toast.LENGTH_SHORT).show();
                    return;
                }

                else if (addPass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Нету пароля", Toast.LENGTH_SHORT).show();
                    return;
                }

                else {
                    SQLiteDatabase db = dbHelper.getReadableDatabase();

                    String query = "SELECT * FROM " + DataBaseHelper.TABLE_NAMES_LOGIN + " WHERE " + DataBaseHelper.COL2_LOGIN + " = ?";
                    Cursor cursor = db.rawQuery(query, new String[]{addLogin});

                    if (cursor.moveToFirst()) {
                        Toast.makeText(Register.this, "Такой пользователь есть", Toast.LENGTH_SHORT).show();
                    }

                    else {
                        Emloyee_login emloyee_login = new Emloyee_login(addLogin, addPass);
                        boolean isInserted = dbHelper.insertLogin(emloyee_login);

                        if(isInserted) {
                            Toast.makeText(getApplicationContext(), "Данные успешно сохранены", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Ошибка при сохранении данных", Toast.LENGTH_SHORT).show();
                        }

                        Intent intent = new Intent(Register.this, MainActivity.class);
                        startActivity(intent);
                    }
                    cursor.close();
                    db.close();
                }
            }
        });

        Button btnBack = findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void giveDate(){
        addLogin = edit_login.getText().toString();
        addPass = edit_pass.getText().toString();
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
