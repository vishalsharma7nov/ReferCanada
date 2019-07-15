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
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class ReviewListingAdapter extends BaseAdapter {

    Context c;
    public static String[] id;
    public static String[] listing_id;
    public static String[] business_id;
    public static String[] name;
    public static String[] email;
    public static String[] city;
    public static String[] mobile_number;
    public static String[] comment;
    public static String[] rating;
    public static String[] created_date;

    public ReviewListingAdapter(Context c, String[] name, String[] comment, String[] created_date,String[] rating)
    {
        this.c = c;
        this.name= name;
        this.comment= comment;
        this.created_date = created_date;
        this.rating = rating;

    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater in=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=in.inflate(R.layout.reviews_listing,null);

       TextView textViewUsername        = view.findViewById(R.id.textViewUserName);
       TextView textViewDate            = view.findViewById(R.id.textViewCreatedDate);
       TextView textViewComment         = view.findViewById(R.id.textViewComment);
       RatingBar ratingBar              = view.findViewById(R.id.reviewedRatingBar);


        textViewUsername.setText(name[i]);
        textViewDate.setText(created_date[i]);
        textViewComment.setText(comment[i]);
        ratingBar.setRating(Float.parseFloat(rating[i]));
//        float a = Float.parseFloat(rating[i]);
//        String a = String.valueOf(rating[i]);
        Log.e("===HELLO",name[i]+"\n"+comment[i]+"\n"+created_date[i]);

        return view;
    }
}
