package com.allumez.refercanada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewCanadianListing,textViewInternationalListing,textViewCouponsLocalStore,textViewDiscountOffers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewCanadianListing         =       (TextView)findViewById(R.id.canadianlisting);
        textViewInternationalListing    =       (TextView)findViewById(R.id.internationallisting);
        textViewCouponsLocalStore       =       (TextView)findViewById(R.id.couponslocalstore);
        textViewDiscountOffers          =       (TextView)findViewById(R.id.discountoffers );

        textViewCanadianListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CanadianListingActivity.class);
                startActivity(intent);
            }
        });

    }
}
