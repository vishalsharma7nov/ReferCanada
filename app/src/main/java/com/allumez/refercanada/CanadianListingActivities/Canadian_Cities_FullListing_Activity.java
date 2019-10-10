package com.allumez.refercanada.CanadianListingActivities;

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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.allumez.refercanada.Adapter.Canadian_Cities_FullListing_Adapter;
import com.allumez.refercanada.Adapter.ReviewListingAdapter;
import com.allumez.refercanada.GetterAndSetter.Setting_Data_RecyclerView;
import com.allumez.refercanada.GetterAndSetter.Setting_Full_Listing;
import com.allumez.refercanada.R;
import com.allumez.refercanada.jsonData.JsonHolder_Category_Listing;
import com.allumez.refercanada.jsonData.JsonHolder_FullListing;
import com.allumez.refercanada.jsonData.JsonHolder_Reviews;
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
import java.util.List;
import java.util.Map;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class Canadian_Cities_FullListing_Activity extends AppCompatActivity  {

    protected boolean doubleBackToExitPressedOnce = false;
    protected TextView textViewListingName,textViewLandmark,textViewDescription;
    protected ImageButton imageButtonMail,imageButtonPhone,imageButtonAddress,imageButtonSms;
    protected String url,urlForReviews;
    protected ListView listViewBusinessInformation,listViewUsersReviews;
    protected String reviewAPI,ratingBarUserReview,listId,businessId,listing_name,landmark;
    protected EditText editTextFullName,editTextMobile,editTextEmailId,editTextCity,editTextReview;
    protected Button buttonSubmitButton,buttonDealsAndOffers,buttonProductAndService;
    protected RatingBar ratingBarReview;
    protected ImageView imageViewCoverImage;
    protected List<Setting_Full_Listing> settingDataList;
    protected List<Setting_Data_RecyclerView> address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canadian_cities_full_listing);
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        }
        imageViewCoverImage = findViewById(R.id.imageViewCoverImage);
        textViewListingName          = findViewById(R.id.textViewListingName);
        textViewDescription          = findViewById(R.id.textViewDescription);
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
        buttonDealsAndOffers = findViewById(R.id.buttonDealsAndOffers);
        buttonProductAndService = findViewById(R.id.buttonProductAndService);
        buttonDealsAndOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Canadian_DealsAndOffersActivity.class);
                startActivity(intent);
            }
        });
        buttonProductAndService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Canadian_ProductAndServicesActivity.class);
                startActivity(intent);
            }
        });
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
        imageButtonMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{JsonHolder_Category_Listing.jsonDataList.get(0).getEmail()});
                startActivity(emailIntent);
            }
        });
        final String address = JsonHolder_Category_Listing.jsonDataList.get(0).getAddress()+", "+ JsonHolder_Category_Listing.jsonDataList.get(0).getLandmark();
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
                        "tel", JsonHolder_Category_Listing.jsonDataList.get(0).getPhone(), null));
                startActivity(phoneIntent);
            }
        });
        imageButtonSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:" + JsonHolder_Category_Listing.jsonDataList.get(0).getPhone()));
                startActivity(sendIntent);
            }
        });
        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        listId = bb.getString("listId", "listId");
        businessId = bb.getString("businessId","businessId");
        listing_name = bb.getString("listing_name","listing_name");
        landmark = bb.getString("landmark","landmark");
        url = "http://canada.net.in/api/getListingDetail.php?listingId="+listId;
        listViewBusinessInformation = findViewById(R.id.listViewBusinessInformation);
        listViewUsersReviews = findViewById(R.id.listViewUsersReviews);
        reviewAPI = "http://canada.net.in/api/addreview.php?";
        ratingBarReview = findViewById(R.id.ratingbarUserReview);
        urlForReviews = "http://canada.net.in/api/getReviews.php?listingId="+listId;
        textViewListingName.setText(listing_name);
        textViewLandmark.setText(landmark);
        textViewDescription.setText(landmark);
        sendRequest();
        sendRequestReviews();
        Log.e("===url",url);
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
                            String description = obj.getJSONObject("data").getString("description");
                            if (abc != 1)
                            {
                                loading.dismiss();
                                Toast.makeText(getApplicationContext(), "Work in Progress...."+response, Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                showJSON(response);
                                textViewDescription.setText(description);
                                String imageUrl = "http://canada.net.in/uploads/listing_img/"+image;
                                Glide.with(getApplicationContext())
                                        .load(imageUrl)
                                        .addListener(new RequestListener<Drawable>() {
                                            @Override
                                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                                Toast.makeText(getApplicationContext(), "Error While Loading Image!!!", Toast.LENGTH_SHORT).show();
                                                return false;
                                            }
                                            @Override
                                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
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
                            makeText(getApplicationContext(), "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
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
                                makeText(Canadian_Cities_FullListing_Activity.this, "Reviewed Successful!!!", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            makeText(getApplicationContext(), "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        makeText(getApplicationContext(), "Error "+error.getMessage(), LENGTH_LONG).show();
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
                                Toast.makeText(getApplicationContext(), "No Reviews!!....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                showJSONReview(response);
                            }
                        } catch (JSONException e) {
                            loading.dismiss();
                            makeText(getApplicationContext(), "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void showJSONReview(String json) {
        JsonHolder_Reviews jsonHolderReviews = new JsonHolder_Reviews(json);
        jsonHolderReviews.parseJSON();
        ReviewListingAdapter reviewListingAdapter = new ReviewListingAdapter(this, JsonHolder_Reviews.name, JsonHolder_Reviews.comment, JsonHolder_Reviews.created_date, JsonHolder_Reviews.rating);
        listViewUsersReviews.setAdapter(reviewListingAdapter);
    }
    private void showJSON(String json) {
        JsonHolder_FullListing jsonHolderFullListing = new JsonHolder_FullListing(json);
        settingDataList=jsonHolderFullListing.parseJSON();
        Canadian_Cities_FullListing_Adapter fullListingAdapter = new Canadian_Cities_FullListing_Adapter(this, settingDataList);
        listViewBusinessInformation.setAdapter(fullListingAdapter);
    }
}
