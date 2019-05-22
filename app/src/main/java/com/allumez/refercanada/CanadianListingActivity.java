package com.allumez.refercanada;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CanadianListingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ListView listView;
    CityNameAdpater cityNameAdpater;
    ImageView imageView;
    int mId;

    private List<JsonHolder.DataBean> citydata;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canadian_listing);

        listView  = (ListView)findViewById(R.id.listView);
        imageView = (ImageView)findViewById(R.id.imageView);

        //Creating a retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        Api api = retrofit.create(Api.class);

        //now making the call object
        //Here we are using the api method that we created inside the api interface
        Call<JsonHolder> calling = api.getCities();
        final ProgressDialog loading = ProgressDialog.show(CanadianListingActivity.this,"Loading","Please wait...",false,false);
        calling.enqueue(new Callback<JsonHolder>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<JsonHolder> call, Response<JsonHolder> response) {
                JsonHolder data = response.body();

                List<JsonHolder.DataBean> cities = data.getData();
                String state_url = data.getState_url();

                String id[]     = new String[cities.size()];
                String name[]   = new String[cities.size()];
                String image[]  = new String[cities.size()];

                for (int i = 0; i < cities.size(); i++) {
                    id[i]       =     cities.get(i).getId();
                    name[i]     =     cities.get(i).getName();
                    image[i]    =     state_url+cities.get(i).getImage();
                    loading.dismiss();
                }
//                Picasso.get().load(image[1]).into(imageView);

                ArrayAdapter ar = new ArrayAdapter(CanadianListingActivity.this, android.R.layout.simple_list_item_1,name);
                listView.setAdapter(ar);


            }

            @Override
            public void onFailure(Call<JsonHolder> call, Throwable t) {

            }
        });
//        cityNameAdpater = new CityNameAdpater(CanadianListingActivity.this,R.layout.citynames,citydata);
//        listView.setAdapter(cityNameAdpater);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mId = position;
        final String selectedFromList = (String) (listView.getItemAtPosition(mId));
        Toast.makeText(CanadianListingActivity.this, selectedFromList, Toast.LENGTH_SHORT).show();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position==mId)
                {

                }
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
