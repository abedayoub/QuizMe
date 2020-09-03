package com.example.quizme;


import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText nameTxt;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameTxt = findViewById(R.id.name_txt);
        startButton = findViewById(R.id.start_btn);
        startButton.setOnClickListener(this);
    }


    //Show Message
    public void ShowMessage(String Title, String Text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Text);
        builder.show();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.start_btn) {
            Intent i = new Intent(MainActivity.this, Question.class);
            i.putExtra("name", nameTxt.getText().toString());
            startActivity(i);
            ShowMessage("test","done main");
        }
    }
}