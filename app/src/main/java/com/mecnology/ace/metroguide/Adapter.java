package com.mecnology.ace.metroguide;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by alexm on 06/04/2018.
 */

public class Adapter extends BaseAdapter {

    String arr[];
    LayoutInflater l;

    public Adapter(Activity a,String[] arr) {
        this.arr = arr;
        l=(LayoutInflater)a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return arr.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v=l.inflate(R.layout.card_design,null);
        TextView station=(TextView)v.findViewById(R.id.station);
        station.setText(arr[position]);
        return v;
    }
}
