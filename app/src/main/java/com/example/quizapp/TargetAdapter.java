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

import org.w3c.dom.Text;

import java.util.List;

public class TargetAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Target> targetList;

    public TargetAdapter( Context context, int layout, List<Target> targetList) {
        this.context = context;
        this.layout = layout;
        this.targetList = targetList;
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

    public List<Target> getTargetList() {
        return targetList;
    }

    public void setTargetList(List<Target> targetList) {
        this.targetList = targetList;
    }

    @Override
    public int getCount() {
        return targetList.size();
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
        TextView txtLesson, txtTarget, txtDate;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if(view == null ){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view  = inflater.inflate(layout,null);
            // anh xa view
            holder.txtDate = (TextView) view.findViewById(R.id.txtDate);
            holder.txtLesson = (TextView) view.findViewById(R.id.txtLesson);
            holder.txtTarget = (TextView) view.findViewById(R.id.txtTarget);
            holder.image = (ImageView) view.findViewById(R.id.imageResource);

            view.setTag(holder);

        } else {
            holder = (TargetAdapter.ViewHolder) view.getTag();

        }
        Target target =  targetList.get(i);
        holder.txtDate.setText(target.getDate());
        // https://stackoverflow.com/questions/20988905/long-press-on-selected-text-in-textview-in-android/20988922
      //  holder.txtDate.setOnLongClickListener();
        holder.txtLesson.setText(target.getLesson());
        holder.txtTarget.setText(Integer.toString(target.getSoccer()));
//        holder.image.setImageResource(target.getImageResource());


        return view;
    }

}
