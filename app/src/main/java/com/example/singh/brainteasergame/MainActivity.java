package com.example.singh.brainteasergame;

import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    Button playAgainbtn;
    TextView timer;
    TextView sumTextView;
    TextView scoreText;
    TextView result;
    ArrayList<Integer> answers;
    int locationCorrectAns;
    int incorrectAns;
    Button btn0 , btn1 , btn2 , btn3;
    int score = 0;
    int numQuestions = 0;
    ConstraintLayout gameConstraintLayout;

    public void playAgain(View view){
        score = 0;
        numQuestions = 0;

        timer.setText("30s");
        scoreText.setText("0/0");
        result.setText("");
        playAgainbtn.setVisibility(View.INVISIBLE);
        generateQuestiolns();

        new CountDownTimer(30000 , 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainbtn.setVisibility(View.VISIBLE);
                timer.setText("0s");
                result.setText("Your score:" + Integer.toString(score) + "/" + Integer.toString(numQuestions));

            }
        }.start();

    }

    public void generateQuestiolns(){

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) +  "+" + Integer.toString(b));

        locationCorrectAns = rand.nextInt(4);
        //Log.i("Location" , Integer.toString(locationCorrectAns));

        answers = new ArrayList<Integer>();
        answers.clear();


        for(int i = 0; i<4; i++){
            if(i == locationCorrectAns){
                answers.add(a + b);
            }else{
                incorrectAns = rand.nextInt(41);

                while(incorrectAns == a +  b){
                    incorrectAns = rand.nextInt(41);
                }

                answers.add(incorrectAns);
            }


        }

        btn0.setText(Integer.toString(answers.get(0)));
        btn1.setText(Integer.toString(answers.get(1)));
        btn2.setText(Integer.toString(answers.get(2)));
        btn3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAns(View view){
        if(view.getTag().toString().equals(Integer.toString(locationCorrectAns))){
            result.setText("Correct!!!!!");
            score ++;
            numQuestions++;
            scoreText.setText(Integer.toString(score) + "/" + Integer.toString(numQuestions));
        }
        else{
            result.setText("Wrong --");
            numQuestions++;
            scoreText.setText(Integer.toString(score) + "/" + Integer.toString(numQuestions));
        }

        generateQuestiolns();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.StartBtn);
        sumTextView = findViewById(R.id.textView3);
        btn0 = findViewById(R.id.button4);
        btn1 = findViewById(R.id.button3);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button);
        result = findViewById(R.id.result);
        scoreText  = findViewById(R.id.textView2);
        timer = findViewById(R.id.textView);
        playAgainbtn = findViewById(R.id.playAgain);
        playAgainbtn.setVisibility(View.INVISIBLE);
        gameConstraintLayout = findViewById(R.id.gameConstraintLayout);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButton.setVisibility(View.INVISIBLE);
                gameConstraintLayout.setVisibility(ConstraintLayout.VISIBLE);

                playAgain(findViewById(R.id.playAgain));
            }
        });


        playAgain(findViewById(R.id.playAgain));


    }
}
