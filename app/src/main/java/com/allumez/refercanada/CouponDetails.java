package com.allumez.refercanada;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class CouponDetails extends AppCompatActivity {

    ListView listViewCouponsListing;
    List<SettingCouponsDetailsData> settingCouponsDetailsData;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon_details);
        listViewCouponsListing = findViewById(R.id.listViewCouponsListing);

        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        String stateId = bb.getString("stateId", "stateId");
        String cityId = bb.getString("cityId", "cityId");
        String categoryId = bb.getString("categoryIdCoupon", "categoryIdCoupon");

        url = "http://refercanada.com/api/getCouponFilterListing.php/?categoryId="+categoryId+"&stateId="+stateId+"&cityId="+cityId;
//        Log.e("==CouponsListing",url);
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
                                Toast.makeText(getApplicationContext(), "Work in Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                showJSON(response);
                                Toast.makeText(getApplicationContext(), "Coupons!!", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json) {
        JsonHolderCouponsListing jsonHolderCouponsListing = new JsonHolderCouponsListing(json);
        settingCouponsDetailsData = jsonHolderCouponsListing.parseJSON();

        CouponsDetailsAdpater couponsDetailsAdpater = new CouponsDetailsAdpater(this, settingCouponsDetailsData);
        listViewCouponsListing.setAdapter(couponsDetailsAdpater);

    }
}
