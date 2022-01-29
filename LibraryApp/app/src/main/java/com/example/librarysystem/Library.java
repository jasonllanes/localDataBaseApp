package com.example.librarysystem;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;


import java.util.ArrayList;
import java.util.HashMap;

public class Library extends AppCompatActivity {

    LibraryDataBase db;
    ListView book_list;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        db = new LibraryDataBase(this);

        ArrayList<HashMap<String, String>> bookList = db.GetBooks();
        book_list = (ListView) findViewById(R.id.book_list);
        ListAdapter adapter = new SimpleAdapter(Library.this, bookList, R.layout.list_row,new String[] {"name","author","status"}, new int[] {R.id.name,R.id.author,R.id.status});
        book_list.setAdapter(adapter);

        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
