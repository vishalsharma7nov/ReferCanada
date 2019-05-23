package com.allumez.refercanada;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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

    ListView listView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canadian_listing);

        listView  = (ListView)findViewById(R.id.listView);

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
                            Log.e("===", String.valueOf(abc));
                            if (abc !=1 )
                            {
                                Toast.makeText(CanadianListingActivity.this, "Work under Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc ==1)
                            {
                                showJSON(response);
                                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                        Toast.makeText(CanadianListingActivity.this, String.valueOf(position+1), Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(CanadianListingActivity.this,CanadianCitiesActivity.class);
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
        CanadianListingAdpater ca = new CanadianListingAdpater(this,jsonHolderListing.id,jsonHolderListing.name, jsonHolderListing.image,jsonHolderListing.status);
        listView.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}
