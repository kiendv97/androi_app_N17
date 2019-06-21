package com.example.quizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.Service.*;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class QuizActivity extends AppCompatActivity  {

    private QuestionLibrary mQuestionLibrary ;

    private TextView mScoreView;
    private TextView mCountQuestion;
    private TextView mQuestionView;
    private TextView txtAmountQuestion;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoiceQuit;
    private String mAnswer;
    private int mScore = 0;
    private int mQuestionNumber = 0;
    private int mCountQs = 0;
    private  TextView countTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
       // setContentView(R.layout.layout_lesson);
        txtAmountQuestion = (TextView) findViewById(R.id.txtAmountQuestion);
        countTimer = (TextView) findViewById(R.id.countTime);
        mScoreView = (TextView)findViewById(R.id.score);
        mCountQuestion = (TextView)findViewById(R.id.countQuestion);
        mQuestionView = (TextView)findViewById(R.id.question);
        mButtonChoice1 = (Button)findViewById(R.id.choice1);
        mButtonChoice2 = (Button)findViewById(R.id.choice2);
        mButtonChoice3 = (Button)findViewById(R.id.choice3);
        mButtonChoiceQuit = (Button) findViewById(R.id.quit);
        mQuestionLibrary= new QuestionLibrary(getIntent().getIntExtra("idMon", 1));
        updateQuestion();
        startCountTime();
    // start time

        //Start of Button Listener for Button1
        mButtonChoice1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (mButtonChoice1.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                //This line of code is optiona
                    Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        //End of Button Listener for Button1

        //Start of Button Listener for Button2
        mButtonChoice2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (mButtonChoice2.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                    //This line of code is optiona
                    Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        //End of Button Listener for Button2


        //Start of Button Listener for Button3
        mButtonChoice3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //My logic for Button goes in here

                if (mButtonChoice3.getText() == mAnswer){
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();
                    //This line of code is optiona
                    Toast.makeText(QuizActivity.this, "correct", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(QuizActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                    updateQuestion();
                }
            }
        });

        //End of Button Listener for Button3

    mButtonChoiceQuit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
            builder.setMessage("Ban cho chac chan muon thoat")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(QuizActivity.this, LessonActivity.class);
                            Toast.makeText(QuizActivity.this, "Quited", Toast.LENGTH_SHORT).show();
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            intent.putExtra("EXIT", true);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
            builder.create();
            builder.show();

        }
    });


    }

    private void updateQuestion() {

        if (mQuestionNumber < mQuestionLibrary.getLength()) {
          //  Log.d("string",mQuestionLibrary.getQuestion(1));
            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
            mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
            mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));

            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
            if(mCountQs < getIntent().getIntExtra("amount",10)) {
                mCountQs++;
                mCountQuestion.setText("" + mCountQs);

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
                builder.setMessage("Hoan thanh")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {

                                    Intent intent = new Intent(QuizActivity.this, LessonActivity.class);
                                    Toast.makeText(QuizActivity.this, "Quited", Toast.LENGTH_SHORT).show();
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.putExtra("EXIT", true);
                                    JSONObject jsonObject = new JSONObject();
                                    jsonObject.put("monhoc",getIntent().getIntExtra("idMon",1));
                                    jsonObject.put("soccer",mScore);
                                    post_data pd = new post_data("http://192.168.1.16:3000/history",jsonObject);
                                    pd.execute();
                                    startActivity(intent);
                                    finish();
                                }catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                builder.create();
                builder.show();
            }

        } else {

            mQuestionNumber = 0;
        }
    }

    private void updateScore(int point) {
        mScoreView.setText("" + mScore);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
        builder.setMessage("Ban cho chac chan muon thoat")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(QuizActivity.this, LessonActivity.class);
                        Toast.makeText(QuizActivity.this, "Quited", Toast.LENGTH_SHORT).show();
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        return;
                    }
                });
        builder.create();
      //  builder.show();

    }
    public  void startCountTime() {
        txtAmountQuestion.setText("/" + getIntent().getIntExtra("amount",10));
        CountDownTimer countDownTimer = new CountDownTimer(getIntent().getIntExtra("time",10) * 60000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countTimer.setText(String.valueOf( millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
                builder.setMessage("Het gio")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    Intent intent = new Intent(QuizActivity.this, LessonActivity.class);
                                    Toast.makeText(QuizActivity.this, "Quited", Toast.LENGTH_SHORT).show();
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    intent.putExtra("EXIT", true);
                                    JSONObject jsonObject = new JSONObject();
                                    jsonObject.put("monhoc",getIntent().getIntExtra("idMon",1));
                                    jsonObject.put("soccer",mScore);
                                    post_data pd = new post_data("http://192.168.1.16:3000/history",jsonObject);
                                    pd.execute();
                                    startActivity(intent);
                                    finish();
                                }catch (Exception e) {

                                }


                            }
                        });

                builder.create();
//                builder.show();
            }
        }.start();
    }
}
