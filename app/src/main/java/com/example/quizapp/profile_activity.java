package com.example.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.quizapp.Service.*;

import org.json.JSONObject;

import java.util.ArrayList;

public class profile_activity extends AppCompatActivity implements CallBackData {
    String hotenS = "Nguyễn văn A";
    String masvS= "B15255255555";
    String sdtS = "0123456789";
    String khoaS="CNTT" ;
    String truongS="PTIT";
    String diachiS="hà nội";
    TextView hoten,masv,sdt, khoa ,truong ,diachi;
    ListView lstPoint;
    ArrayList<PointDTO> lstP = new ArrayList<>();
    JSONObject data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_activity);
        fetch_data ft = new fetch_data("http://192.168.1.16:3000/profile/" + getIntent().getStringExtra("idUser"),null);

        this.setTitle("Profile");
        hoten = (TextView) findViewById(R.id.hoten);
        masv = (TextView) findViewById(R.id.masv);
        sdt = (TextView) findViewById(R.id.sdt);
        khoa = (TextView) findViewById(R.id.khoa);
        truong = (TextView) findViewById(R.id.truong);
        diachi = (TextView) findViewById(R.id.diachi);
        lstPoint = (ListView) findViewById(R.id.lstPoint);


        ft.delegate = this;
        ft.execute();


    }
    public void initData(JSONObject json) throws Exception{
//        lay du lieu do vao view
        lstP.add(new PointDTO("Triết học","10đ"));
        lstP.add(new PointDTO("An toàn mạng","20đ"));
        lstP.add(new PointDTO("Giám sát mạng","30đ"));

       hoten.setText(json.getString("name"));

        masv.setText(json.getString("maSV"));

        sdt.setText(json.getString("sdt"));

        khoa.setText(json.getString("khoa"));

        truong.setText(json.getString("truong"));

        diachi.setText(json.getString("diachi"));

        PointAdapter pointAdapter = new PointAdapter(lstP,this);
        lstPoint.setAdapter(pointAdapter);
    }

    @Override
    public void onReceiveData(String data) throws Exception {
        JSONObject json = new JSONObject(data);
    this.data = new JSONObject(json.toString());
        initData(this.data);
    }
}
