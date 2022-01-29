package com.example.librarysystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class LibraryDataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Library.db";
    public static final String TABLE_NAME = "book_list";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "AUTHOR";
    public static final String COL_4 = "STATUS";

    MainActivity passUser = new MainActivity();



    public LibraryDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,AUTHOR TEXT,STATUS TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String name,String author,String status){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,author);
        contentValues.put(COL_4,status);

        long result = myDB.insert(TABLE_NAME, null,contentValues);
        if(result == -1){
            return false;
        }else {
            return true;
        }

    }
    public Cursor viewData(){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor res = myDB.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }
    public boolean getSpecificData(String username,String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor res = myDB.query(TABLE_NAME, new String [] {COL_1,COL_2,COL_3,COL_4},COL_2 + "=? AND "+COL_4 + "=?" , new String[]{username,password},null,null,null,null);
        if(res.getCount() > 0){
            return true;
        }else{
            return false;
        }
    }
    public boolean deleteData(String username){
        SQLiteDatabase myDB = this.getWritableDatabase();
        long result = myDB.delete(TABLE_NAME,COL_1 + "=?", new String[]{username});
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    public boolean updateData(String id,String name,String author,String status){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,name);
        contentValues.put(COL_3,author);
        contentValues.put(COL_4,status);

        long result = myDB.update(TABLE_NAME, contentValues,"ID = ",new String [] {id});
        if(result == -1){
            return false;
        }else {
            return true;
        }

    }


    public ArrayList<HashMap<String, String>> GetBooks(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> bookList = new ArrayList<>();
        String query = "SELECT NAME, AUTHOR, STATUS FROM "+ TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
        HashMap<String,String> user = new HashMap<>();
             user.put("name",cursor.getString(cursor.getColumnIndex(COL_2)));
             user.put("author",cursor.getString(cursor.getColumnIndex(COL_3)));
             user.put("status",cursor.getString(cursor.getColumnIndex(COL_4)));
             bookList.add(user);
            }
        return bookList;
    }

}