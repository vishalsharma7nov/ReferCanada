package com.allumez.refercanada;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;

public class Coupons extends AppCompatActivity {

    LinearLayout frame2;
    GridLayout frame1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons);

//        frame1 = findViewById(R.id.frame1);
//        frame2 = findViewById(R.id.frame2);
//        frame1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                frame1.setVisibility(View.GONE);
//                frame2.setVisibility(View.VISIBLE);
//            }
//        });
    }
}
