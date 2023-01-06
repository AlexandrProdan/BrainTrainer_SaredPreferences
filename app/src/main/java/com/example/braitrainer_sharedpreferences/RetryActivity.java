package com.example.braitrainer_sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;

public class RetryActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "score";
    private TextView textViewHighScore;
    private TextView textViewScore;
    private CardView cardViewRetry;

    SharedPreferences highScore;

    int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retry);
        score = (int)getIntent().getIntExtra(EXTRA_SCORE,0);
        highScore = PreferenceManager.getDefaultSharedPreferences(this);
        initViews();
        textViewScore.setText(""+score);

        cardViewRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RetryActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        isHighScore();
    }
    void initViews(){
        textViewHighScore = findViewById(R.id.textViewHighScore);
        textViewScore = findViewById(R.id.textViewScore);
        cardViewRetry = findViewById(R.id.cardViewRetry);
    }

    void isHighScore(){
        if(score > highScore.getInt("highScore",0)){
            highScore.edit().putInt("highScore", score).apply();
            textViewScore.setText(""+score);
            textViewHighScore.setText(""+score);
        }else {
            String high = ""+ (highScore.getInt("highScore",0));
            textViewHighScore.setText(high);
            textViewScore.setText(""+score);
        }
    }

    public static Intent newIntent(Context context, int score){
            Intent intent = new Intent(context, RetryActivity.class);
            intent.putExtra(EXTRA_SCORE, score);
            return intent;
    }
}