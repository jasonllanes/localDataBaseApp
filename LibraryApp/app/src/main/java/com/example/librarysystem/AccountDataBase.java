package com.example.librarysystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AccountDataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Account.db";
    public static final String TABLE_NAME = "tablename";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Username";
    public static final String COL_3 = "Name";
    public static final String COL_4 = "Password";

    MainActivity passUser = new MainActivity();



    public AccountDataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,USERNAME TEXT,NAME TEXT,PASSWORD TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String username,String name,String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,username);
        contentValues.put(COL_3,name);
        contentValues.put(COL_4,password);

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
    public boolean updateData(String username,String name,String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,username);
        contentValues.put(COL_3,name);
        contentValues.put(COL_4,password);

        long result = myDB.update(TABLE_NAME, contentValues,"Username = ?",new String [] {username});
        if(result == -1){
            return false;
        }else {
            return true;
        }

    }
}