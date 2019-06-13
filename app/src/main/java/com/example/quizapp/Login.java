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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Login extends AppCompatActivity implements CallBackData {
    Button login;
    EditText idUser;
    ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();
    fetch_data ft = new fetch_data("https://api.myjson.com/bins/15zock", null);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.btnLogin);
        idUser = (EditText) findViewById(R.id.idUser);
        ft.delegate = this;
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(idUser.getText().length() > 0) {


                    ft.execute();
                    startActivity(new Intent(Login.this, MainActivity.class));
                    Intent intent = new Intent(Login.this
                            , AlarmNotificationReceiver.class);
                    // API get list target on server
                    for (int i =0 ; i < 10; i++ ){
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(Login.this,i, intent,0);
                        Calendar calendar = Calendar.getInstance();

//                        calendar.set(Calendar.HOUR_OF_DAY,10);
//                        calendar.set(Calendar.MINUTE,13);
                        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                                SystemClock.elapsedRealtime() + 60000 * i,
                                pendingIntent);
                        intentArray.add(pendingIntent);

                    }

                } else Toast.makeText(Login.this, "please input your student_id", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onReceiveData(String data) {
        Log.d("Runner", data);
    }
}
