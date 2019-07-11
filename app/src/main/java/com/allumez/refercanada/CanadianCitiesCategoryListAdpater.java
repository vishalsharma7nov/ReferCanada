package com.allumez.refercanada;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


public class CanadianCitiesCategoryListAdpater extends BaseAdapter{

    Context c;
    public static String[] id;
    public static String[] name;
    public static String[] image;

    public CanadianCitiesCategoryListAdpater(Context c, String[] id, String[] name, String[] image)
    {
        this.c=c;
        CanadianCitiesCategoryListAdpater.id = id;
        CanadianCitiesCategoryListAdpater.name = name;
        CanadianCitiesCategoryListAdpater.image = image;
    }

    @Override
    public int getCount() {
        return id.length;
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
        LayoutInflater in=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView=in.inflate(R.layout.citynames,null);


        TextView t1= convertView.findViewById(R.id.textViewName);
        for (int i = 0;i<image.length;i++)
        {
            t1.setText(name[position]);
        }
        return convertView;
    }
}