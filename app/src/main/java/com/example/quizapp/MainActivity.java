package com.example.quizapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.quizapp.Service.CallBackData;
import com.example.quizapp.Service.fetch_data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements CallBackData {
    Button btnTest, btnSetAlarm,btnTarget,btnNoti;
    ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getButton();
        try {
            String scrope = loadPrefer();
            if(!getIntent().getStringExtra("idUser").equals("")) {
                fetch_data ft = new fetch_data("http://192.168.1.16:3000/target/" + scrope,null);

                ft.delegate = this;
                ft.execute();
            }
        }catch(Exception e) {
            e.printStackTrace();
        }


        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Exam", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, LessonActivity.class));
            }
        });
        btnNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "profile", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,profile_activity.class);
                intent.putExtra("idUser",getIntent().getStringExtra("idUser"));
                startActivity(intent);
            }
        });
        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "set alarm", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,setAlarm.class);
                intent.putExtra("idUser",getIntent().getStringExtra("idUser"));
                startActivity(intent);
               // startActivity(new Intent(MainActivity.this,setAlarm.class));
            }
        });
        btnTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "target", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,list_item_target.class);
                intent.putExtra("idUser",getIntent().getStringExtra("idUser"));
                startActivity(intent);
              //  startActivity(new Intent(MainActivity.this,list_item_target.class));
            }
        });
    }
    boolean doubleBackToExitPressedOnce = false;
    public String loadPrefer(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        String score = sharedPreferences.getString("idUser","b15dcat103");
        return score;

    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            moveTaskToBack(true);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
    public  void getButton(){
        btnTest = (Button) findViewById(R.id.btnTest);
        btnSetAlarm = (Button) findViewById(R.id.btnBook);
        btnTarget = (Button) findViewById(R.id.btnTarget);
        btnNoti = (Button) findViewById(R.id.btnNoti);


    }

    @Override
    public void onReceiveData(String data) throws Exception {
handleNoti(data);
    }
    void handleNoti(String data) throws Exception {
        final JSONObject json  = new JSONObject(data);
        final JSONArray arrj = new JSONArray(json.get("target").toString());
        Log.d("Runner", json.toString());
        Log.d("Runner", arrj.toString());
        Intent intent = new Intent(MainActivity.this
                , AlarmNotificationReceiver.class)   ;
        for (int i = 0 ; i < arrj.length(); i++ ){
            JSONObject x= new JSONObject(arrj.opt(i).toString());

            long miliSecsDate = milliseconds (x.getString("date"));
            Log.d("mili",String.valueOf(miliSecsDate));
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,i, intent,0);
            AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP,
                    miliSecsDate,
                    pendingIntent);
            intentArray.add(pendingIntent);
        }
    }
    public long milliseconds(String date)
    {
        //String date_ = date;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date mDate = sdf.parse(date);
            long timeInMilliseconds = mDate.getTime();
            Log.d("Date in milli :: " , String.valueOf(timeInMilliseconds));
            return timeInMilliseconds;
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return 0;
    }
}
