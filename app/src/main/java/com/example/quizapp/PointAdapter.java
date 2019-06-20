package com.example.quizapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PointAdapter  extends BaseAdapter {
    private Context mContext;
    private ArrayList<PointDTO> lstPoint = new ArrayList<>();


    public PointAdapter(ArrayList<PointDTO> arr, Context context) {
        this.lstPoint = arr;
        mContext = context;
    }

    @Override
    public int getCount() {
        return lstPoint.size();
    }

    @Override
    public Object getItem(int arg0) {
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        return 0;
    }

    class ViewHolder {
        TextView testName,point;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        ViewHolder holder;
        View mView = arg1;

        if (mView == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            mView = inflater.inflate(R.layout.item_layout, arg2,
                    false);
            holder = new ViewHolder();
            holder.testName = mView.findViewById(R.id.testName);
            holder.point = mView.findViewById(R.id.point);
            mView.setTag(holder);
        } else {
            holder = (ViewHolder) mView.getTag();
        }
        PointDTO pstn = lstPoint.get(arg0);
        holder.testName.setText(pstn.getTestName());
        holder.point.setText(pstn.getPoint());

        return mView;
    }

}
