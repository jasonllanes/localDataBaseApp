package com.example.librarysystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    AccountDataBase myDB;

    Button btnLogin;
    TextView tvRegister;
    EditText etUserId, etPassword;
    public String passUsername;
    public String passPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new AccountDataBase(this);


        tvRegister = findViewById(R.id.tvRegister);
        btnLogin = findViewById(R.id.btnLogin);
        etUserId = findViewById(R.id.etUserId);
        etPassword = findViewById(R.id.etPassword);



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passUsername = etUserId.getText().toString();
                passPassword = etPassword.getText().toString();
                if (passUsername.isEmpty() || passPassword.isEmpty()){
                    Toast.makeText(MainActivity.this,"Please fill all the data.", Toast.LENGTH_LONG).show();
                }else{
                    if(myDB.getSpecificData(passUsername,passPassword) == true){
                        if(passUsername.equals("admin") && passPassword.equals("admin")){
                            Toast.makeText(MainActivity.this,"Welcome ADMIN!", Toast.LENGTH_LONG).show();
                          Intent i = new Intent(MainActivity.this,Admin.class);
                          startActivity(i);

                        }else{
                            Toast.makeText(MainActivity.this,"Welcome " + passUsername, Toast.LENGTH_LONG).show();
                            Intent a = new Intent(MainActivity.this,Library.class);
                            startActivity(a);

                        }

                    }else {
                        Toast.makeText(MainActivity.this,"Please sign up!", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });


        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}