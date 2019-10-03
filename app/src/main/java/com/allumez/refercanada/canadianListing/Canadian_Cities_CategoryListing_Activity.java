package com.allumez.refercanada.canadianListing;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.Toast;

import com.allumez.refercanada.R;
import com.allumez.refercanada.RecyclerView.RecyclerViewTopRated;
import com.allumez.refercanada.GetterAndSetter.Setting_Data_RecyclerView;
import com.allumez.refercanada.jsonData.JsonHolder_Category_Listing;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Canadian_Cities_CategoryListing_Activity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    RecyclerView recyclerViewTopRated;
    ListView listViewListing;
    String url;
    List<Setting_Data_RecyclerView> settingDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canadian_cities_category_listing);
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }
        listViewListing  = findViewById(R.id.listviewListing);
        recyclerViewTopRated = findViewById(R.id.recyclerViewTopRated);
        RecyclerViewTopRated myadpt = new RecyclerViewTopRated(this,settingDataList);
        recyclerViewTopRated.setAdapter(myadpt);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewTopRated.setLayoutManager(lm);
        recyclerViewTopRated.setItemAnimator(new DefaultItemAnimator());
        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        String stateId = bb.getString("stateId", "stateId");
        String cityId = bb.getString("cityId", "cityId");
        String categoryId = bb.getString("categoryId", "categoryId");
        String subcategoryId = bb.getString("subcategoryId", "subcategoryId");
        url = "http://refercanada.com/api/getListing.php?stateId="+stateId+"&cityId="+cityId+"&categoryId="+categoryId+"&subcategoryId="+subcategoryId;
        sendRequest();
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        else
        {
            this.doubleBackToExitPressedOnce = true;
            Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce=false;
                }
            }, 2000);
        }
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
                                Toast.makeText(Canadian_Cities_CategoryListing_Activity.this, "Work under Progress....", Toast.LENGTH_SHORT).show();
                                loading.dismiss();
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
        JsonHolder_Category_Listing jsonHolderListing = new JsonHolder_Category_Listing(json);
        settingDataList = jsonHolderListing.parseJSON();
        Canadian_Cities_CategoryListing_Adapter ca = new Canadian_Cities_CategoryListing_Adapter(this, settingDataList);
        listViewListing.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}
