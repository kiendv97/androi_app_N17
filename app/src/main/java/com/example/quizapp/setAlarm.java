package com.example.quizapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.Service.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class setAlarm extends AppCompatActivity implements CallBackData {
    private ArrayList<String> arrayMonHoc;
    private Spinner spinnerMH;
    private Button btnSet;
    JSONObject data;
    TextView editTarget;
    TextView monhoc;
    CalendarView calendarView;
    Spinner spinner;
    fetch_data ft = new fetch_data("http://192.168.1.16:3000/monhoc",null);
long date;
Calendar calendar = Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        spinnerMH = (Spinner) findViewById(R.id.spinner);
        btnSet = (Button) findViewById(R.id.btnSet);
        editTarget = (TextView) findViewById(R.id.editTarget);
        monhoc = (TextView) findViewById(R.id.monhoc);

        spinner = (Spinner) findViewById(R.id.spinner);
        ft.delegate = this;
        ft.execute();
        calendarView = (CalendarView) findViewById(R.id.calendarView);


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange( CalendarView view, int year, int month, int dayOfMonth) {
                Log.d("DDD!",String.valueOf(year));
                Log.d("DDD!",String.valueOf(month));
                Log.d("DDD!",String.valueOf(dayOfMonth));

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.HOUR_OF_DAY, dayOfMonth);
                Log.d("DDD!",String.valueOf(calendar.getTime()));


            }
        });





    }
    public String loadPrefer(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        String score = sharedPreferences.getString("idUser","b15dcat103");
        return score;

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

               try {

                   JSONObject jsonObject = new JSONObject();
                   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                   String selectedDate = sdf.format(calendar.getTime());
                   Log.d("DDD", String.valueOf(selectedDate));
                   jsonObject.put("date", selectedDate);
                   jsonObject.put("idMonhoc", spinner.getSelectedItemPosition());
                   jsonObject.put("soccer",editTarget.getText());
                   jsonObject.put("maSv", loadPrefer());



                   post_data pd = new post_data("http://192.168.1.16:3000/targetPost", jsonObject);
                   pd.execute();

                   startActivity(new Intent(setAlarm.this,list_item_target.class));
               }catch (Exception e) {
                   e.printStackTrace();
               }

            }
        });
    }
}
