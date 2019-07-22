package com.allumez.refercanada;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
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

import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class CanadianCitiesFullListingActivity extends AppCompatActivity  {


    boolean doubleBackToExitPressedOnce = false;
    TextView textViewListingName,textViewLandmark;
    ImageButton imageButtonMail,imageButtonPhone,imageButtonAddress,imageButtonSms;
    String url,urlForReviews;
    ListView listViewBusinessInformation,listViewUsersReviews;
    String reviewAPI,ratingBarUserReview;
    String listId,businessId;
    EditText editTextFullName,editTextMobile,editTextEmailId,editTextCity,editTextReview;
    Button buttonSubmitButton;
    RatingBar ratingBarReview;
    ImageView imageViewCoverImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canadian_cities_full_listing);

        imageViewCoverImage = findViewById(R.id.imageViewCoverImage);


        textViewListingName          = findViewById(R.id.textViewListingName);
        textViewLandmark             = findViewById(R.id.textViewLandMark);
        imageButtonAddress           = findViewById(R.id.imageButtonLocation);
        imageButtonMail              = findViewById(R.id.imageButtonMail);
        imageButtonPhone             = findViewById(R.id.imageButtonPhone);
        imageButtonSms               = findViewById(R.id.imageButtonSMS);

        editTextFullName        =findViewById(R.id.editTextFullName);
        editTextMobile          =findViewById(R.id.editTextMobile);
        editTextEmailId         =findViewById(R.id.editTextEmailId);
        editTextCity            =findViewById(R.id.editTextCity);
        editTextReview          =findViewById(R.id.editTextReview);

        buttonSubmitButton = findViewById(R.id.buttonSubmitReview);
        buttonSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendingReview();
                ratingBarUserReview = String.valueOf(ratingBarReview.getRating());
                editTextFullName.setText("");
                editTextMobile.setText("");
                editTextEmailId.setText("");
                editTextCity.setText("");
                editTextReview.setText("");
                ratingBarReview.setRating(0);
            }
        });

        textViewListingName.setText(JsonHolderCategoryListing.listing_name[0]);
        textViewLandmark.setText(JsonHolderCategoryListing.landmark[0]);

        imageButtonMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{JsonHolderCategoryListing.email[0]});
                startActivity(emailIntent);
            }
        });

        final String address = JsonHolderCategoryListing.address[0]+", "+ JsonHolderCategoryListing.landmark[0];

        imageButtonAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q="+address);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        imageButtonPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, Uri.fromParts(
                        "tel", JsonHolderCategoryListing.phone[0], null));
                startActivity(phoneIntent);
            }
        });
        imageButtonSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:" + JsonHolderCategoryListing.phone[0]));
                startActivity(sendIntent);
            }
        });

        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        listId = bb.getString("listId", "listId");
        businessId = bb.getString("businessId","businessId");

        url = "http://refercanada.com/api/getListingDetail.php?listingId="+listId;

        listViewBusinessInformation = findViewById(R.id.listViewBusinessInformation);
        listViewUsersReviews = findViewById(R.id.listViewUsersReviews);
        reviewAPI = "http://refercanada.com/api/addreview.php?";

        ratingBarReview = findViewById(R.id.ratingbarUserReview);

        urlForReviews = "http://refercanada.com/api/getReviews.php?listingId="+listId;


        sendRequest();
        sendRequestReviews();

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
        final ProgressDialog loading = ProgressDialog.show(this, "Loading", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("status"));
                            String image = obj.getJSONObject("data").getString("cover_image");
//
                            if (abc != 1)
                            {
                                loading.dismiss();
                                Toast.makeText(getApplicationContext(), "Work in Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                showJSON(response);
                                String imageUrl = "http://refercanada.com/uploads/listing_img/"+image;
                                Glide.with(getApplicationContext())
                                        .load(imageUrl)
                                        .centerCrop()
                                        .addListener(new RequestListener<Drawable>() {
                                            @Override
                                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                                Toast.makeText(getApplicationContext(), "Error While Loading Image!!!", Toast.LENGTH_SHORT).show();
                                                Log.e("===exception",e.getMessage());
                                                return false;
                                            }
                                            @Override
                                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
//                            Toast.makeText(c, "Image Loading Finished!!!", Toast.LENGTH_SHORT).show();
                                                return false;
                                            }
                                        })
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(imageViewCoverImage);


                            }
                        }
                        catch (JSONException e)
                        {
                            loading.dismiss();
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void sendingReview()
    {
        final ProgressDialog loading = ProgressDialog.show(this,"Loading","Please wait...",false,false);

        final String fullname  = editTextFullName.getText().toString();
        final String mobile    = editTextMobile.getText().toString();
        final String email     = editTextEmailId.getText().toString();
        final String city      = editTextCity.getText().toString();
        final String review    = editTextReview.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, reviewAPI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("status"));
                            String loginMsg = obj.getString("msg");

                            if (abc !=1 )
                            {
                                loading.dismiss();
                                makeText(getApplicationContext(), loginMsg, Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                loading.dismiss();
                                makeText(CanadianCitiesFullListingActivity.this, "Reviewed Successful!!!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.dismiss();
                        }
                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        makeText(getApplicationContext(), error.getMessage(), LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", fullname);
                params.put("mobile",mobile);
                params.put("email",email);
                params.put("city",city);
                params.put("comment",review);
                params.put("rating",ratingBarUserReview);
                params.put("business_id",businessId);
                params.put("listingId",listId);

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void sendRequestReviews() {
        final ProgressDialog loading = ProgressDialog.show(this, "Loading", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(urlForReviews,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("status"));

                            if (abc != 1)
                            {
                                loading.dismiss();
                                Toast.makeText(getApplicationContext(), "Work in Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                showJSONReview(response);
                            }
                        } catch (JSONException e) {
                            loading.dismiss();
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSONReview(String json) {
        JsonHolderReviews jsonHolderReviews = new JsonHolderReviews(json);
        jsonHolderReviews.parseJSON();

        ReviewListingAdapter reviewListingAdapter = new ReviewListingAdapter(this, JsonHolderReviews.name, JsonHolderReviews.comment, JsonHolderReviews.created_date, JsonHolderReviews.rating);
        listViewUsersReviews.setAdapter(reviewListingAdapter);

    }


    private void showJSON(String json) {
        JsonHolderFullListing jsonHolderFullListing = new JsonHolderFullListing(json);
        jsonHolderFullListing.parseJSON();

        FullListingAdapter fullListingAdapter = new FullListingAdapter(this, JsonHolderFullListing.title, JsonHolderFullListing.product_image, JsonHolderFullListing.discount, JsonHolderFullListing.price, JsonHolderFullListing.features);
        listViewBusinessInformation.setAdapter(fullListingAdapter);

    }

}
