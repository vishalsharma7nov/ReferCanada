package com.allumez.refercanada.internationalListing;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.allumez.refercanada.Adapter.Canadian_State_Listing_Adpater;
import com.allumez.refercanada.GetterAndSetter.Setting_Data;
import com.allumez.refercanada.R;
import com.allumez.refercanada.jsonData.JsonHolder_State_Listing;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class International_CategoryList_Activity extends AppCompatActivity {

    ListView listViewInternationalCategoryList,listViewId,listViewSearch;
    String url="http://canada.net.in/api/getinterCategoryList.php";
    JsonHolder_State_Listing jsonHolderListing;
    SearchView searchView;
    List<Setting_Data> settingDataList;
    ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internationalcategorylist);
        listViewInternationalCategoryList= findViewById(R.id.listView);
        listViewId= findViewById(R.id.listViewId);
        listViewSearch= findViewById(R.id.listViewsearch);
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
                                Toast.makeText(International_CategoryList_Activity.this, "Work in Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                showJSON(response);
                                final String[] mId = JsonHolder_State_Listing.id;
                                final ArrayAdapter a = new ArrayAdapter(International_CategoryList_Activity.this,android.R.layout.simple_list_item_1,mId);
                                listViewId.setAdapter(a);
                                final Canadian_State_Listing_Adpater ar = new Canadian_State_Listing_Adpater(getApplicationContext(), settingDataList);
                                listViewSearch.setAdapter(ar);
                                listViewInternationalCategoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(International_CategoryList_Activity.this, International_CategorySubList_Activity.class);
                                        intent.putExtra("pos",ar.filteredData.get(position).getId());
                                        startActivity(intent);
                                    }
                                });
                                listViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(International_CategoryList_Activity.this, International_CategorySubList_Activity.class);
                                        intent.putExtra("pos",ar.filteredData.get(position).getId());
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
                                                    Toast.makeText(International_CategoryList_Activity.this, "No Match found",Toast.LENGTH_LONG).show();
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
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(), "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
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
        jsonHolderListing = new JsonHolder_State_Listing(json);
        settingDataList = jsonHolderListing.parseJSON();
        Canadian_State_Listing_Adpater ca = new Canadian_State_Listing_Adpater(this, settingDataList);
        listViewInternationalCategoryList.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}
