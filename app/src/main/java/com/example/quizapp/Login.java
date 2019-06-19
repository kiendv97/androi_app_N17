package com.example.quizapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quizapp.Service.CallBackData;
import com.example.quizapp.Service.fetch_data;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Login extends AppCompatActivity implements CallBackData {
    Button login;
    EditText idUser;
    ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();
    fetch_data ft = new fetch_data("http://192.168.1.16:3000/target",null);
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.btnLogin);
        idUser = (EditText) findViewById(R.id.idUser);
        ft.delegate = this;
        ft.execute();

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
public  void exe( JSONObject json) {



}
    @Override
    public void onReceiveData(String data)  {
            try {
               final  JSONObject json  = new JSONObject(data);
                final JSONArray arrj = new JSONArray(json.get("target").toString());
                Log.d("Runner", json.toString());
                Log.d("Runner", arrj.toString());
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {


                            if(idUser.getText().toString().equals("b15dcat103") || idUser.getText().toString().equals("b15dcat107")) {

                                startActivity(new Intent(Login.this, MainActivity.class));
                                Intent intent = new Intent(Login.this
                                        , AlarmNotificationReceiver.class)   ;





                                // API get list target on server
                                for (int i = 0 ; i < arrj.length(); i++ ){
                                    JSONObject x= new JSONObject(arrj.opt(i).toString());

                                    long miliSecsDate = milliseconds (x.getString("date"));
                                    Log.d("mili",String.valueOf(miliSecsDate));
                                    PendingIntent pendingIntent = PendingIntent.getBroadcast(Login.this,i, intent,0);
                                    AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                                    alarmManager.set(AlarmManager.RTC_WAKEUP,
                                            miliSecsDate,
                                            pendingIntent);
                                    intentArray.add(pendingIntent);
                                }

                            } else Toast.makeText(Login.this, "invalid studentId", Toast.LENGTH_SHORT).show();
                        }catch (Exception e) {

                        }

                    }
                });

            }catch (Exception e ){
                e.printStackTrace();
        }

    }
}
