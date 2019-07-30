package com.allumez.refercanada;

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

public class Canadian_Cities_FullListing_Adapter extends BaseAdapter {

    Context c;
    public static String[] id;
    public static String[] title;
    public static String[] product_image;
    public static String[] detail;
    public static String[] price;
    public static String[] features;
    public static String[] show_product;
    public static String[] show_price;
    public static String[] discount;

    public Canadian_Cities_FullListing_Adapter(Context c, String[] title, String[] product_image, String[]discount, String[] price, String[] features)
    {
        this.c = c;
        Canadian_Cities_FullListing_Adapter.title = title;
        Canadian_Cities_FullListing_Adapter.product_image = product_image;
        Canadian_Cities_FullListing_Adapter.discount = discount;
        Canadian_Cities_FullListing_Adapter.price = price;
        Canadian_Cities_FullListing_Adapter.features = features;

    }

    @Override
    public int getCount() {
        return title.length;
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
        view=in.inflate(R.layout.full_listing,null);

       TextView textViewTitle           = view.findViewById(R.id.textViewTitle);
       TextView textViewDiscount        = view.findViewById(R.id.textViewDiscount);
       TextView textViewPrice           = view.findViewById(R.id.textViewPrice);
       TextView textViewFeatures        = view.findViewById(R.id.textViewFeatures);

        textViewTitle.setText(title[i]);
        textViewDiscount.setText("Offer Price="+discount[i]);
        textViewPrice.setText(price[i]);
        textViewFeatures.setText(features[i]);

        ImageView i1= view.findViewById(R.id.imageViewGallery);
        String url= "http://refercanada.com/uploads/product_img/"+product_image[i];

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
                .into(i1);

        return view;
    }
}
