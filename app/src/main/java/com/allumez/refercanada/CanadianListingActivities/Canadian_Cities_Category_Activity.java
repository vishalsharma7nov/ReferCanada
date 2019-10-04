package com.allumez.refercanada.CanadianListingActivities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.allumez.refercanada.Adapter.Canadian_Cities_Category_Adpater;
import com.allumez.refercanada.GetterAndSetter.Setting_Category_Data;
import com.allumez.refercanada.R;
import com.allumez.refercanada.jsonData.JsonHolder_Cities_Category;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Canadian_Cities_Category_Activity extends AppCompatActivity {

    ListView listViewCitiesCategory,listViewId,listViewSearch;
    String url;
    JsonHolder_Cities_Category jsonHolderCitiesCategory;
    SearchView searchView;
    ArrayList<String> list;
    List<Setting_Category_Data> settingDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canadian_cities_category);
        url="http://canada.net.in/api/getCategoryList.php";
        listViewCitiesCategory = findViewById(R.id.listView);
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
                                loading.dismiss();
                                Toast.makeText(Canadian_Cities_Category_Activity.this, "Work under Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                showJSON(response);
                                final String[] mId = JsonHolder_Cities_Category.id;
                                final ArrayAdapter a = new ArrayAdapter(Canadian_Cities_Category_Activity.this,android.R.layout.simple_list_item_1,mId);
                                listViewId.setAdapter(a);
                                final Canadian_Cities_Category_Adpater ar = new Canadian_Cities_Category_Adpater(getApplicationContext(), settingDataList);
                                listViewSearch.setAdapter(ar);
                                listViewCitiesCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(Canadian_Cities_Category_Activity.this, Canadian_Cities_CategoryList_Activity.class);
                                        intent.putExtra("pos",ar.filteredData.get(position).getId());
                                        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
                                        SharedPreferences.Editor edit = prefs.edit();
                                        edit.putString("categoryId", ar.filteredData.get(position).getId());
                                        edit.commit();
                                        startActivity(intent);
                                    }
                                });
                                listViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        Intent intent = new Intent(Canadian_Cities_Category_Activity.this, Canadian_Cities_CategoryList_Activity.class);
                                        intent.putExtra("pos",ar.filteredData.get(position).getId());
                                        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
                                        SharedPreferences.Editor edit = prefs.edit();
                                        edit.putString("categoryId", ar.filteredData.get(position).getId());
                                        edit.commit();
                                        startActivity(intent);
                                    }
                                });
                                searchView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        searchView.setIconified(false);
                                        listViewCitiesCategory.setVisibility(View.GONE);
                                        listViewSearch.setVisibility(View.VISIBLE);
                                        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                            @Override
                                            public boolean onQueryTextSubmit(String query) {
                                                if(list.contains(query)){
                                                    ar.getFilter().filter(query);
                                                }else{
                                                    Toast.makeText(Canadian_Cities_Category_Activity.this, "No Match found",Toast.LENGTH_LONG).show();
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
                                        listViewCitiesCategory.setVisibility(View.VISIBLE);
                                        listViewSearch.setVisibility(View.GONE);
                                        return false;
                                    }
                                });
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
        jsonHolderCitiesCategory= new JsonHolder_Cities_Category(json);
        settingDataList = jsonHolderCitiesCategory.parseJSON();
        Canadian_Cities_Category_Adpater ca = new Canadian_Cities_Category_Adpater(this, settingDataList);
        listViewCitiesCategory.setAdapter(ca);
        ca.notifyDataSetChanged();
    }
}
