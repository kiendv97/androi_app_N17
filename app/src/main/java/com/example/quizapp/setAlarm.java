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

import com.example.quizapp.Service.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class setAlarm extends AppCompatActivity implements CallBackData {
    private ArrayList<String> arrayMonHoc;
    private Spinner spinnerMH;
    private Button btnSet;
    JSONObject data;
    fetch_data ft = new fetch_data("http://192.168.1.16:3000/monhoc",null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        spinnerMH = (Spinner) findViewById(R.id.spinner);
        btnSet = (Button) findViewById(R.id.btnSet);
        ft.delegate = this;
        ft.execute();



    }

    @Override
    public void onReceiveData(String data) throws Exception {
        this.data = new JSONObject(data);
        arrayMonHoc = new ArrayList<String>();
        JSONArray arrj = new JSONArray(this.data.get("monhoc").toString());
        for (int i =0 ;i < arrj.length(); i ++ ) {
            JSONObject x= new JSONObject(arrj.opt(i).toString());

            arrayMonHoc.add(x.getString("nameLesson"));

        }


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
