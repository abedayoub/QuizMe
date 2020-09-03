package com.example.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import android.os.Vibrator;
import android.os.VibrationEffect;
import java.util.Random;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.w3c.dom.Text;

public class Question extends AppCompatActivity implements View.OnClickListener{


    String[] questions = {"Jaw thrust is done when:","Unequal pupils are a symptom for:","In case of an amputated leg for a cardiac arrested patient, priority is for:","Traction is forbidden if","Hypovolemic Shock is:","CCAB stands for:","Scene size-up is:","A&0x3 stands for:","In Case of Hip Trauma .... is used to Transfer Patient","by AHA, What's the HeartBeat Rate for a 34 years female?","by AHA, What's the 2 Rescuers CPR Rate for an 11 Y/O Male?", "For how much, you perform suction for a 26 YO Lady?", "Life Threatening Bleeding is Controlled through:"};
    String[] sugs1 = {"Unequal pupils","Leg Fracture","The nose","The Patient is a pregnant lady","Loss of Breath","Consciousness - Circulation - Airway = Breath","Checking the ambulance Gaz Level","Alert and Oriented to place, time and event","Scoop Board","12-20 BPM", "30 Compressions 5 Breathes", "15 Seconds", "Sam Splint"};
    String[] sugs2 = {"Severe Blood Loss","Pneumothorax","The Critical Bleeding","The bleeding isn't Stopping","Critical Bleeding","Cancer - Candy - Amiglada - Bones","Running Through the scene","Alert and Oriented to Accident, Cellular and Damage","Spine Board","45-105 BPM", "15 Compressions 5 Breathes", "5 minutes", "Wound Compression"};
    String[] sugs3 = {"Spine Injury Airway Check","Tibial Artery Bleeding","The Life Threading cardiac arrest","Fracture is on the level of a joint","Lower Blood Volume","Medical Case Name","Criteria to assure the scene and patient first survey","Alert and Oriented to Place, Person and Blood","KED","60-120 BPM", "30 Compressions 2 Breathes", "10 Seconds", "Tourniquet"};
    String[] sugs4 = {"Hip Trauma","Brain Injury","The Ambulance","Tachypnea","Excessive O2 Enrichment","Advanced Trauma Assessment Protocol","Measurement of Consciousness","Amputation and Oxygen for 3 Liters","Sam Splint","85-135 RPM", "15 Compressions 2 Breathes", "5 Seconds", "Oxygen Open Flow"};
    String[] sugs5 = {"Suicide Attempt","Hypoventilation","The Fracture","Conscious Patient","Diabetics Emergency","Country Name","CPR Criteria","Alert and Oriented to place, time and person","Tourniquet","12-18 RPM", "30 Compressions 2 Breathes 1 Tourniquet", "1 minute", "Spine Board"};
    String[] righAns = {"Spine Injury Airway Check","Brain Injury","The Critical Bleeding","Fracture is on the level of a joint","Lower Blood Volume","Consciousness - Circulation - Airway = Breath","Criteria to assure the scene and patient first survey","Alert and Oriented to place, time and person","Scoop Board","60-120 BPM", "30 Compressions 2 Breathes", "15 Seconds", "Tourniquet"};
    String[]  hint = {"Spine Jaw Thrust","Pupils Hint","Comparision Hint","Traction hint","Lower Blood Volume","CCAB Hint","Scene size up hint","Hip trauma is transferred through Scoop","AHA Guidelines 2019, Has Proven that...","AHA Guidelines 30:2","Suction for adults is a ...","Tourniquet is an American ..."};
    ArrayList<String> passed = new ArrayList<String>();
    String question, sug1, sug2, sug3, sug4, sug5, answer, rightAnswer, selectedAns, name;
    TextView questText, nameLbl, gradeLbl, timerTxt;
    RadioButton sug1Txt, sug2Txt, sug3Txt, sug4Txt, sug5Txt, radioButton;
    RadioGroup rg;
    Button check_btn;
    int result = 0, ans = -1, res = -1, count=0;
    Random rand = new Random();
    Vibrator vibrator;
    SQLiteDatabase db;
    DatabaseManager myDB;
    CountDownTimer countDownTimer;
//    DatabaseHandler helper = new DatabaseHandler(this);


