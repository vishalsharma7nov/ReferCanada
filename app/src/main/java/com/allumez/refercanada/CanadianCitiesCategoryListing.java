package com.allumez.refercanada;

import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class CanadianCitiesCategoryListing extends AppCompatActivity {

    CardView cardView1,cardView2,cardView3;
    LinearLayout linearLayout;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canadian_cities_category_listing);

        cardView1 = (CardView)findViewById(R.id.listing1);
        cardView2 = (CardView)findViewById(R.id.listing2);
        cardView3 = (CardView)findViewById(R.id.listing3);

        imageView = (ImageView)findViewById(R.id.imageView);


        linearLayout = (LinearLayout)findViewById(R.id.fulllisting);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardView1.setVisibility(View.GONE);
                cardView2.setVisibility(View.GONE);
                cardView3.setVisibility(View.GONE);
                imageView.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
            }
        });

                final int[] currentPage = {0};
        Timer timer;
        final long DELAY_MS = 1000;//delay in milliseconds before task is to be executed
        final long PERIOD_MS = 4000; // time in milliseconds between successive task executions.



        RatingBar ratingBarOverall,ratingBarReviewed;
        ratingBarOverall  = (RatingBar)findViewById(R.id.ratingbar);
        ratingBarReviewed = (RatingBar)findViewById(R.id.ratingbarReviewed);
        ratingBarOverall.setRating(4.5f);
        ratingBarReviewed.setRating(4.5f);
        ratingBarReviewed.setSelected(false);
        final ViewPager mViewPager = (ViewPager)findViewById(R.id.viewPager);
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
    }
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        else
        {

            cardView1.setVisibility(View.VISIBLE);
            cardView2.setVisibility(View.VISIBLE);
            cardView3.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE
        );


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
}
