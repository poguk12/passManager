package com.example.parolmanager;

import android.content.Intent;
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


        Button btnVxod = findViewById(R.id.buttonVxod);
        btnVxod.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                giveDate();

                String combinedText = addLogin + addPass;

                if(addLogin.isEmpty() ) {
                    Toast.makeText(getApplicationContext(), "Нету логина", Toast.LENGTH_SHORT).show();
                }

                else if (addPass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Нету пароля", Toast.LENGTH_SHORT).show();
                }

                else {
                    Intent intent = new Intent(MainActivity.this, Entrance.class);
                    startActivity(intent);
                }
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

    private void giveDate(){
        addLogin = login.getText().toString();
        addPass = pass.getText().toString();
    }
}