package com.example.android.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button start,b1,b2,b3,b4,playagain;
    Random random;
    RelativeLayout r1;
    int a,b,actualsum,incorrect,locationCorrectAnswer,score=0,noofquestion=0;

    ArrayList <Integer> answers;
    TextView question,correct,score1,timer;
    public void generatequestion()
    {
        a = random.nextInt(21);
        b = random.nextInt(21);
        answers.clear();


        locationCorrectAnswer = random.nextInt(4);


        question.setText(a + "+" + b);


        actualsum = a + b;
        for (int i = 0; i < 4; i++) {

            if (i == locationCorrectAnswer) {
                answers.add(actualsum);
            }
            else {
                incorrect = random.nextInt(41);
                while (incorrect == actualsum) {
                    incorrect = random.nextInt(41);
                }
                answers.add(incorrect);
            }
        }
        b1.setText(Integer.toString(answers.get(0)));
        b2.setText(Integer.toString(answers.get(1)));
        b3.setText(Integer.toString(answers.get(2)));
        b4.setText(Integer.toString(answers.get(3)));


    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        random = new Random();
        timer=(TextView)findViewById(R.id.timertextview);
        playagain=(Button)findViewById(R.id.playagain);
        answers = new ArrayList<Integer>();
        correct = (TextView) findViewById(R.id.resulttextview);
        start = (Button) findViewById(R.id.startButton);
        score1 = (TextView) findViewById(R.id.scoretextview);
        question = (TextView) findViewById(R.id.questextview);


        b1 = (Button) findViewById(R.id.button0);
        b2 = (Button) findViewById(R.id.button1);
        b3 = (Button) findViewById(R.id.button2);
        b4 = (Button) findViewById(R.id.button3);
        r1=(RelativeLayout)findViewById(R.id.r1);
        generatequestion();

        new CountDownTimer(30000,1000) {


            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(Long.toString((millisUntilFinished/1000))+"s");

            }

            @Override
            public void onFinish() {
                playagain.setVisibility(View.VISIBLE);

timer.setText("0s");
                correct.setText("Your final score "+Integer.toString(score));



            }
        }.start();
        playagain(findViewById(R.id.button3));

    }





    public void start(View view) {
        start.setVisibility(View.INVISIBLE);
        r1.setVisibility(View.VISIBLE);
    }

    public void choose(View view) {
        if(view.getTag().toString().equals(Integer.toString(locationCorrectAnswer)))
        {
            correct.setText("Correct");
            score++;
        }
        else
        {
            correct.setText("Incorrect");
        }
        noofquestion++;
        score1.setText(Integer.toString(score)+"/"+ Integer.toString(noofquestion));
        generatequestion();

    }

    public void playagain(View view) {

        playagain.setVisibility(View.INVISIBLE);
        score=0;
        noofquestion=0;
        timer.setText("30s");
        score1.setText("0/0");
        correct.setText("");
        new CountDownTimer(30000,1000) {


            @Override
            public void onTick(long millisUntilFinished) {
                timer.setText(Long.toString((millisUntilFinished/1000))+"s");

            }

            @Override
            public void onFinish() {


            }
        }.start();

    }
}
