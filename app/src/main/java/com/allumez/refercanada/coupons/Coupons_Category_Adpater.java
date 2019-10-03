package com.allumez.refercanada.coupons;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.allumez.refercanada.R;
import com.allumez.refercanada.GetterAndSetter.Setting_Coupons_Category_Data;
import com.allumez.refercanada.CanadianListingActivities.Canadian_State_Name;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class Coupons_Category_Adpater extends BaseAdapter implements Filterable{

    Context c;
    public static String[] id;
    public static String[] name;
    public static String[] image;
    public static String[] icon;
    public static String[] coupon_icon;

    public Coupons_Category_Adpater.ItemFilter mFilter = new Coupons_Category_Adpater.ItemFilter();
    List<Setting_Coupons_Category_Data> list;
    List<Setting_Coupons_Category_Data> filteredData;

    public Coupons_Category_Adpater(Context c, List<Setting_Coupons_Category_Data> list )
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater in=(LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=in.inflate(R.layout.coupons_layout,null);

        TextView t1= convertView.findViewById(R.id.textViewName);
        TextView t2= convertView.findViewById(R.id.textViewId);

        t1.setText(filteredData.get(position).getName());
        t2.setText(filteredData.get(position).getId());

        ImageView i1= convertView.findViewById(R.id.imageViewCity);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, Canadian_State_Name.class);
                SharedPreferences prefs = c.getSharedPreferences("my_prefs", MODE_PRIVATE);
                SharedPreferences.Editor edit = prefs.edit();
                edit.putString("categoryIdCoupon", filteredData.get(position).getId());
                edit.commit();
                c.startActivity(intent);
            }
        });
        String url= "http://canada.net.in/uploads/category_img/"+filteredData.get(position).getCoupon_icon();
        Glide.with(c)
                    .load(url)
                    .fitCenter()
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

            final List<Setting_Coupons_Category_Data> list1 = list;

            int count = list1.size();
            final ArrayList<Setting_Coupons_Category_Data> nlist = new ArrayList<Setting_Coupons_Category_Data>(count);

            Setting_Coupons_Category_Data filterableString ;

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
            filteredData = (ArrayList<Setting_Coupons_Category_Data>) results.values;
            notifyDataSetChanged();
        }

    }

}