package com.example.parolmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    private EditText login;
    private EditText pass;



    private String addLogin;
    private String addPass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        login = findViewById(R.id.editTextLogin);
        pass = findViewById(R.id.editTextPasswd);

        Button btnAddNewPass = findViewById(R.id.buttonRegister);
        btnAddNewPass.setOnClickListener(new View.OnClickListener(){
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
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
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
        addLogin = login.getText().toString();
        addPass = pass.getText().toString();
    }
}
