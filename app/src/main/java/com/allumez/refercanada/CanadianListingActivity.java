package com.allumez.refercanada;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class CanadianListingActivity extends AppCompatActivity{

    ListView listView,listViewId;
    JsonHolderListing jsonHolderListing;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canadian_listing);

        listView    = (ListView)findViewById(R.id.listView);
        listViewId  = (ListView)findViewById(R.id.listViewId);


        sendRequest();
    }

    private void sendRequest() {
        final ProgressDialog loading = ProgressDialog.show(CanadianListingActivity.this,"Loading","Please wait...",false,false);

        StringRequest stringRequest = new StringRequest("http://refercanada.com/api/getStateList.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        loading.dismiss();
                        try {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("status"));

                            if (abc !=1 )
                            {
                                Toast.makeText(CanadianListingActivity.this, "Work in Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc ==1)
                            {
                                showJSON(response);
                                final String mId[] = jsonHolderListing.id;
                                final ArrayAdapter a = new ArrayAdapter(CanadianListingActivity.this,android.R.layout.simple_list_item_1,mId);
                                listViewId.setAdapter(a);
//
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        String selectedId = String.valueOf(a.getItem(position));
//                                        Log.e("===mi",selectedId);
                                        Intent intent = new Intent(CanadianListingActivity.this,CanadianCitiesActivity.class);
                                        intent.putExtra("pos",selectedId);
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
        jsonHolderListing = new JsonHolderListing(json);
        jsonHolderListing.parseJSON();
        CanadianListingAdpater ca = new CanadianListingAdpater(this,jsonHolderListing.id,jsonHolderListing.name, jsonHolderListing.image);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();

    }

}
