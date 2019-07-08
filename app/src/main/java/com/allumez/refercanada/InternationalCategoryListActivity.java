package com.allumez.refercanada;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;

public class InternationalCategoryListActivity extends AppCompatActivity {

    ListView listViewInternationalCategoryList,listViewId,listViewSearch;
    String url="http://refercanada.com/api/getinterCategoryList.php";
    JsonHolderListing jsonHolderListing;
    SearchView searchView;

    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;
    final long PERIOD_MS = 3000;


    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internationalcategorylist);







        listViewInternationalCategoryList= (ListView)findViewById(R.id.listView);
        listViewId= (ListView)findViewById(R.id.listViewId);
        listViewSearch= (ListView)findViewById(R.id.listViewsearch);
        searchView = (SearchView)findViewById(R.id.searchview);
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
                                Toast.makeText(InternationalCategoryListActivity.this, "Work in Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                showJSON(response);
                                final String mId[] = jsonHolderListing.id;
                                final ArrayAdapter a = new ArrayAdapter(InternationalCategoryListActivity.this,android.R.layout.simple_list_item_1,mId);
                                listViewId.setAdapter(a);

                                final ArrayAdapter ar = new ArrayAdapter(InternationalCategoryListActivity.this,android.R.layout.simple_list_item_1,jsonHolderListing.name);
                                listViewSearch.setAdapter(ar);

                                listViewInternationalCategoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        String selectedId = String.valueOf(a.getItem(position));
                                        Intent intent = new Intent(InternationalCategoryListActivity.this,InternationalCategorySubListActivity.class);
                                        intent.putExtra("pos",selectedId);
                                        startActivity(intent);


                                    }
                                });
                                listViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        String selectedId = String.valueOf(a.getItem(position));
                                        Intent intent = new Intent(InternationalCategoryListActivity.this,InternationalCategorySubListActivity.class);
                                        intent.putExtra("pos",selectedId);
                                        startActivity(intent);


                                    }
                                });

                                searchView.setOnSearchClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        listViewInternationalCategoryList.setVisibility(View.GONE);
                                        listViewSearch.setVisibility(View.VISIBLE);
                                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                            @Override
                                            public boolean onQueryTextSubmit(String query) {
                                                if(list.contains(query)){
                                                    ar.getFilter().filter(query);
                                                }else{
                                                    Toast.makeText(InternationalCategoryListActivity.this, "No Match found",Toast.LENGTH_LONG).show();
                                                }
                                                return false;
                                            }
                                            @Override
                                            public boolean onQueryTextChange(String newText) {
                                                ar.getFilter().filter(newText);
                                                return false;
                                            }
                                        });
                                    }
                                });
                                searchView.setOnCloseListener(new SearchView.OnCloseListener() {
                                    @Override
                                    public boolean onClose() {
                                        listViewInternationalCategoryList.setVisibility(View.VISIBLE);
                                        listViewSearch.setVisibility(View.GONE);
                                        return false;
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
        CanadianCitiesCategoryListAdpater ca = new CanadianCitiesCategoryListAdpater(this,jsonHolderListing.id,jsonHolderListing.name, jsonHolderListing.image);
        listViewInternationalCategoryList.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}
