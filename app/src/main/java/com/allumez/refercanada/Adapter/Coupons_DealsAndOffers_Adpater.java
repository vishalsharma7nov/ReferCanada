package com.allumez.refercanada.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.allumez.refercanada.GetterAndSetter.Setting_Deals_And_Offers;
import com.allumez.refercanada.LoginActivity;
import com.allumez.refercanada.R;

import java.util.List;


public class Coupons_DealsAndOffers_Adpater extends BaseAdapter{

    Context c;
    List<Setting_Deals_And_Offers> list;

    public Coupons_DealsAndOffers_Adpater(Context c, List<Setting_Deals_And_Offers> list )
    {
        this.c=c;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView( int position, View convertView, ViewGroup parent) {
        LayoutInflater in=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=in.inflate(R.layout.coupons_listing_layout,null);
        TextView t1= convertView.findViewById(R.id.textViewCouponsTitle);
        TextView t2= convertView.findViewById(R.id.textViewCouponsDescription);
        TextView t3= convertView.findViewById(R.id.textViewCouponsLeft);
        TextView t4= convertView.findViewById(R.id.textViewCouponsDiscountPrice);
        TextView t5= convertView.findViewById(R.id.textViewCouponsActualPrice);
        t1.setText(list.get(position).getTitle());
        t2.setText(list.get(position).getDescription());
        t3.setText("Total Coupons Left = "+list.get(position).getTotal_number_of_coupons());
        t4.setText("Price =$"+list.get(position).getDiscounted_price());
        t5.setText("$"+list.get(position).getActual_price());
        Button b1 =convertView.findViewById(R.id.buttonGetDeal);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c.getApplicationContext(), LoginActivity.class);
                c.startActivity(intent);
            }
        });
        return convertView;
    }
}