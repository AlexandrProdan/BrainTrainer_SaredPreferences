package com.example.braitrainer_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private TextView textViewScore;
    private TextView textViewTime;
    private TextView textViewRiddle;
    private TextView textViewAnswer1;
    private TextView textViewAnswer2;
    private TextView textViewAnswer3;
    private TextView textViewAnswer4;

    private MainVM mainVM;

    private static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        mainVM = new ViewModelProvider(this).get(MainVM.class);

        mainVM.getRiddle().observe(this, new Observer<Riddle>() {
            @Override
            public void onChanged(Riddle riddle) {
                displayRiddleOnScreen(riddle);
            }
        });

        setOnClickListeners();
    }
    //=============================================================================================
    private void initViews(){
        Log.d(TAG, "initViews: ");
        textViewScore = findViewById(R.id.textViewScore);
        textViewTime = findViewById(R.id.textViewTime);
        textViewRiddle = findViewById(R.id.textViewRiddle);
        textViewAnswer1 = findViewById(R.id.textViewAnswer1);
        textViewAnswer2 = findViewById(R.id.textViewAnswer2);
        textViewAnswer3 = findViewById(R.id.textViewAnswer3);
        textViewAnswer4 = findViewById(R.id.textViewAnswer4);
    }

    private  void resetValuesOnscreen(){
        resetContToZero();
        //reset time to 0
        //riddle = new Riddle();
        //displayRiddleOnScreen();
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

    private boolean isAnswerCorrect(TextView textViewUserGuess){
        int correctAnswer = mainVM.getRiddle().getValue().getCorrectAnswer();
        int userGuess = Integer.parseInt(textViewUserGuess.getText().toString());

        if(correctAnswer == userGuess){
                return true;
        }else {
            return false;
        }
    }

    private void setOnClickListeners(){
        textViewAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCorrect =  isAnswerCorrect(textViewAnswer1);
                if(isCorrect){
                    mainVM.nextRiddle();
                }
            }
        });

        textViewAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCorrect =  isAnswerCorrect(textViewAnswer2);
                if(isCorrect){
                    mainVM.nextRiddle();
                }
            }
        });

        textViewAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCorrect =  isAnswerCorrect(textViewAnswer3);
                if(isCorrect){
                    mainVM.nextRiddle();
                }
            }
        });

        textViewAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isCorrect =  isAnswerCorrect(textViewAnswer4);
                if(isCorrect){
                    mainVM.nextRiddle();
                }
            }
        });


    }

    private void resetContToZero(){count = 0;}
}