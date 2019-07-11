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
import android.widget.Toast;

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
        CanadianCitiesCategoryAdpater.id = id;
        CanadianCitiesCategoryAdpater.name = name;
        CanadianCitiesCategoryAdpater.image = image;
        CanadianCitiesCategoryAdpater.icon = icon;
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

        TextView t1= convertView.findViewById(R.id.textViewName);
        ImageView i3= convertView.findViewById(R.id.imageViewCity);

        String url=null;
        for (int i = 0;i<image.length;i++)
        {
            t1.setText(name[position]);
            url= "http://refercanada.com/uploads/category_img/"+icon[position];

            Glide.with(c)
                    .load(url)
                    .optionalCircleCrop()
                    .centerCrop()
                    .addListener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Toast.makeText(c, "Error While Loading Image!!!", Toast.LENGTH_SHORT).show();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            Toast.makeText(c, "Image Loading Finished!!!", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                    })
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(i3);
        }

        return convertView;
    }
}