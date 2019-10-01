package com.allumez.refercanada.canadianListing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.allumez.refercanada.R;
import com.allumez.refercanada.jsonData.JsonHolder_State_Listing;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Canadian_Cities_CategoryList_Activity extends AppCompatActivity {

    ListView listViewCitiesCategoryList;
    String url;
    ListView listViewId,listViewSearch;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canadian_cities);
        Intent intent = getIntent();
        String a = intent.getStringExtra("pos");

        url="http://refercanada.com/api/getSubCategoryList.php?categoryId="+a;
        Log.e("url",url);
        listViewCitiesCategoryList = findViewById(R.id.listView);

        listViewId  = findViewById(R.id.listViewId);
        listViewSearch = findViewById(R.id.listViewsearch);
        searchView = findViewById(R.id.searchview);

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchView.setIconified(false);
            }
        });

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
                                Toast.makeText(Canadian_Cities_CategoryList_Activity.this, "Work under Progress....", Toast.LENGTH_SHORT).show();
                                loading.dismiss();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                showJSON(response);

                                final String[] mId = JsonHolder_State_Listing.id;

                                final ArrayAdapter a = new ArrayAdapter(Canadian_Cities_CategoryList_Activity.this,android.R.layout.simple_list_item_1,mId);
                                listViewId.setAdapter(a);


                                listViewCitiesCategoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                        String selectedId = String.valueOf(a.getItem(position));
                                        Intent intent = new Intent(Canadian_Cities_CategoryList_Activity.this, Canadian_Cities_CategoryListing_Activity.class);
                                        intent.putExtra("pos",position);
                                        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
                                        SharedPreferences.Editor edit = prefs.edit();
                                        edit.putString("subcategoryId", selectedId);
                                        edit.commit();
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
        JsonHolder_State_Listing jsonHolderListing = new JsonHolder_State_Listing(json);
        jsonHolderListing.parseJSON();
        Canadian_Cities_CategoryList_Adpater ca = new Canadian_Cities_CategoryList_Adpater(this, JsonHolder_State_Listing.id, JsonHolder_State_Listing.name, JsonHolder_State_Listing.image);
        listViewCitiesCategoryList.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}