    public void vibrateFor500ms() {
        vibrator.vibrate(500);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        questText = (TextView) findViewById(R.id.quest);
        sug1Txt = (RadioButton) findViewById(R.id.sug1);
        sug2Txt = (RadioButton) findViewById(R.id.sug2);
        timerTxt = (TextView) findViewById(R.id.timerTxt);
        sug3Txt = (RadioButton) findViewById(R.id.sug3);
        sug4Txt = (RadioButton) findViewById(R.id.sug4);
        sug5Txt = (RadioButton) findViewById(R.id.sug5);
        nameLbl = (TextView) findViewById(R.id.name_lbl);
        gradeLbl = (TextView) findViewById(R.id.grade_lbl);
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        check_btn = (Button) findViewById(R.id.check);
        check_btn.setOnClickListener(this);
        Intent i = getIntent();
        name = i.getStringExtra("name");
        nameLbl.setText(name);
        counter().start();
        questGenerator();
        myDB = new DatabaseManager(this);
//
//        //db
//        db = openOrCreateDatabase("resultsdb", Context.MODE_PRIVATE,null );
//        db.execSQL("CREATE TABLE IF NOT EXISTS resultsTB (playerName VARCHAR,finalResult VARCHAR);");

    }

    //Generate Questions
    public void questGenerator() {
        res = rand.nextInt(questions.length);
        question = questions[res];
        sug1 = sugs1[res];
        sug2 = sugs2[res];
        sug3 = sugs3[res];
        sug4 = sugs4[res];
        sug5 = sugs5[res];
        rightAnswer = righAns[res];
        questText.setText((count+1)+". " +question);
        sug1Txt.setText(sug1);
        sug2Txt.setText(sug2);
        sug3Txt.setText(sug3);
        sug4Txt.setText(sug4);
        sug5Txt.setText(sug5);

        passed.add(Integer.toString(res));

    }

    //Questions Checker
    public int questionsChecker(int index) {
        int ind = -1;
        for (int i = 0; i < passed.size(); i++) {
            if (passed.indexOf(i) != index) {
                ind = 1;
            } else
                ind = 0;
        }
        return ind;
    }


    //Check Answer
    public int AnswerChecker() {
//        Log.d("test: ", getAnswerFunc());
        if (getAnswerFunc().equals(rightAnswer)) {
            return 1;
        } else return 0;
    }


    //Show Message
    public void ShowMessage(String Title, String Text) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Text);
        builder.show();
    }

    public String getAnswerFunc() {
        int selectedId = rg.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        String AnsFunc = radioButton.getText().toString();
        return AnsFunc;
    }

    @Override
    public void onBackPressed(){}


    //OnClick
    public void onClick(View view) {
        if (view.getId() == R.id.check) {
            count++;
            String onClickAnswer = getAnswerFunc();
            if (AnswerChecker() == 1) {
                result++;
                ShowMessage("Great", Integer.toString(AnswerChecker()));
//                stopCounter();
                counter().cancel();
                counter().start();
                questGenerator();
                gradeLbl.setText(Integer.toString(result)+"/10");
                if(count>=9){
                        String resultStr = Integer.toString(result);
                        myDB.addData(name, resultStr);

//                    db.execSQL("insert into resultsTB values('"+name+"','"+Integer.toString(result)+"');");
                        Intent resI = new Intent(this, Results.class);
                        startActivity(resI);
                }

                if(questionsChecker(res)==1){
                    questGenerator();
                }
                else if(questionsChecker(res)==0){
                    res = rand.nextInt(questions.length);
                    ShowMessage("New", "New Res Generated");
                }
            } else if (AnswerChecker() == 0) {
                vibrateFor500ms();
//                stopCounter();
                counterZero().start();
                counter().cancel();
                counterZero().cancel();

                counter().start();
                ShowMessage("Error", Integer.toString(AnswerChecker()));
                questGenerator();
            }
//            while (questionsChecker(res) != 0) {
//                questGenerator();
//
//            }
        }
    }

    public CountDownTimer counter(){
        countDownTimer = new CountDownTimer(10000, 1000) {


            public void onTick(long millisUntilFinished) {
                timerTxt.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                questGenerator();
                count++;
            }
        };
        return countDownTimer;
    }

    public CountDownTimer counterZero(){
        countDownTimer = new CountDownTimer(10, 10) {

            public void onTick(long millisUntilFinished) {
                timerTxt.setText("seconds remaining: " + 0);
            }

            public void onFinish() {
                questGenerator();
//                count++;
            }
        };
        return countDownTimer;
    }
}