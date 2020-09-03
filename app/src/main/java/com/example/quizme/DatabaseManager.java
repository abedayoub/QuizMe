package com.example.quizme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseManager extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "QuizmeDB";
    public static final String TABLE_NAME = "ResultsTb";
    public static final String Col1 = "NameCol";
    public static final String Col2 = "GradeCol";

    public DatabaseManager(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CreateTable = "CREATE TABLE "+TABLE_NAME+" (NameCol VARCHAR)";
        db.execSQL(CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

    public boolean addData(String Name, String Grade){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues NameData = new ContentValues();
        NameData.put(Col1,(Name+"               "+Grade));
        long result = db.insert(TABLE_NAME , null,NameData);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data =  db.rawQuery("SELECT * from "+TABLE_NAME, null);
        return data;
    }
}
