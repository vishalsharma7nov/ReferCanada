package com.allumez.refercanada;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;


public class CanadianListingAdpater extends BaseAdapter implements Filterable
{

    Context c;
    public static String[] id;
    public static String[] name;
    public static String[] image;
    TextView t2;
    String mID;

    List<SettingData> list;
    List<SettingData> filteredData;

    public ItemFilter mFilter = new ItemFilter();

    public CanadianListingAdpater(Context c, List<SettingData> list )
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
        return filteredData.get(position).getName();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater in=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        convertView=in.inflate(R.layout.citynames,null);


        TextView t1= convertView.findViewById(R.id.textViewName);
        t2= convertView.findViewById(R.id.textViewId);

            t1.setText(filteredData.get(position).getName());
            t2.setText(filteredData.get(position).getId());

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

            final List<SettingData> list1 = list;

            int count = list1.size();
            final ArrayList<SettingData> nlist = new ArrayList<SettingData>(count);

            SettingData filterableString ;

            for (int i = 0; i < count; i++) {
                filterableString = list1.get(i);
                if (filterableString.getName().toLowerCase().contains(filterString)) {
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
            filteredData = (ArrayList<SettingData>) results.values;
            notifyDataSetChanged();
        }

    }
}