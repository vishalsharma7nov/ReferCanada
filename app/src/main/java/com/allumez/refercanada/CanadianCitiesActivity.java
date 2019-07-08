package com.allumez.refercanada;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CanadianCitiesActivity extends AppCompatActivity {

    ListView listViewCities,listViewSearch;
    String url;
    SearchView searchView;

    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canadian_cities);
        Intent intent = getIntent();
        String a = intent.getStringExtra("pos");

        url="http://refercanada.com/api/getCityList.php?stateId="+a;
        Log.e("url",url);

        listViewCities = (ListView)findViewById(R.id.listView);
        listViewSearch = (ListView)findViewById(R.id.listViewsearch);
        searchView = (SearchView)findViewById(R.id.searchview);

        sendRequest();
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
                                loading.dismiss();
                                Toast.makeText(CanadianCitiesActivity.this, "Work in Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                showJSON(response);

                                final ArrayAdapter ar = new ArrayAdapter(CanadianCitiesActivity.this,android.R.layout.simple_list_item_1, JsonHolderListing.name);
                                listViewSearch.setAdapter(ar);


                                listViewCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
                                        Intent intent = new Intent(CanadianCitiesActivity.this,CanadianCitiesCategoryActivity.class);
                                        intent.putExtra("pos",position);
                                        startActivity(intent);
                                    }
                                });

                                listViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(CanadianCitiesActivity.this,CanadianCitiesCategoryActivity.class);
                                        intent.putExtra("pos",position);
                                        startActivity(intent);


                                    }
                                });

                                searchView.setOnSearchClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        listViewCities.setVisibility(View.GONE);
                                        listViewSearch.setVisibility(View.VISIBLE);
                                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                            @Override
                                            public boolean onQueryTextSubmit(String query) {
                                                if(list.contains(query)){
                                                    ar.getFilter().filter(query);
                                                }else{
                                                    Toast.makeText(CanadianCitiesActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                                                }
                                                return false;
                                            }
                                            @Override
                                            public boolean onQueryTextChange(String newText) {
                                                ar.getFilter().filter(newText);
                                                return false;
                                            }
                                        });
                                    }
                                });
                                searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                                    @Override
                                    public boolean onClose() {
                                        listViewCities.setVisibility(View.VISIBLE);
                                        listViewSearch.setVisibility(View.GONE);
                                        return false;
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
        CanadianCitiesAdpater ca = new CanadianCitiesAdpater(this, JsonHolderListing.id, JsonHolderListing.name, JsonHolderListing.image);
        listViewCities.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}
