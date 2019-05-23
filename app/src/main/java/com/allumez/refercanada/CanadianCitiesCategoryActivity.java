package com.allumez.refercanada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class CanadianCitiesCategoryActivity extends AppCompatActivity {

    ListView listViewCitiesCategory;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canadian_cities);
        Intent intent = getIntent();
        String a = String.valueOf(intent.getIntExtra("pos",0));

        url="http://refercanada.com/api/getCategoryList.php";
        Log.e("url",url);
        listViewCitiesCategory = (ListView)findViewById(R.id.listView);
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
                                Toast.makeText(CanadianCitiesCategoryActivity.this, "Work under Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                showJSON(response);
                                listViewCitiesCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                        Toast.makeText(CanadianCitiesCategoryActivity.this, String.valueOf(position+1), Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(CanadianCitiesCategoryActivity.this,CanadianCitiesCategoryListActivity.class);
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
        CanadianCitiesCategoryAdpater ca = new CanadianCitiesCategoryAdpater(this,jsonHolderListing.id,jsonHolderListing.name, jsonHolderListing.image);
        listViewCitiesCategory.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}
