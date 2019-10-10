package com.allumez.refercanada.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.allumez.refercanada.GetterAndSetter.Setting_Coupons_Details_Data;
import com.allumez.refercanada.LoginActivity;
import com.allumez.refercanada.R;

import java.util.ArrayList;
import java.util.List;


public class Coupons_Details_Adpater extends BaseAdapter implements Filterable {

    public Coupons_Details_Adpater.ItemFilter mFilter = new Coupons_Details_Adpater.ItemFilter();
    Context c;
    List<Setting_Coupons_Details_Data> list;
    List<Setting_Coupons_Details_Data> filteredData;

    public Coupons_Details_Adpater(Context c, List<Setting_Coupons_Details_Data> list )
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
        return filteredData.get(position).getTitle();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater in=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=in.inflate(R.layout.coupons_listing_layout,null);
        TextView t1= convertView.findViewById(R.id.textViewCouponsTitle);
        TextView t2= convertView.findViewById(R.id.textViewCouponsDescription);
        TextView t3= convertView.findViewById(R.id.textViewCouponsLeft);
        TextView t4= convertView.findViewById(R.id.textViewCouponsDiscountPrice);
        TextView t5= convertView.findViewById(R.id.textViewCouponsActualPrice);
        t1.setText(filteredData.get(position).getTitle());
        t2.setText(filteredData.get(position).getDescription());
        t3.setText("Total Coupons Left = "+filteredData.get(position).getTotal_number_of_coupons());
        t4.setText("Price =$"+filteredData.get(position).getDiscounted_price());
        t5.setText("$"+filteredData.get(position).getActual_price());
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

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();

            final List<Setting_Coupons_Details_Data> list1 = list;

            int count = list1.size();
            final ArrayList<Setting_Coupons_Details_Data> nlist = new ArrayList<Setting_Coupons_Details_Data>(count);

            Setting_Coupons_Details_Data filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = list1.get(i);
                if (filterableString.getTitle().toLowerCase().contains(filterString)) {
                    nlist.add(filterableString);
                }
            }

            results.values = nlist;
            results.count = nlist.size();

            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredData = (ArrayList<Setting_Coupons_Details_Data>) results.values;
            notifyDataSetChanged();
        }

    }

}