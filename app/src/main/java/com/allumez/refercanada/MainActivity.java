package com.allumez.refercanada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

//    TextView textViewCanadianListing,textViewInternationalListing,textViewCouponsLocalStore,textViewDiscountOffers;
    ImageView textViewCanadianListing,textViewInternationalListing;
    LinearLayout linearLayoutCanadianList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCanadianListing         =       (ImageView)findViewById(R.id.canadianlisting);
        textViewInternationalListing    =       (ImageView) findViewById(R.id.internationallisting);
//        textViewCouponsLocalStore       =       (TextView)findViewById(R.id.couponslocalstore);
//        textViewDiscountOffers          =       (TextView)findViewById(R.id.discountoffers );

        linearLayoutCanadianList = (LinearLayout)findViewById(R.id.linearlayoutcanadianlisting);
        linearLayoutCanadianList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CanadianListingActivity.class);
                startActivity(intent);
            }
        });
//        textViewCanadianListing.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this,CanadianListingActivity.class);
//                startActivity(intent);
//            }
//        });

    }
}
