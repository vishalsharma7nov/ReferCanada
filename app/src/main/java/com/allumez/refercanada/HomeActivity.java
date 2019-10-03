package com.allumez.refercanada;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.allumez.refercanada.blogs.Blog;
import com.allumez.refercanada.canadianListing.Canadian_State_Listing_Activity;
import com.allumez.refercanada.coupons.Coupons;
import com.allumez.refercanada.forum.ForumActivity;
import com.allumez.refercanada.internationalListing.International_CategoryList_Activity;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView imageViewReferCanadaLogo,imageViewHeader;
    LinearLayout linearLayoutListing;
    TextView textViewCanadianList,textViewInternationalListing,textViewCoupon,textViewFourm;
    Button buttonSignIn,buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        buttonSignIn   = findViewById(R.id.buttonSignIn);
        buttonRegister = findViewById(R.id.buttonRegister);
        final RelativeLayout relativeLayout1 = findViewById(R.id.relativeLayout1);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });
        imageViewReferCanadaLogo = findViewById(R.id.imageViewReferCanadaLogo);
        imageViewHeader          = findViewById(R.id.imageView);
        textViewCanadianList    = findViewById(R.id.canadianlisting);
        textViewInternationalListing = findViewById(R.id.internationallisting);
        textViewCoupon              = findViewById(R.id.coupon);
        textViewFourm           = findViewById(R.id.form);
        textViewFourm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ForumActivity.class);
                startActivity(intent);
            }
        });
        linearLayoutListing      = findViewById(R.id.linearLayoutListing);
        textViewCanadianList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, Canadian_State_Listing_Activity.class);
                startActivity(intent);
            }
        });
        textViewInternationalListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, International_CategoryList_Activity.class);
                startActivity(intent);
            }
        });
        textViewCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, Coupons.class);
                startActivity(intent);
            }
        });
        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                relativeLayout1.setVisibility(View.GONE);
                linearLayoutListing.setVisibility(View.VISIBLE);
                imageViewHeader.setVisibility(View.VISIBLE);
                buttonRegister.setVisibility(View.VISIBLE);
            }
        });
        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                relativeLayout1.setVisibility(View.GONE);
                linearLayoutListing.setVisibility(View.VISIBLE);
                imageViewHeader.setVisibility(View.VISIBLE);
                buttonRegister.setVisibility(View.VISIBLE);
                someHandler.postDelayed(this, 3000);
            }
        }, 3000);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_home)
        {

        }
        else if (id == R.id.nav_sign_in)
        {
            Intent intent =  new Intent(HomeActivity.this,LoginActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_register)
        {
            Intent intent = new Intent(HomeActivity.this,RegistrationActivity.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_blog)
        {
            Intent intent = new Intent(HomeActivity.this, Blog.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_coupons)
        {
            Intent intent = new Intent(HomeActivity.this,Coupons.class);
            startActivity(intent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
