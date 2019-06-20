package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.example.quizapp.Service.CallBackData;
import com.example.quizapp.Service.fetch_data;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LessonActivity  extends AppCompatActivity implements CallBackData {
    JSONObject data;
    ListView lvLesson;
    ArrayList<LessonLibary> lessonLibaries;
    lessonAdapter lessonAdapter;
    fetch_data ft ;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lesson);
       ft = new fetch_data("http://192.168.1.16:3000/monhoc",null);
      ft.delegate = this;
      ft.execute();
    //  convert();



    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LessonActivity.this, MainActivity.class);

        startActivity(intent);
        finish();
        super.onBackPressed();
    }
    @Override
    public void onReceiveData(String data) throws Exception {
        this.data = new JSONObject(data);

        Log.d("JSON",  this.data.toString());
        convert();
    }
    public void convert(){
        try {

            lessonLibaries =  new ArrayList<LessonLibary>();
            Log.d("JSON1",data.toString());
            JSONArray arrj = new JSONArray(data.get("monhoc").toString());
for (int i =0 ;i < arrj.length(); i ++ ) {
    JSONObject x= new JSONObject(arrj.opt(i).toString());

        lessonLibaries.add(new LessonLibary(x.getString("nameLesson"),x.getString("amount"),  getResources().getIdentifier(x.getString("picture"), "drawable", getPackageName())));

}


            lessonAdapter  = new lessonAdapter(this,R.layout.layout_lesson,lessonLibaries);
            lvLesson = (ListView) findViewById(R.id.listview);
            lvLesson.setAdapter(lessonAdapter);

        }catch (Exception e) {
            e.printStackTrace();
        }



    }


}
