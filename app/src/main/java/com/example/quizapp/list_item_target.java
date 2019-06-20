package com.example.quizapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.quizapp.Service.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class list_item_target extends AppCompatActivity implements CallBackData {
    fetch_data ft = new fetch_data("http://192.168.1.16:3000/target",null);
    ListView listView;
    JSONObject data;
    ArrayList<Target> arrayList;
    TargetAdapter targetAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item_target);
        ft.delegate = this;
        ft.execute();
        // convert();

    }

    public void convert() throws  Exception{


        arrayList = new ArrayList<Target>();
        Log.d("json1",data.toString());
        JSONArray arrj = new JSONArray(data.get("target").toString());
        for (int i =0 ;i < arrj.length(); i ++ ) {
            JSONObject x= new JSONObject(arrj.opt(i).toString());
            arrayList.add(new Target(x.getString("date"), x.getString("lesson"), x.getInt("soccer"), 4));


        }
        targetAdapter = new TargetAdapter(this,R.layout.list_item,arrayList);
        listView = (ListView) findViewById(R.id.recyclerView);
        listView.setAdapter(targetAdapter);

    }



    @Override
    public void onReceiveData(String data) throws Exception {
    this.data = new JSONObject(data);
        convert();
    }


    public void addCalendar(View view) {
        Intent intent = new Intent(getBaseContext(), setAlarm.class);
        startActivity(intent);
    }
}
