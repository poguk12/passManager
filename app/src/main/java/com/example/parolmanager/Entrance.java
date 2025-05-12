package com.example.parolmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Entrance extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entrance_activity); // здесь подключаем наш XML файл


        Button btnAdd = findViewById(R.id.AddBtn);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Entrance.this, addPasswd.class);
                startActivity(intent);
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

}
