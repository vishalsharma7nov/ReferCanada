package com.allumez.refercanada.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.allumez.refercanada.CanadianListingActivities.Canadian_Cities_FullListing_Activity;
import com.allumez.refercanada.GetterAndSetter.Setting_Data_RecyclerView;
import com.allumez.refercanada.R;
import com.allumez.refercanada.jsonData.JsonHolder_Category_Listing;
import com.bumptech.glide.Glide;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerViewTopRated extends RecyclerView.Adapter<RecyclerViewTopRated.MyHolder> {

    List<Setting_Data_RecyclerView> objList;
    LayoutInflater inflater;
    ImageView im;
    TextView textView;

    public RecyclerViewTopRated(Context c, List<Setting_Data_RecyclerView> obj)
    {
        inflater = LayoutInflater.from(c);
        objList = obj;
    }
    @NonNull
    @Override
    public RecyclerViewTopRated.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.image_slider_layout_item,viewGroup,false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyHolder myHolder, final int i) {
           textView.setText(JsonHolder_Category_Listing.jsonDataList.get(i).getListing_name());
           Glide.with(myHolder.itemView.getContext())
                   .load("http://canada.net.in/uploads/listing_img/"+JsonHolder_Category_Listing.jsonDataList.get(i).getCover_image())
                   .fitCenter()
                   .into(im);
           im.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent = new Intent(myHolder.itemView.getContext(), Canadian_Cities_FullListing_Activity.class);
                   SharedPreferences prefs = myHolder.itemView.getContext().getSharedPreferences("my_prefs", MODE_PRIVATE);
                   SharedPreferences.Editor edit = prefs.edit();
                   edit.putString("listId", JsonHolder_Category_Listing.jsonDataList.get(i).getId());
                   edit.putString("businessId", JsonHolder_Category_Listing.jsonDataList.get(i).getBusiness_id());
                   edit.commit();
                   myHolder.itemView.getContext().startActivity(intent);
               }
           });
           Setting_Data_RecyclerView current = JsonHolder_Category_Listing.jsonDataList.get(i);
           myHolder.setData(current,i);
        }

    @Override
    public int getItemCount() {
        return JsonHolder_Category_Listing.jsonDataList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder
    {
        int position;
        Setting_Data_RecyclerView currentobj;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            im = itemView.findViewById(R.id.iv_auto_image_slider);
            textView = itemView.findViewById(R.id.tv);
        }
        public void setData(Setting_Data_RecyclerView current, int i) {
            this.position = i;
            currentobj = current;
        }
    }
}
