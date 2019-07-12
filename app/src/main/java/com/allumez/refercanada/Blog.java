package com.allumez.refercanada;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Blog extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    String url;
    ListView listViewBlogListing,listViewBlogId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        listViewBlogListing  = findViewById(R.id.listViewBlogListing);
        listViewBlogId       = findViewById(R.id.listViewBlogId);

        url = "http://refercanada.com/api/getBlog.php";

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
                                loading.dismiss();
                                Toast.makeText(Blog.this, "Work in Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                showJSON(response);
                                Toast.makeText(Blog.this, "Blogs", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.dismiss();
                            Toast.makeText(Blog.this, "Error", Toast.LENGTH_SHORT).show();
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
        JsonHolderBlogListing jsonHolderBlogListing = new JsonHolderBlogListing(json);
        jsonHolderBlogListing.parseJSON();

        BlogListingAdpater blogListingAdpater = new BlogListingAdpater(this, JsonHolderBlogListing.id, JsonHolderBlogListing.title, JsonHolderBlogListing.description, JsonHolderBlogListing.image, JsonHolderBlogListing.meta_key, JsonHolderBlogListing.meta_description);
        listViewBlogListing.setAdapter(blogListingAdpater);
        blogListingAdpater.notifyDataSetChanged();
    }

}
