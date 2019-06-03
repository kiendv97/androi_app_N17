package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class LessonActivity  extends AppCompatActivity {

    ListView lvLesson;
    ArrayList<LessonLibary> lessonLibaries;
    lessonAdapter lessonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_lesson);

    convert();
    lessonAdapter  = new lessonAdapter(this,R.layout.layout_lesson,lessonLibaries);
        lvLesson.setAdapter(lessonAdapter);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LessonActivity.this, MainActivity.class);

        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    public void convert(){
        lvLesson = (ListView) findViewById(R.id.listview);
        lessonLibaries =  new ArrayList<LessonLibary>();

        lessonLibaries.add(new LessonLibary("Triết học","30",R.drawable.triethoc));
        lessonLibaries.add(new LessonLibary("Toán rời rạc","20",R.drawable.toanroirac));
        lessonLibaries.add(new LessonLibary("Giám sát mạng","10",R.drawable.giamsatmang));
        lessonLibaries.add(new LessonLibary("Triết học","30",R.drawable.triethoc));
        lessonLibaries.add(new LessonLibary("Toán rời rạc","20",R.drawable.toanroirac));
        lessonLibaries.add(new LessonLibary("Giám sát mạng","10",R.drawable.giamsatmang));
        lessonLibaries.add(new LessonLibary("Triết học","30",R.drawable.triethoc));
        lessonLibaries.add(new LessonLibary("Toán rời rạc","20",R.drawable.toanroirac));
        lessonLibaries.add(new LessonLibary("Giám sát mạng","10",R.drawable.giamsatmang));
        lessonLibaries.add(new LessonLibary("Phân tích thiết kế hệ thống","100",R.drawable.dientuso));


    }
}
