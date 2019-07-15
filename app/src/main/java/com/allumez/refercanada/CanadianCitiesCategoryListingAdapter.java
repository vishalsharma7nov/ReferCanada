package com.allumez.refercanada;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import static android.content.Context.MODE_PRIVATE;


public class CanadianCitiesCategoryListingAdapter extends BaseAdapter{

    Context c;
    public static String[] id;
    public static String[] business_id;
    public static String[] listing_name;
    public static String[] cover_image;
    public static String[] phone;
    public static String[] email;
    public static String[] address;



    public CanadianCitiesCategoryListingAdapter(Context c, String[] id, String[] cover_image,String[] business_id, String[] listing_name, String[] address, String[] phone, String[] email)
    {
        this.c=c;
        this.id = id;
        this.business_id= business_id;
        this.cover_image = cover_image;
        this.listing_name = listing_name;
        this.address = address;
        this.phone = phone;
        this.email = email;
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

        convertView=in.inflate(R.layout.listing,null);

        final ProgressDialog loading = ProgressDialog.show(c,"Loading Images","Please wait...",false,false);

        CardView card1 = convertView.findViewById(R.id.listing);
        ImageView i1 = convertView.findViewById(R.id.imageViewCoverImage);
        TextView t1  = convertView.findViewById(R.id.textViewListingName);
        TextView t2  = convertView.findViewById(R.id.textViewAddress);
        TextView t3  = convertView.findViewById(R.id.textViewPhone);
        TextView t4  = convertView.findViewById(R.id.textViewEmail);
        Button   b1  = convertView.findViewById(R.id.buttonOffers);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, CanadianCitiesFullListingActivity.class);
                SharedPreferences prefs = c.getSharedPreferences("my_prefs", MODE_PRIVATE);
                SharedPreferences.Editor edit = prefs.edit();
                edit.putString("listId", id[position]);
                edit.putString("businessId", business_id[position]);
                edit.commit();
                c.startActivity(intent);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, CanadianCitiesFullListingActivity.class);
                SharedPreferences prefs = c.getSharedPreferences("my_prefs", MODE_PRIVATE);
                SharedPreferences.Editor edit = prefs.edit();
                edit.putString("listId", id[position]);
                edit.putString("businessId", business_id[position]);
                edit.commit();
                c.startActivity(intent);
            }
        });

        String url = "http://refercanada.com/uploads/listing_img/"+cover_image[position];
            Glide.with(c)
                    .load(url)
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

                    .into(i1);
            t1.setText(listing_name[position]);
            t2.setText(address[position]);
            t3.setText(phone[position]);
            t4.setText(email[position]);

        return convertView;
    }
}