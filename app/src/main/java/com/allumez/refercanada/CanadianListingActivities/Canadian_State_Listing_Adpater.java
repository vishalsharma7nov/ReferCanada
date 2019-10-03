package com.allumez.refercanada.CanadianListingActivities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.allumez.refercanada.R;
import com.allumez.refercanada.GetterAndSetter.Setting_Data;

import java.util.ArrayList;
import java.util.List;


public class Canadian_State_Listing_Adpater extends BaseAdapter implements Filterable
{

    Context c;
    public static String[] id;
    public static String[] name;
    public static String[] image;

    List<Setting_Data> list;
    public List<Setting_Data> filteredData;

    public ItemFilter mFilter = new ItemFilter();

    public Canadian_State_Listing_Adpater(Context c, List<Setting_Data> list )
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
        TextView t2= convertView.findViewById(R.id.textViewId);

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

            final List<Setting_Data> list1 = list;

            int count = list1.size();
            final ArrayList<Setting_Data> nlist = new ArrayList<Setting_Data>(count);

            Setting_Data filterableString ;

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
            filteredData = (ArrayList<Setting_Data>) results.values;
            notifyDataSetChanged();
        }

    }
}