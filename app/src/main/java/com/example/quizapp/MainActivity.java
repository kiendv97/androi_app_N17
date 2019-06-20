package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button btnTest, btnSetAlarm,btnTarget,btnNoti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getButton();
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
                startActivity(new Intent(MainActivity.this,setAlarm.class));
            }
        });
        btnTarget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,list_item_target.class));
            }
        });
    }
    boolean doubleBackToExitPressedOnce = false;

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
        // builder http connect
        HttpURLConnection urlConnection;
        StringBuilder result = new StringBuilder();
        try {

            URL url = new URL("http://192.168.1.9:3000/test/androi/");
            urlConnection = (HttpURLConnection) url.openConnection();
            Log.d("123456", String.valueOf(urlConnection.getResponseCode()));

            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            Log.d("123456", String.valueOf(urlConnection.getResponseCode()));

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());

            Log.d("123456", in.toString());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            Log.d("Result: ", result.toString());
            in.close();

        }catch (Exception ex) {
            Log.d("BUG-URL", ex.toString());
        }
        finally {
          //  urlConnection.disconnect();
        }

    }
}
