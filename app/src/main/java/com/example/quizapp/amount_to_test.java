package com.example.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class amount_to_test extends AppCompatActivity {
    private Button btnAmount1,btnAmount2,btnAmount3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount_to_test);
        btnAmount1 = (Button) findViewById(R.id.btnAmount1);
        btnAmount2 = (Button) findViewById(R.id.btnAmount2);
        btnAmount3 = (Button) findViewById(R.id.btnAmount3);

        btnAmount1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(amount_to_test.this, time_to_exam.class));
            }
        });
        btnAmount2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(amount_to_test.this, time_to_exam.class));
            }
        });
        btnAmount3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(amount_to_test.this, time_to_exam.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(amount_to_test.this, LessonActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}
