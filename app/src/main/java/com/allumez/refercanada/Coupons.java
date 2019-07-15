package com.allumez.refercanada;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Coupons extends AppCompatActivity {

    SearchView searchView;
    ArrayList<String> list;
    ListView listViewId,listViewSearch;
    List<SettingCategoryData> settingDataList;
    String url;
    GridView listViewCitiesCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);

//        listViewCitiesCategory = findViewById(R.id.listView);
        listViewId  = findViewById(R.id.listViewId);
        listViewSearch = findViewById(R.id.listViewsearch);
        searchView = findViewById(R.id.searchview);

        GridView gridview = findViewById(R.id.listView);
        gridview.setAdapter(new GridViewAdapter(this));

        url = "http://refercanada.com/api/getCategoryList.php";
//        sendRequest();

    }

//    private void sendRequest() {
//        final ProgressDialog loading = ProgressDialog.show(this,"Loading","Please wait...",false,false);
//
//        StringRequest stringRequest = new StringRequest(url,
//                new com.android.volley.Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            JSONObject obj = new JSONObject(response);
//                            int abc = Integer.parseInt(obj.getString("status"));
//
//                            if (abc !=1 )
//                            {
//                                loading.dismiss();
//                                Toast.makeText(getApplicationContext(), "Work under Progress....", Toast.LENGTH_SHORT).show();
//                            }
//                            else if (abc == 1)
//                            {
//                                loading.dismiss();
//                                showJSON(response);
//                                final String[] mId = JsonHolderCitiesCategory.id;
//
//                                final ArrayAdapter a = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,mId);
//                                listViewId.setAdapter(a);
//
//
//                                final CouponsCategoryAdpater ar = new CouponsCategoryAdpater(getApplicationContext(), settingDataList);
//                                listViewSearch.setAdapter(ar);
//
//
//                                listViewCitiesCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                    @Override
//                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                                    }
//                                });
//                                listViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                    @Override
//                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//                                    }
//                                });
//                                searchView.setOnSearchClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        searchView.setIconified(false);
//                                        listViewCitiesCategory.setVisibility(View.GONE);
//                                        listViewSearch.setVisibility(View.VISIBLE);
//                                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                                            @Override
//                                            public boolean onQueryTextSubmit(String query) {
//                                                if(list.contains(query)){
//                                                    ar.getFilter().filter(query);
//                                                }else{
//                                                    Toast.makeText(getApplicationContext(), "No Match found",Toast.LENGTH_LONG).show();
//                                                }
//                                                return false;
//                                            }
//                                            @Override
//                                            public boolean onQueryTextChange(String newText) {
//                                                ar.getFilter().filter(newText);
//                                                return false;
//                                            }
//                                        });
//                                    }
//                                });
//                                searchView.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        searchView.setIconified(false);
//                                        listViewCitiesCategory.setVisibility(View.GONE);
//                                        listViewSearch.setVisibility(View.VISIBLE);
//                                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//                                            @Override
//                                            public boolean onQueryTextSubmit(String query) {
//                                                if(list.contains(query)){
//                                                    ar.getFilter().filter(query);
//                                                }else{
//                                                    Toast.makeText(getApplicationContext(), "No Match found",Toast.LENGTH_LONG).show();
//                                                }
//                                                return false;
//                                            }
//                                            @Override
//                                            public boolean onQueryTextChange(String newText) {
//                                                ar.getFilter().filter(newText);
//                                                return false;
//                                            }
//                                        });
//                                    }
//                                });
//                                searchView.setOnCloseListener(new SearchView.OnCloseListener() {
//                                    @Override
//                                    public boolean onClose() {
//                                        listViewCitiesCategory.setVisibility(View.VISIBLE);
//                                        listViewSearch.setVisibility(View.GONE);
//                                        return false;
//                                    }
//                                });
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                });
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }
//
//    private void showJSON(String json) {
//        JsonHolderCitiesCategory jsonHolderCitiesCategory= new JsonHolderCitiesCategory(json);
//        settingDataList = jsonHolderCitiesCategory.parseJSON();
//
////        CouponsCategoryAdpater ca = new CouponsCategoryAdpater(this, settingDataList);
////        listViewCitiesCategory.setAdapter(ca);
////        ca.notifyDataSetChanged();
//    }

}
