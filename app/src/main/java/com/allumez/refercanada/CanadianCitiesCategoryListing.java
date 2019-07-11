package com.allumez.refercanada;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class CanadianCitiesCategoryListing extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;

    ImageView imageView;
    ListView listViewListing;
    String url;
    JsonHolderCategoryListing jsonHolderListing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canadian_cities_category_listing);

        listViewListing  = findViewById(R.id.listviewListing);

        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        String stateId = bb.getString("stateId", "stateId");
        String cityId = bb.getString("cityId", "cityId");
        String categoryId = bb.getString("categoryId", "categoryId");
        String subcategoryId = bb.getString("subcategoryId", "subcategoryId");

        url = "http://refercanada.com/api/getListing.php?stateId="+stateId+"&cityId="+cityId+"&categoryId="+categoryId+"&subcategoryId="+subcategoryId;

        sendRequest();
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        else
        {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }
    }

    private void sendRequest() {
        final ProgressDialog loading = ProgressDialog.show(this,"Loading","Please wait...",false,false);

        StringRequest stringRequest = new StringRequest(url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("status"));


                            if (abc !=1 )
                            {
                                Toast.makeText(CanadianCitiesCategoryListing.this, "Work under Progress....", Toast.LENGTH_SHORT).show();
                                loading.dismiss();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                showJSON(response);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(CanadianCitiesCategoryListing.this, "Exception"+e, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json) {
        JsonHolderCategoryListing jsonHolderListing = new JsonHolderCategoryListing(json);
        jsonHolderListing.parseJSON();


        CanadianCitiesCategoryListingAdapter ca = new CanadianCitiesCategoryListingAdapter(this, JsonHolderCategoryListing.id, JsonHolderCategoryListing.cover_image, JsonHolderCategoryListing.listing_name, JsonHolderCategoryListing.address, JsonHolderCategoryListing.phone, JsonHolderCategoryListing.email);
        listViewListing.setAdapter(ca);

        ca.notifyDataSetChanged();
    }

}
