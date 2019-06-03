package com.example.quizapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class lessonAdapter extends BaseAdapter {
    private  LessonActivity lessonActivity ;
    private Context context;
    private int layout;
    private List<LessonLibary> lessonLibaries;

    public lessonAdapter(Context context, int layout, List<LessonLibary> lessonLibaries) {
        this.context = context;
        this.layout = layout;
        this.lessonLibaries = lessonLibaries;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getLayout() {
        return layout;
    }

    public void setLayout(int layout) {
        this.layout = layout;
    }

    public List<LessonLibary> getLessonLibaries() {
        return lessonLibaries;
    }

    public void setLessonLibaries(List<LessonLibary> lessonLibaries) {
        this.lessonLibaries = lessonLibaries;
    }

    public lessonAdapter() {
    }

    @Override
    public int getCount() {
        return lessonLibaries.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private  class ViewHolder {
        ImageView image;
        TextView txtLesson, txtDes;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
    ViewHolder holder = new ViewHolder();
     if(view == null ){
         LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         view  = inflater.inflate(layout,null);
         // anh xa view
        holder.txtLesson = (TextView) view.findViewById(R.id.textLesson);
         holder.txtDes = (TextView) view.findViewById(R.id.textDesciption);
         holder.image = (ImageView) view.findViewById(R.id.image);

          view.setTag(holder);

 } else {
    holder = (ViewHolder) view.getTag();

     }
        LessonLibary lessonLibary =  lessonLibaries.get(i);
        holder.txtLesson.setText(lessonLibary.getNameLesson());
        holder.txtDes.setText(lessonLibary.getAmount());
        holder.image.setImageResource(lessonLibary.getPicture());
        holder.txtLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AllOnClick(v);
            }
        });
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AllOnClick(v);
            }
        });
        holder.txtDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AllOnClick(v);
            }
        });

        return view;
    }
    public void AllOnClick(View v){
      //  Toast.makeText(v.getContext(),"next screen", Toast.LENGTH_SHORT).show();
        context.startActivity(new Intent(v.getContext(),amount_to_test.class));

    }
}
