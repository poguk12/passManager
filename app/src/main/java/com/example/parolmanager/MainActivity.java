package com.example.parolmanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.parolmanager.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private DataBaseHelper dbHelper;

    private EditText login;
    private EditText pass;

    private String addLogin;
    private String addPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.editTextLogin);
        pass = findViewById(R.id.editTextPasswd);

        dbHelper = new DataBaseHelper(this);

        Button btnVxod = findViewById(R.id.buttonVxod);
        btnVxod.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                addLogin = login.getText().toString();
                addPass = pass.getText().toString();

                if(addLogin.isEmpty() || addPass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Нету логина или пароля", Toast.LENGTH_SHORT).show();
                    return;
                }

                SQLiteDatabase db = dbHelper.getReadableDatabase();

                String query = "SELECT * FROM " + DataBaseHelper.TABLE_NAMES_LOGIN + " WHERE " + DataBaseHelper.COL2_LOGIN + " = ?";
                Cursor cursor = db.rawQuery(query, new String[]{addLogin});

                if (cursor.moveToFirst()) {

                    String dbPass = cursor.getString(cursor.getColumnIndexOrThrow(DataBaseHelper.COL3_LOGIN));

                    if (addPass.equals(dbPass)) {
                        Intent intent = new Intent(MainActivity.this, Entrance.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Неверный пароль", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Пользователь не найден", Toast.LENGTH_SHORT).show();
                }

                cursor.close();
                db.close();
            }
        });

        Button btnRegister = findViewById(R.id.buttonRegister);
        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
            }
        });
    }
}