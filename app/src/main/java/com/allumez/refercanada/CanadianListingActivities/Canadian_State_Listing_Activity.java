package com.allumez.refercanada.CanadianListingActivities;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.allumez.refercanada.Adapter.Canadian_State_Listing_Adpater;
import com.allumez.refercanada.GetterAndSetter.Setting_Data;
import com.allumez.refercanada.R;
import com.allumez.refercanada.jsonData.JsonHolder_State_Listing;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Canadian_State_Listing_Activity extends AppCompatActivity {

    protected ListView listView, listViewId, listViewSearch;
    protected JsonHolder_State_Listing jsonHolderListing;
    protected SearchView searchView;
    protected ArrayList<String> list;
    protected List<Setting_Data> settingDataList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canadian_listing);
        listView = findViewById(R.id.listView);
        listViewId = findViewById(R.id.listViewId);
        listViewSearch = findViewById(R.id.listViewsearch);
        searchView = findViewById(R.id.searchview);
        sendRequest();
    }

    private void sendRequest() {
        final ProgressDialog loading = ProgressDialog.show(this, "Loading", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest("http://canada.net.in/api/getStateList.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("status"));
                            if (abc != 1) {
                                Toast.makeText(Canadian_State_Listing_Activity.this, "Application Under Maintenance!!", Toast.LENGTH_SHORT).show();
                            } else if (abc == 1) {
                                loading.dismiss();
                                showJSON(response);
                                final String[] mId = JsonHolder_State_Listing.id;
                                final ArrayAdapter a = new ArrayAdapter(Canadian_State_Listing_Activity.this, android.R.layout.simple_list_item_1, mId);
                                listViewId.setAdapter(a);
                                final Canadian_State_Listing_Adpater ar = new Canadian_State_Listing_Adpater(getApplicationContext(), settingDataList);
                                listViewSearch.setAdapter(ar);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(Canadian_State_Listing_Activity.this, Canadian_Cities_Activity.class);
                                        intent.putExtra("pos", ar.filteredData.get(position).getId());
                                        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
                                        SharedPreferences.Editor edit = prefs.edit();
                                        edit.putString("stateId", ar.filteredData.get(position).getId());
                                        edit.commit();
                                        startActivity(intent);
                                    }
                                });
                                listViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                        String x = settingDataList.get(position).getId();
                                        Intent intent = new Intent(Canadian_State_Listing_Activity.this, Canadian_Cities_Activity.class);
                                        intent.putExtra("pos",ar.filteredData.get(position).getId());
                                        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
                                        SharedPreferences.Editor edit = prefs.edit();
                                        edit.putString("stateId", ar.filteredData.get(position).getId());
                                        edit.commit();
                                        startActivity(intent);
                                    }
                                });
                                searchView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        listView.setVisibility(View.GONE);
                                        searchView.setIconified(false);
                                        listViewSearch.setVisibility(View.VISIBLE);
                                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                            @Override
                                            public boolean onQueryTextSubmit(String query) {
                                                if (list.contains(query)) {
                                                    ar.getFilter().filter(query);

                                                } else {
                                                    Toast.makeText(Canadian_State_Listing_Activity.this, "No Match found", Toast.LENGTH_LONG).show();
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
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(), "Application Under Maintenance!!"+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Application Under Maintenance!!"+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json) {
        jsonHolderListing = new JsonHolder_State_Listing(json);
        settingDataList = jsonHolderListing.parseJSON();
        Canadian_State_Listing_Adpater ca = new Canadian_State_Listing_Adpater(this, settingDataList);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}
