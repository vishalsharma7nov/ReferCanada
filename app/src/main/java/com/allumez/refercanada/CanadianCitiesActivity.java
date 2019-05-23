package com.allumez.refercanada;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CanadianCitiesActivity extends AppCompatActivity {
    ListView listViewCities;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canadian_cities);
        Intent intent = getIntent();
        String a = String.valueOf(intent.getIntExtra("pos",0)+1);

        url="http://refercanada.com/api/getCityList.php?stateId="+a;
        Log.e("url",url);
        listViewCities = (ListView)findViewById(R.id.listView);
        sendRequest();
    }

    private void sendRequest() {

        StringRequest stringRequest = new StringRequest(url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("status"));
                            Log.e("===", String.valueOf(abc));
                            if (abc !=1 )
                            {
                                Toast.makeText(CanadianCitiesActivity.this, "Work under Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                showJSON(response);
                                listViewCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                        Toast.makeText(CanadianCitiesActivity.this, String.valueOf(position+1), Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(CanadianCitiesActivity.this,CanadianCitiesCategoryActivity.class);
                                        intent.putExtra("pos",position);
                                        startActivity(intent);
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
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
        JsonHolderListing jsonHolderListing = new JsonHolderListing(json);
        jsonHolderListing.parseJSON();
        CanadianCitiesAdpater ca = new CanadianCitiesAdpater(this,jsonHolderListing.id,jsonHolderListing.name, jsonHolderListing.image);
        listViewCities.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}
