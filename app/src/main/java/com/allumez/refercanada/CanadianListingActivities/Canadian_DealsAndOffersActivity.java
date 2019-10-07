package com.allumez.refercanada.CanadianListingActivities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.allumez.refercanada.R;

import static android.widget.Toast.makeText;

public class Canadian_DealsAndOffersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canadian__deals_and_offers);
        makeText(this, "No Deals And Offers Available...", Toast.LENGTH_SHORT).show();
    }

}
