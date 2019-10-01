package com.allumez.refercanada.canadianListing;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.allumez.refercanada.R;
import com.allumez.refercanada.Setting_Data_RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class Canadian_Cities_CategoryListing_Adapter extends BaseAdapter{

    Context c;
    public static String[] id;
    public static String[] business_id;
    public static String[] listing_name;
    public static String[] cover_image;
    public static String[] phone;
    public static String[] email;
    public static String[] address;

    public List<Setting_Data_RecyclerView> filteredData;
    List<Setting_Data_RecyclerView> list;


    public Canadian_Cities_CategoryListing_Adapter(Context c, List<Setting_Data_RecyclerView> list )
    {
        this.c=c;
        this.list = list;
        this.filteredData = list;
    }

    @Override
    public int getCount() {
        return filteredData.size();
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

        convertView=in.inflate(R.layout.business_listing,null);

        final ProgressDialog loading = ProgressDialog.show(c,"Loading Images","Please wait...",false,false);

        CardView card1 = convertView.findViewById(R.id.listing);
        ImageView i1 = convertView.findViewById(R.id.imageViewCoverImage);
        TextView t1  = convertView.findViewById(R.id.textViewListingName);
        TextView t2  = convertView.findViewById(R.id.textViewAddress);
        TextView t3  = convertView.findViewById(R.id.textViewPhone);
        TextView t4  = convertView.findViewById(R.id.textViewEmail);
        TextView t5  = convertView.findViewById(R.id.textViewOffers);
//        Button   b1  = convertView.findViewById(R.id.buttonOffers);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, Canadian_Cities_FullListing_Activity.class);
                SharedPreferences prefs = c.getSharedPreferences("my_prefs", MODE_PRIVATE);
                SharedPreferences.Editor edit = prefs.edit();
                edit.putString("listId", filteredData.get(position).getId());
                edit.putString("businessId", filteredData.get(position).getBusiness_id());
                edit.commit();
                c.startActivity(intent);
            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, Canadian_Cities_FullListing_Activity.class);
                SharedPreferences prefs = c.getSharedPreferences("my_prefs", MODE_PRIVATE);
                SharedPreferences.Editor edit = prefs.edit();
                edit.putString("listId", filteredData.get(position).getId());
                edit.putString("businessId", filteredData.get(position).getBusiness_id());
                edit.commit();
                c.startActivity(intent);
            }
        });

//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(c, Canadian_Cities_FullListing_Activity.class);
//                SharedPreferences prefs = c.getSharedPreferences("my_prefs", MODE_PRIVATE);
//                SharedPreferences.Editor edit = prefs.edit();
//                edit.putString("listId", id[position]);
//                edit.putString("businessId", business_id[position]);
//                edit.commit();
//                c.startActivity(intent);
//            }
//        });

        String url = "http://refercanada.com/uploads/listing_img/"+filteredData.get(position).getCover_image();
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
            t1.setText(filteredData.get(position).getListing_name());
            t2.setText(filteredData.get(position).getAddress());
            t3.setText(filteredData.get(position).getPhone());
            t4.setText(filteredData.get(position).getEmail());

        return convertView;
    }

}