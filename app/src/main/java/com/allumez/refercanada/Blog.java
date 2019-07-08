package com.allumez.refercanada;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Blog extends AppCompatActivity {

    LinearLayout linearLayout1,linearLayout2;
    Button button1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        linearLayout1 = (LinearLayout)findViewById(R.id.bloglisting);
        linearLayout2 = (LinearLayout)findViewById(R.id.blogfulldetials);

        button1 = (Button)findViewById(R.id.buttonReadMore);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.VISIBLE);
            }
        });

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
            linearLayout1.setVisibility(View.VISIBLE);
            linearLayout2.setVisibility(View.GONE);

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
