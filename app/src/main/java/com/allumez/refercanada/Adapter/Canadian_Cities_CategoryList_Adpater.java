package com.allumez.refercanada.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.allumez.refercanada.R;


public class Canadian_Cities_CategoryList_Adpater extends BaseAdapter{

    Context c;
    protected static String[] id;
    protected static String[] name;
    protected static String[] image;

    public Canadian_Cities_CategoryList_Adpater(Context c, String[] id, String[] name, String[] image)
    {
        this.c=c;
        Canadian_Cities_CategoryList_Adpater.id = id;
        Canadian_Cities_CategoryList_Adpater.name = name;
        Canadian_Cities_CategoryList_Adpater.image = image;
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
        t1.setText(name[position]);

        return convertView;
    }
}