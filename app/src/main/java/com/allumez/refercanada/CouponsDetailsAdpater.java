package com.allumez.refercanada;

import android.content.Context;
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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;


public class CouponsDetailsAdpater extends BaseAdapter implements Filterable {

    public CouponsDetailsAdpater.ItemFilter mFilter = new CouponsDetailsAdpater.ItemFilter();
    Context c;
    List<SettingCouponsDetailsData> list;
    List<SettingCouponsDetailsData> filteredData;

    public CouponsDetailsAdpater(Context c, List<SettingCouponsDetailsData> list )
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

        ImageView i1= convertView.findViewById(R.id.imageViewCouponListing);

        String url= "http://refercanada.com/uploads/coupon_img/"+filteredData.get(position).getImage();
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

            final List<SettingCouponsDetailsData> list1 = list;

            int count = list1.size();
            final ArrayList<SettingCouponsDetailsData> nlist = new ArrayList<SettingCouponsDetailsData>(count);

            SettingCouponsDetailsData filterableString ;

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
            filteredData = (ArrayList<SettingCouponsDetailsData>) results.values;
            notifyDataSetChanged();
        }

    }

}