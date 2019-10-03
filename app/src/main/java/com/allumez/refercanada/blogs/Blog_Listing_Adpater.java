package com.allumez.refercanada.blogs;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.allumez.refercanada.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


public class Blog_Listing_Adpater extends BaseAdapter{

    Context c;
    public static String[] id;
    public static String[] title;
    public static String[] description;
    public static String[] image;
    public static String[] meta_key;
    public static String[] meta_description;



    public Blog_Listing_Adpater(Context c,
                                String[] id,
                                String[] title,
                                String[] description,
                                String[] image,
                                String[] meta_key ,
                                String[] meta_description)
    {
        this.c=c;
        Blog_Listing_Adpater.id = id;
        Blog_Listing_Adpater.title = title;
        Blog_Listing_Adpater.description = description;
        Blog_Listing_Adpater.meta_key = meta_key;
        Blog_Listing_Adpater.meta_description = meta_description;
        Blog_Listing_Adpater.image = image;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater in=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView=in.inflate(R.layout.blog_listing,null);


        ImageView i1= convertView.findViewById(R.id.imageViewBlogImage);

        TextView t1= convertView.findViewById(R.id.textViewBlogTitle);
        TextView t2= convertView.findViewById(R.id.textViewBlogDescription);

        LinearLayout lo1 = convertView.findViewById(R.id.linearLayoutbloglisting);
        lo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, Blog_Detail.class);
                intent.putExtra("blogId",id[position]);
                c.startActivity(intent);
            }
        });

        Button b1 = convertView.findViewById(R.id.buttonBlogReadMore);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, Blog_Detail.class);
                intent.putExtra("blogId",id[position]);
                c.startActivity(intent);
            }
        });


        String url= "http://canada.net.in/uploads/blogs_img/"+image[position];
        Glide.with(c)
                .load(url)
                .addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Toast.makeText(c, "Image Loading Failed!!", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Toast.makeText(c, "Image Loading Finished!!!", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .into(i1);


        for (int i = 0;i<id.length;i++)
        {
            t1.setText(title[position]);
            t2.setText(description[position]);

        }
        return convertView;
    }
}