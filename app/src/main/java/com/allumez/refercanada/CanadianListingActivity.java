package com.allumez.refercanada;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class CanadianListingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    ListView listView,listViewId,listViewSpinner,listViewSearch;
    JsonHolderListing jsonHolderListing;
    SearchView searchView;
    ArrayList<String> list;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canadian_listing);

        listView    = (ListView)findViewById(R.id.listView);
        listViewId  = (ListView)findViewById(R.id.listViewId);
        listViewSearch = (ListView)findViewById(R.id.listViewsearch);
        searchView = (SearchView)findViewById(R.id.searchview);



        sendRequest();
    }

    private void sendRequest() {
        final ProgressDialog loading = ProgressDialog.show(this,"Loading","Please wait...",false,false);

        StringRequest stringRequest = new StringRequest("http://refercanada.com/api/getStateList.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("status"));

                            if (abc !=1 )
                            {
                                Toast.makeText(CanadianListingActivity.this, "Work in Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc ==1)
                            {
                                loading.dismiss();
                                showJSON(response);
                                final String[] mId = jsonHolderListing.id;
                                final ArrayAdapter a = new ArrayAdapter(CanadianListingActivity.this,android.R.layout.simple_list_item_1,mId);
                                listViewId.setAdapter(a);

                                final ArrayAdapter ar = new ArrayAdapter(CanadianListingActivity.this,android.R.layout.simple_list_item_1,jsonHolderListing.name);
                                listViewSearch.setAdapter(ar);

                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        String selectedId = String.valueOf(a.getItem(position));
                                        Intent intent = new Intent(CanadianListingActivity.this,CanadianCitiesActivity.class);
                                        intent.putExtra("pos",selectedId);
                                        startActivity(intent);


                                    }
                                });
                                listViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        String selectedId = String.valueOf(a.getItem(position));
                                        Intent intent = new Intent(CanadianListingActivity.this,CanadianCitiesActivity.class);
                                        intent.putExtra("pos",selectedId);
                                        startActivity(intent);

                                    }
                                });

                                searchView.setOnSearchClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        listView.setVisibility(View.GONE);
                                        listViewSearch.setVisibility(View.VISIBLE);
                                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                            @Override
                                            public boolean onQueryTextSubmit(String query) {
                                                if(list.contains(query)){
                                                    ar.getFilter().filter(query);
                                                }else{
                                                    Toast.makeText(CanadianListingActivity.this, "No Match found",Toast.LENGTH_LONG).show();
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
                                        listView.setVisibility(View.VISIBLE);
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
        jsonHolderListing = new JsonHolderListing(json);
        jsonHolderListing.parseJSON();
        CanadianListingAdpater ca = new CanadianListingAdpater(this,jsonHolderListing.id,jsonHolderListing.name, jsonHolderListing.image);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String selectedFromList = (String) (listViewSpinner.getItemAtPosition(position));
        String selectedId = (String) (listViewId.getItemAtPosition(position));
        Log.e("===Selected id",selectedId);
        Intent intent = new Intent(CanadianListingActivity.this,CanadianCitiesActivity.class);
        intent.putExtra("pos",selectedId);
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
