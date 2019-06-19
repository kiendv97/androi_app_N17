package com.example.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class time_to_exam extends AppCompatActivity {
    private Button btnTime1,btnTime2,btnTime3;
    int amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_to_exam);
        btnTime1 = (Button) findViewById(R.id.btnTime1);
        btnTime2 = (Button) findViewById(R.id.btnTime2);
        btnTime3 = (Button) findViewById(R.id.btnTime3);
         amount = getIntent().getIntExtra("amount",0);
        btnTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), QuizActivity.class);
                intent.putExtra("amount",amount);
                intent.putExtra("time",5);
                startActivity(new Intent(intent));
            }
        });
        btnTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), QuizActivity.class);
                intent.putExtra("amount",amount);
                intent.putExtra("time",10);
                startActivity(new Intent(intent));
            }
        });
        btnTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), QuizActivity.class);
                intent.putExtra("amount",amount);
                intent.putExtra("time",15);
                startActivity(new Intent(intent));
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(time_to_exam.this, LessonActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
