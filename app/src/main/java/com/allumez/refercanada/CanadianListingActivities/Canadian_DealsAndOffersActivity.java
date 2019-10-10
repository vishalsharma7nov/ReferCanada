package com.allumez.refercanada.CanadianListingActivities;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.allumez.refercanada.Adapter.Coupons_DealsAndOffers_Adpater;
import com.allumez.refercanada.GetterAndSetter.Setting_Deals_And_Offers;
import com.allumez.refercanada.R;
import com.allumez.refercanada.jsonData.JsonHolder_DealsAndOffers;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import static android.widget.Toast.makeText;

public class Canadian_DealsAndOffersActivity extends AppCompatActivity {
    protected String API,listId,businessId,url;
    protected ListView listViewDealsAndOffers;
    protected List<Setting_Deals_And_Offers> settingDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canadian__deals_and_offers);
        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        listId = bb.getString("listId", "listId");
        API = "http://canada.net.in/api/getCouponOfListing.php?listing="+listId;
        listViewDealsAndOffers = findViewById(R.id.listViewDealsAndOffers);
        sendRequest();
        Log.e("===dealsAndOffers",API);
    }
    private void sendRequest() {
        final ProgressDialog loading = ProgressDialog.show(this, "Loading", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(API,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("status"));
                            if (abc != 1)
                            {
                                loading.dismiss();
                                Toast.makeText(getApplicationContext(), "Application Under Maintenance!!"+response, Toast.LENGTH_SHORT).show();
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
                            makeText(getApplicationContext(), "Application Under Maintenance!!"+e.getMessage(), Toast.LENGTH_SHORT).show();
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
        JsonHolder_DealsAndOffers jsonHolder_dealsAndOffers = new JsonHolder_DealsAndOffers(json);
        settingDataList=jsonHolder_dealsAndOffers.parseJSON();
        Coupons_DealsAndOffers_Adpater coupons_dealsAndOffers_adpater = new Coupons_DealsAndOffers_Adpater(this,settingDataList);
        listViewDealsAndOffers.setAdapter(coupons_dealsAndOffers_adpater);
    }

}
