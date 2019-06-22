package com.example.quizapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = (Button) findViewById(R.id.btnLogin);
        idUser = (EditText) findViewById(R.id.idUser);

        try {

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {


                        if(idUser.getText().toString().equals("b15dcat103") || idUser.getText().toString().equals("b15dcat107")) {
                            Intent intent1 = new Intent(Login.this, MainActivity.class);

                            intent1.putExtra("idUser", idUser.getText().toString());

SavePrefe(idUser.getText().toString());

                            startActivity(intent1);

                        } else Toast.makeText(Login.this, "invalid studentId", Toast.LENGTH_SHORT).show();
                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });

        }catch (Exception e ){
            e.printStackTrace();
        }

    }

    public void SavePrefe(String value) {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
      editor.putString("idUser",value);
      editor.commit();
    }


    @Override
    public void onReceiveData(final String data)  {


    }
}
