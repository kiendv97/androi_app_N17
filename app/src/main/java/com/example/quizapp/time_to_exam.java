package com.example.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class time_to_exam extends AppCompatActivity {
    private Button btnTime1,btnTime2,btnTime3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_to_exam);
        btnTime1 = (Button) findViewById(R.id.btnTime1);
        btnTime2 = (Button) findViewById(R.id.btnTime2);
        btnTime3 = (Button) findViewById(R.id.btnTime3);

        btnTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(time_to_exam.this, QuizActivity.class));
            }
        });
        btnTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(time_to_exam.this, QuizActivity.class));
            }
        });
        btnTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(time_to_exam.this, QuizActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(time_to_exam.this, LessonActivity.class);
        startActivity(intent );
        finish();
        super.onBackPressed();
    }
}
