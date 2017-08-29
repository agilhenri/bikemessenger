package com.example.agil.bikemessengerbandung;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class Menuutama extends AppCompatActivity {

    FloatingActionButton fab_plus,fab_order,fab_www,fab_logout;
    Animation FabOpen,FabClose,FabRClockwise,FabRanticlockwise;
    boolean isOpen = false;

    ViewPager viewPager;
    ScreenshootsAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.agil.bikemessengerbandung.R.layout.activity_menuutama);


        fab_plus = (FloatingActionButton)findViewById(R.id.fab_plus);
        fab_order = (FloatingActionButton)findViewById(R.id.fab_order);
        fab_www = (FloatingActionButton)findViewById(R.id.fab_www);
        fab_logout = (FloatingActionButton)findViewById(R.id.fab_logout);
        FabOpen = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        FabClose = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        FabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        FabRanticlockwise = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);
        fab_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOpen)
                {
                    fab_order.startAnimation(FabClose);
                    fab_www.startAnimation(FabClose);
                    fab_logout.startAnimation(FabClose);
                    fab_plus.startAnimation(FabRanticlockwise);
                    fab_www.setClickable(false);
                    fab_order.setClickable(false);
                    fab_logout.setClickable(false);
                    isOpen = false;
                    fab_www.setVisibility (View.VISIBLE);
                    fab_order.setVisibility(View.VISIBLE);
                    fab_logout.setVisibility(View.VISIBLE);
                }
                else
                {
                    fab_order.startAnimation(FabOpen);
                    fab_www.startAnimation(FabOpen);
                    fab_logout.startAnimation(FabOpen);
                    fab_plus.startAnimation(FabRClockwise);
                    fab_www.setClickable(true);
                    fab_order.setClickable(true);
                    fab_logout.setClickable(true);
                    isOpen = true;
                }
            }
        });

        viewPager = (ViewPager)findViewById(com.example.agil.bikemessengerbandung.R.id.screenshoot_slider);
        adapter = new ScreenshootsAdapter(this);
        viewPager.setAdapter( adapter );

        fab_order = (FloatingActionButton)findViewById(R.id.fab_order);
        fab_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menuutama.this,MapsActivity.class));
            }
        });
        fab_logout = (FloatingActionButton)findViewById(R.id.fab_logout);
        fab_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Menuutama.this,LoginActivity.class));
            }
        });

    }
}
