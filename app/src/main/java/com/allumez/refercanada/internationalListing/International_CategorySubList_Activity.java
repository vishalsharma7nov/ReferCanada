package com.allumez.refercanada.internationalListing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.allumez.refercanada.Adapter.Canadian_Cities_CategoryList_Adpater;
import com.allumez.refercanada.R;
import com.allumez.refercanada.jsonData.JsonHolder_State_Listing;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class International_CategorySubList_Activity extends AppCompatActivity {

    ListView listViewCitiesCategoryList;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_international_category_sub_list);
        Intent intent = getIntent();
        String a = intent.getStringExtra("pos");
        url="http://canada.net.in/api/getinterSubCategoryList.php?categoryId="+a;
        Log.e("url",url);
        listViewCitiesCategoryList = findViewById(R.id.listView);
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
                                Toast.makeText(International_CategorySubList_Activity.this, "Work in Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                               loading.dismiss();
                               showJSON(response);
                            }
                        } catch (JSONException e) {
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(), "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Error "+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json) {
        JsonHolder_State_Listing jsonHolderListing = new JsonHolder_State_Listing(json);
        jsonHolderListing.parseJSON();
        Canadian_Cities_CategoryList_Adpater ca = new Canadian_Cities_CategoryList_Adpater(this, JsonHolder_State_Listing.id, JsonHolder_State_Listing.name, JsonHolder_State_Listing.image);
        listViewCitiesCategoryList.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}