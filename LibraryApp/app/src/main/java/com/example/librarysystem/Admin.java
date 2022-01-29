package com.example.librarysystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class Admin extends AppCompatActivity {

    LibraryDataBase db;
    ListView book_list;
    EditText etAddName, etAddAuthor, etAddStatus,etID;
    Button btnRegister, btnBack,btnUpdate,btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        db = new LibraryDataBase(this);


        etAddName = findViewById(R.id.etAddName);
        etAddAuthor = findViewById(R.id.etAddAuthor);
        etAddStatus = findViewById(R.id.etAddStatus);
        etID = findViewById(R.id.etID);


        btnRegister = findViewById(R.id.btnRegister);
        btnBack = findViewById(R.id.btnBack);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        addData();
        viewAll();
        updateData();
        deleteData();

//        ArrayList<HashMap<String, String>> bookList = db.GetBooks();
//        book_list = (ListView) findViewById(R.id.book_list);
//        ListAdapter adapter = new SimpleAdapter(Admin.this, bookList, R.layout.list_row,new String[] {"name","author","status"}, new int[] {R.id.bookname,R.id.author,R.id.status});
//        book_list.setAdapter(adapter);



    }

    public void addData(){
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etAddName.getText().toString().isEmpty() || etAddAuthor.getText().toString().isEmpty()){
                    Toast.makeText(Admin.this,"Fill all the requirements", Toast.LENGTH_LONG).show();
                }else{
                    String avail = "Available";
                    boolean isInserted = db.addData(etAddName.getText().toString(),etAddAuthor.getText().toString(),avail);
                    if(isInserted == true){

                        Toast.makeText(Admin.this,"REGISTERED!", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(Admin.this,"ERROR", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }
    public void viewAll(){
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.viewData();
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
    public void updateData(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = db.updateData(etID.getText().toString(),etAddName.getText().toString(),etAddAuthor.getText().toString(),etAddStatus.getText().toString());
                if(isUpdated = true){
                    String none = "";
                    etID.getText().toString().equals("");
                    etAddName.getText().toString().equals("");
                    etAddAuthor.getText().toString().equals("");
                    etAddStatus.getText().toString().equals("");
                    Toast.makeText(Admin.this,"UPDATED!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Admin.this,"Input the ID!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void deleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteData(etID.getText().toString());
                Toast.makeText(Admin.this,"DELETED!", Toast.LENGTH_LONG).show();
            }
        });
    }

}
