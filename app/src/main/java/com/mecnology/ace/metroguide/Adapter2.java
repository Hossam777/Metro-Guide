package com.mecnology.ace.metroguide;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alexm on 06/04/2018.
 */

public class Adapter2 extends BaseAdapter {

    ArrayList<String>arr=new ArrayList<String>();
    LayoutInflater l;

    public Adapter2(Activity a, ArrayList<String> arr) {
        this.arr = arr;
        l=(LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v=l.inflate(R.layout.card_design,null);
        TextView station=(TextView)v.findViewById(R.id.station);
        TextView up=(TextView)v.findViewById(R.id.up);
        TextView down=(TextView)v.findViewById(R.id.down);
        if(arr.get(position).equals("الشهداء") || arr.get(position).equals("السادات") || arr.get(position).equals("العتبه"))
        {
            up.setBackgroundColor(R.color.changebackground);
            down.setBackgroundColor(R.color.changebackground);
        }
        station.setText(arr.get(position));
        return v;
    }
}
