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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addpasswd_activity);

        siteName = findViewById(R.id.EditName);
        login = findViewById(R.id.EditLogin);
        pass = findViewById(R.id.EditPass);
        opisanie = findViewById(R.id.EditZametka);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                giveDate();

                String combinedText = Name + Login + Pass + Opisanie;

                if(Name.isEmpty() ) {
                    Toast.makeText(getApplicationContext(), "Нету имени сайта", Toast.LENGTH_SHORT).show();
                }

                else if (Login.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Нету логина", Toast.LENGTH_SHORT).show();
                }

                else if (Pass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Нету пароля", Toast.LENGTH_SHORT).show();
                }

                else {
                    Intent intent = new Intent(addPasswd.this, Entrance.class);
                    startActivity(intent);
                }
            }
        });

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addPasswd.this, Entrance.class);
                startActivity(intent);
            }
        });
    }

    private void giveDate(){
        Name = siteName.getText().toString();
        Login = login.getText().toString();
        Pass = pass.getText().toString();
        Opisanie = opisanie.getText().toString();

    }
}
