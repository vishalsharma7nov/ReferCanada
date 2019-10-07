package com.allumez.refercanada.CanadianListingActivities;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.allumez.refercanada.Adapter.Canadian_Cities_FullListing_Adapter;
import com.allumez.refercanada.R;
import com.allumez.refercanada.jsonData.JsonHolder_FullListing;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static android.widget.Toast.makeText;

public class Canadian_ProductAndServicesActivity extends AppCompatActivity {
    protected ListView listViewBusinessInformation;
    protected String listId,businessId,url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canadian__product_and_services);
        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        listId = bb.getString("listId", "listId");
        businessId = bb.getString("businessId","businessId");
        url = "http://canada.net.in/api/getListingDetail.php?listingId="+listId;
        listViewBusinessInformation = findViewById(R.id.listViewBusinessInformation);
        sendRequest();
    }
    private void sendRequest() {
        final ProgressDialog loading = ProgressDialog.show(this, "Loading", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("status"));
                            if (abc != 1)
                            {
                                loading.dismiss();
                                Toast.makeText(getApplicationContext(), "Work in Progress...."+response, Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                showJSON(response);
                            }
                        }
                        catch (JSONException e)
                        {
                            loading.dismiss();
                            makeText(getApplicationContext(), "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
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
        JsonHolder_FullListing jsonHolderFullListing = new JsonHolder_FullListing(json);
        jsonHolderFullListing.parseJSON();
        Canadian_Cities_FullListing_Adapter fullListingAdapter = new Canadian_Cities_FullListing_Adapter(this, JsonHolder_FullListing.title, JsonHolder_FullListing.product_image, JsonHolder_FullListing.discount, JsonHolder_FullListing.price, JsonHolder_FullListing.features);
        listViewBusinessInformation.setAdapter(fullListingAdapter);
    }
}
