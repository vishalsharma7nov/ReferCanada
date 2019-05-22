package com.allumez.refercanada;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Callback;


public class CityNameAdpater extends ArrayAdapter<JsonHolder> {

    //storing all the names in the list
    private List<JsonHolder.DataBean> city;
    private List<JsonHolder> image;
    //context object
    private Context context;


    //constructor
    public CityNameAdpater(CanadianListingActivity context, int resource, List<JsonHolder.DataBean> city) {
        super((Context) context, resource);
        this.city =  city;
        this.context = (Context) context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //getting the layoutinflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //getting listview itmes
        View listViewItem = inflater.inflate(R.layout.citynames, null, true);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        ImageView textViewPhone = (ImageView) listViewItem.findViewById(R.id.textViewPhone);

        //getting the current name
        JsonHolder.DataBean citydata = city.get(position);
        JsonHolder.DataBean imagename = city.get(position);
        JsonHolder imageView = image.get(position);
        //setting the name to textview
        textViewName.setText(citydata.getName());
        Picasso.get().load(imageView.getState_url()).into(textViewPhone);


        return listViewItem;
    }
}