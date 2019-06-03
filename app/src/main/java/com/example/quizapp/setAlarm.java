package com.example.quizapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class setAlarm extends AppCompatActivity {
    private ArrayList<String> arrayMonHoc;
    private Spinner spinnerMH;
    private Button btnSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        spinnerMH = (Spinner) findViewById(R.id.spinner);
        btnSet = (Button) findViewById(R.id.btnSet);
        arrayMonHoc = new ArrayList<String>();
        arrayMonHoc.add("Toán rời rạc");
        arrayMonHoc.add("Triết học");
        arrayMonHoc.add("Giám sát mạng");
        arrayMonHoc.add("Phân tích");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,arrayMonHoc);
        spinnerMH.setAdapter(arrayAdapter);
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(setAlarm.this,list_item_target.class));
            }
        });


    }
}
