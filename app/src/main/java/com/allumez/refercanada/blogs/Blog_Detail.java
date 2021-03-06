package com.allumez.refercanada.blogs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.allumez.refercanada.R;
import com.allumez.refercanada.jsonData.JsonHolder_Blog_Detail;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.json.JSONException;
import org.json.JSONObject;

public class Blog_Detail extends AppCompatActivity {

    protected String url;
    protected TextView textViewBlogDetailTitle,textViewBlogDetailDescription;
    protected ImageView imageViewBlogDetailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_full_detail);
        textViewBlogDetailTitle       = findViewById(R.id.textViewBlogDetailTitle);
        textViewBlogDetailDescription = findViewById(R.id.textViewBlogDetailDescription);
        imageViewBlogDetailImage      = findViewById(R.id.imageViewBlogDetailImage);
        Intent intent = getIntent();
        String blogId = intent.getStringExtra("blogId");
        url = "http://canada.net.in/api/getBlogDetail.php?blogId="+blogId;
        Log.e("==urlBlog",url);
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
                                Toast.makeText(Blog_Detail.this, "Work in Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                showJSON(response);
                                String title = obj.getJSONObject("data").getString("title");
                                String description = obj.getJSONObject("data").getString("description");
                                String image = obj.getJSONObject("data").getString("image");
                                textViewBlogDetailTitle.setText(title);
                                textViewBlogDetailDescription.setText(description);
                                String imageUrl= "http://refercanada.com/uploads/blogs_img/"+image;
                                final ProgressDialog loading = ProgressDialog.show(Blog_Detail.this,"Loading Images","Please wait...",false,true);
                                Glide.with(Blog_Detail.this)
                                        .load(imageUrl)
                                        .centerCrop()
                                        .addListener(new RequestListener<Drawable>() {
                                            @Override
                                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                                loading.dismiss();
                                                return false;
                                            }

                                            @Override
                                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                                loading.dismiss();
                                                return false;
                                            }
                                        })
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(imageViewBlogDetailImage);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.dismiss();
                            Toast.makeText(getApplicationContext(), "Error "+e.getMessage(), Toast.LENGTH_LONG).show();
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
        JsonHolder_Blog_Detail jsonHolderBlogDetail = new JsonHolder_Blog_Detail(json);
        jsonHolderBlogDetail.parseJSON();
    }
}