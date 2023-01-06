package com.example.braitrainer_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    public static final int ALLOCATED_TIME = 20;
    private TextView textViewScore;
    private TextView textViewTime;
    private TextView textViewRiddle;
    private TextView textViewAnswer1;
    private TextView textViewAnswer2;
    private TextView textViewAnswer3;
    private TextView textViewAnswer4;

    CountDownTimer timer;

    private MainVM mainVM;


    private static int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        score = 0;
        initViews();


        mainVM = new ViewModelProvider(this).get(MainVM.class);

        mainVM.getRiddle().observe(this, new Observer<Riddle>() {
            @Override
            public void onChanged(Riddle riddle) {
                displayRiddleOnScreen(riddle);
            }
        });

        setOnClickListeners();
        countTime(ALLOCATED_TIME);


    }
    //=============================================================================================
    private void initViews(){
        Log.d(TAG, "initViews: ");
        textViewScore = findViewById(R.id.textViewScore);
        textViewRiddle = findViewById(R.id.textViewRiddle);
        textViewAnswer1 = findViewById(R.id.textViewAnswer1);
        textViewAnswer2 = findViewById(R.id.textViewAnswer2);
        textViewAnswer3 = findViewById(R.id.textViewAnswer3);
        textViewAnswer4 = findViewById(R.id.textViewAnswer4);

        textViewTime = findViewById(R.id.textViewTime);
    }

    private void displayRiddleOnScreen(Riddle riddle){
        String operation="";
        if(riddle.getOperation()){
            operation = " + ";
        }else {
            operation = " - ";
        }
        textViewRiddle.setText(""+riddle.getNum1()+operation+riddle.getNum2());

        textViewAnswer1.setText(riddle.getRandomOrdAnsArrList().get(0).toString());
        textViewAnswer2.setText(riddle.getRandomOrdAnsArrList().get(1).toString());
        textViewAnswer3.setText(riddle.getRandomOrdAnsArrList().get(2).toString());
        textViewAnswer4.setText(riddle.getRandomOrdAnsArrList().get(3).toString());
    }

    private void isAnswerCorrect(TextView textViewUserGuess){
        int correctAnswer = mainVM.getRiddle().getValue().getCorrectAnswer();
        int userGuess = Integer.parseInt(textViewUserGuess.getText().toString());

        if(correctAnswer == userGuess){
            score++;
            textViewScore.setText(""+score);
            timer.cancel();
            this.countTime(ALLOCATED_TIME);
            mainVM.nextRiddle();
        }else {
            timer.cancel();
            Intent intent = RetryActivity.newIntent(MainActivity.this, score);
            startActivity(intent);
            finish();
        }
    }

    private void setOnClickListeners(){
        textViewAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAnswerCorrect(textViewAnswer1);
            }
        });

        textViewAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAnswerCorrect(textViewAnswer2);
            }
        });

        textViewAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAnswerCorrect(textViewAnswer3);
            }
        });

        textViewAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isAnswerCorrect(textViewAnswer4);
            }
        });


    }

    void countTime(int seconds){
        timer = new CountDownTimer((seconds*1000), 1000) {
            @Override
            public void onTick(long millisUntilFinishes) {
                int secondsLeft = (int) (millisUntilFinishes/1000);
                secondsLeft++;
                textViewTime.setText(""+secondsLeft);
            }

            @Override
            public void onFinish() {
                textViewTime.setText("0");
                Intent intent = RetryActivity.newIntent(MainActivity.this, score);
                timer.cancel();
                startActivity(intent);
                finish();
            }
        };
        timer.start();
    }


}