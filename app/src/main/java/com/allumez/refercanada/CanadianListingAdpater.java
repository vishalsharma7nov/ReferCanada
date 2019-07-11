package com.allumez.refercanada;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


public class CanadianListingAdpater extends BaseAdapter{

    Context c;
    public static String[] id;
    public static String[] name;
    public static String[] image;
    TextView t2;
    String mID;

    public CanadianListingAdpater(Context c, String[] id, String[] name, String[] image)
    {
        this.c=c;
        CanadianListingAdpater.id = id;
        CanadianListingAdpater.name = name;
        CanadianListingAdpater.image = image;


    }


    @Override
    public int getCount() {
        return id.length;
    }

    @Override
    public Object getItem(int position) {
        String a = getItem(position).toString();
        Log.e("===id",a);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater in=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView=in.inflate(R.layout.citynames,null);


        TextView t1= convertView.findViewById(R.id.textViewName);
        t2= convertView.findViewById(R.id.textViewId);
//        ImageView t3=(ImageView) convertView.findViewById(R.id.imageViewCity);
        String url=null;
        for (int i = 0;i<image.length;i++)
        {
            t1.setText(name[position]);
            t2.setText(id[position]);
//            url= "http://refercanada.com/uploads/states_img/"+image[position];

//            Glide.with(c)
//                    .load(url)
//                    .centerCrop()
//                    .listener(new RequestListener<Drawable>() {
//                        @Override
//                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            return false;
//                        }
//                    })
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(t3);
        }

        return convertView;
    }

}