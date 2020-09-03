package com.example.quizme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Results extends AppCompatActivity implements View.OnClickListener {


    DatabaseManager myDB;
    Button playBtn;
//    SQLiteDatabase db;
    ListView Lv;
//    ListView GradeLV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        playBtn=(Button) findViewById(R.id.PAgain_Btn);
        playBtn.setOnClickListener(this);
        Lv = (ListView) findViewById(R.id.lv);
//        GradeLV = (ListView) findViewById(R.id.grade_LV);
        myDB = new DatabaseManager(this);
//        myDB.getReadableDatabase();
        ArrayList<String> NameAndGrade = new ArrayList<>();

        String[] test;
        ArrayList<String> Name = new ArrayList<>();
        ArrayList<String> Grade = new ArrayList<>();
        Cursor data = myDB.getListContents();

        if(data.getCount() == 0) {
            ShowMessage("No Data", "Returned data is 0 results");
//        }else{
//            while (data.moveToNext()){
//                int i=0;
//                NameAndGrade.add(data.getString(0));
//                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,NameAndGrade);
//                Lv.setAdapter(listAdapter);
//                i++;
//            }
//        }
//
        }else{
//            for(int i=0;i<data.getCount();i++){
//                data.moveToNext(); ;
//                test = NameAndGrade.get(i).split("/");
//
//                String[] arrOfStr = NameAndGrade.add(data.getString(i).split("/"));
//                for (String a: arrOfStr)
//                    System.out.println(a);
//
//                Name.add(test[0]);
//                Grade.add(test[1]);
//                ListAdapter nameLA = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,Name);
//                ListAdapter gradeLA = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, Grade);
//                Lv.setAdapter(nameLA);
//                GradeLV.setAdapter(gradeLA);
//            }
            while (data.moveToNext()){
                int i=0;
                NameAndGrade.add(data.getString(0));
                ListAdapter listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,NameAndGrade);
                Lv.setAdapter(listAdapter);
                i++;
            }
        }

//
//        db = openOrCreateDatabase("resultsdb", Context.MODE_PRIVATE, null);
//
//        getResults();
    }



//
//    public void getResults() {
//        String query = "Select * FROM resultsTB";
////        List<String> res = new ArrayList<Integer>();
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, res);
//
//        Cursor cursor = db.rawQuery(query, null);
//
//        if (cursor.moveToFirst()) {
//            cursor.moveToFirst();
//            cursor.close();
//        }
//
//        Lv.setAdapter(adapter);
//        db.close();
//    }


    //Show Message
    public void ShowMessage(String Title, String Text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Text);
        builder.show();
    }

    //OnClick
    public void onClick(View view){
        if(view.getId()==playBtn.getId()){
            Intent playI = new Intent(this, MainActivity.class);
            startActivity(playI);
        }
    }

    @Override
    public void onBackPressed(){}

//    public voidGetAllResults(){
//        db = database
//    }
}
