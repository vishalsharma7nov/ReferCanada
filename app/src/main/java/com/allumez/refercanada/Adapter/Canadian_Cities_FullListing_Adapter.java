package com.allumez.refercanada.Adapter;

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

import com.allumez.refercanada.GetterAndSetter.Setting_Full_Listing;
import com.allumez.refercanada.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class Canadian_Cities_FullListing_Adapter extends BaseAdapter {

    Context c;
    protected List<Setting_Full_Listing> list;

    public Canadian_Cities_FullListing_Adapter(Context c, List<Setting_Full_Listing> list) {
        this.c = c;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        textViewTitle.setText(list.get(i).getTitle());
        textViewDiscount.setText("Price="+list.get(i).getDiscount());
        textViewPrice.setText(list.get(i).getPrice());
        textViewFeatures.setText(list.get(i).getFeatures());
        ImageView i1= view.findViewById(R.id.imageViewGallery);
        String url= "http://canada.net.in/uploads/product_img/"+list.get(i).getProduct_image();
        Glide.with(c)
                .load(url)
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
