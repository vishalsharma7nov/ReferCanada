package com.allumez.refercanada;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


public class CanadianCitiesAdpater extends BaseAdapter{

    Context c;
    public static String[] id;
    public static String[] name;
    public static String[] image;


    public CanadianCitiesAdpater(Context c, String[] id, String[] name, String[] image)
    {
        this.c=c;
        this.id         = id;
        this.name       = name;
        this.image      = image;

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


        TextView t1=(TextView)convertView.findViewById(R.id.textViewName);
        ImageView t3=(ImageView) convertView.findViewById(R.id.imageViewCity);


//        Picasso.get().load("http://refercanada.com/uploads/states_img/").into(t3);

        String url=null;
        for (int i = 0;i<image.length;i++)
        {
            t1.setText(name[position]);
            url= "http://refercanada.com/uploads/cities_img/"+image[position];
            Glide.with(c)
                    .load(url)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(t3);
        }


        return convertView;
    }
}