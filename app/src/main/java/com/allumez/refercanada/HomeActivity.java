package com.allumez.refercanada;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ImageView imageViewReferCanadaLogo,imageViewHeader;
    LinearLayout linearLayoutListing;

    ImageView imageViewCanadianList,imageViewInternationalListing;
    Button buttonSignIn,buttonRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        buttonSignIn   = (Button)findViewById(R.id.buttonSignIn);
        buttonRegister = (Button)findViewById(R.id.buttonRegister);

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

        imageViewReferCanadaLogo = (ImageView)findViewById(R.id.imageViewReferCanadaLogo);
        imageViewHeader          = (ImageView)findViewById(R.id.imageView);
        imageViewCanadianList = (ImageView) findViewById(R.id.canadianlisting);
        imageViewInternationalListing= (ImageView)findViewById(R.id.internationallisting);

        linearLayoutListing      = (LinearLayout)findViewById(R.id.linearLayoutListing);


        imageViewCanadianList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,CanadianListingActivity.class);
                startActivity(intent);
            }
        });

        imageViewInternationalListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,InternationalCategoryListActivity.class);
                startActivity(intent);
            }
        });


        final Handler someHandler = new Handler(getMainLooper());
        someHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageViewReferCanadaLogo.setVisibility(View.GONE);
                linearLayoutListing.setVisibility(View.VISIBLE);
                imageViewHeader.setVisibility(View.VISIBLE);
                buttonRegister.setVisibility(View.VISIBLE);
                someHandler.postDelayed(this, 1000);
            }
        }, 1000);




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
            Intent intent = new Intent(HomeActivity.this,Blog.class);
            startActivity(intent);

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}