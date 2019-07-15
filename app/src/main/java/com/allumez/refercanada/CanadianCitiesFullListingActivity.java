package com.allumez.refercanada;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class CanadianCitiesFullListingActivity extends AppCompatActivity  {

    final int[] currentPage = {0};
    Timer timer;
    final long DELAY_MS = 1000;
    final long PERIOD_MS = 4000;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canadian_cities_full_listing);

        final ViewPager mViewPager = findViewById(R.id.viewPager);
        ImageAdapter adapterView = new ImageAdapter(this);
        mViewPager.setAdapter(adapterView);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage[0] == 5-1) {
                    currentPage[0] = 0;
                }
                mViewPager.setCurrentItem(currentPage[0]++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);


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

                            if (abc != 1)
                            {
                                Toast.makeText(getApplicationContext(), "Work in Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                showJSON(response);
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

    public void sendingReview()
    {
        final ProgressDialog loading = ProgressDialog.show(this,"Loading","Please wait...",false,false);

        final String fullname         = editTextFullName.getText().toString();
        final String mobile           = editTextMobile.getText().toString();
        final String email            = editTextEmailId.getText().toString();
        final String city             = editTextCity.getText().toString();
        final String review           = editTextReview.getText().toString();

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
                                Toast.makeText(getApplicationContext(), "Work in Progress....", Toast.LENGTH_SHORT).show();
                            }
                            else if (abc == 1)
                            {
                                loading.dismiss();
                                showJSONReview(response);
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

    private void showJSONReview(String json) {
        JsonHolderReviews jsonHolderReviews = new JsonHolderReviews(json);
        jsonHolderReviews.parseJSON();

        ReviewListingAdapter reviewListingAdapter = new ReviewListingAdapter(this,jsonHolderReviews.name,jsonHolderReviews.comment,jsonHolderReviews.created_date,jsonHolderReviews.rating);
        listViewUsersReviews.setAdapter(reviewListingAdapter);

    }


    private void showJSON(String json) {
        JsonHolderFullListing jsonHolderFullListing = new JsonHolderFullListing(json);
        jsonHolderFullListing.parseJSON();

        FullListingAdapter fullListingAdapter = new FullListingAdapter(this,jsonHolderFullListing.title,jsonHolderFullListing.product_image,jsonHolderFullListing.discount,jsonHolderFullListing.price,jsonHolderFullListing.features);
        listViewBusinessInformation.setAdapter(fullListingAdapter);

    }

}
