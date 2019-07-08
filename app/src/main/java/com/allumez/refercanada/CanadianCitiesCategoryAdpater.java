package com.allumez.refercanada;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
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


public class CanadianCitiesCategoryAdpater extends BaseAdapter{

    Context c;
    public static String[] id;
    public static String[] name;
    public static String[] image;
    public static String[] icon;

    public CanadianCitiesCategoryAdpater(Context c, String[] id, String[] name, String[] image, String[] icon)
    {
        this.c=c;
        this.id         = id;
        this.name       = name;
        this.image      = image;
        this.icon      = icon;
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

        convertView=in.inflate(R.layout.services,null);

        final ProgressDialog loading = ProgressDialog.show(c,"Loading Images","Please wait...",false,false);
        TextView t1=(TextView)convertView.findViewById(R.id.textViewName);
        ImageView t3=(ImageView) convertView.findViewById(R.id.imageViewCity);

        String url=null;
        for (int i = 0;i<image.length;i++)
        {
            t1.setText(name[position]);
            url= "http://refercanada.com/uploads/category_img/"+icon[position];

            Glide.with(c)
                    .load(url)
                    .centerCrop()
                    .addListener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            loading.dismiss();
                            return false;
                        }
                    })
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(t3);
        }


        return convertView;
    }
}