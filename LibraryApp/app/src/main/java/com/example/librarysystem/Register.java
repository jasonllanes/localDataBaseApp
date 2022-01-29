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
import android.widget.Toast;

public class Register extends AppCompatActivity {

    AccountDataBase myDB;
    EditText etRegisterUsername, etRegisterName, etRegisterPassword;
    Button btnRegister, btnBack,btnUpdate,btnDelete;
    String none;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        myDB = new AccountDataBase(this);

        etRegisterUsername = findViewById(R.id.etRegisterUsername);
        etRegisterName = findViewById(R.id.etRegisterName);
        etRegisterPassword = findViewById(R.id.etRegisterPassword);


        btnRegister = findViewById(R.id.btnRegister);
        btnBack = findViewById(R.id.btnBack);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        addData();
        viewAll();


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
    public void addData(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etRegisterUsername.getText().toString().isEmpty() || etRegisterName.getText().toString().isEmpty() || etRegisterPassword.getText().toString().isEmpty()){
                    etRegisterUsername.getText().toString().equals(null);
                    etRegisterName.getText().toString().equals(null);
                    etRegisterPassword.getText().toString().equals(null);
                    Toast.makeText(Register.this,"Fill all the requirements", Toast.LENGTH_LONG).show();
                }else{
                    boolean isInserted = myDB.addData(etRegisterUsername.getText().toString(),etRegisterName.getText().toString(),etRegisterName.getText().toString());
                    if(isInserted == true){

                        Toast.makeText(Register.this,"REGISTERED!", Toast.LENGTH_LONG).show();
                        finish();

                    }else{
                        Toast.makeText(Register.this,"ERROR", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
    public void viewAll(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.viewData();
                if(res.getCount() == 0){
                    showMessage("ERROR","NOTHING FOUND");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID: " + res.getString(0)+"\n");
                    buffer.append("Username: " + res.getString(1)+"\n");
                    buffer.append("Name: " + res.getString(2)+"\n");
                    buffer.append("Password: " + res.getString(3)+"\n");
                }
                showMessage("Data",buffer.toString());
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
