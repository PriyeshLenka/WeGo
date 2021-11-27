package com.nextgen.wego;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;

public class OnBoarding extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dots;
    SharedPreferences sharedPreferences1;
    SlideAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        sharedPreferences1 = getSharedPreferences("WeGoLogin", MODE_PRIVATE);
        viewPager = findViewById(R.id.slider);
        dots = findViewById(R.id.dots);

        sliderAdapter = new SlideAdapter(this);
        viewPager.setAdapter(sliderAdapter);


//        if (sharedPreferences1.getString("profile", "").contentEquals("sp") || sharedPreferences1.getString("profile", "").contentEquals("cp")) {
//            Intent intent = new Intent(OnBoarding.this, GenerateOTP.class);
//            startActivity(intent);
//            finish();
//
//
//
//        } else {
//            Intent intent = new Intent(OnBoarding.this, ChooserActivity.class);
//            startActivity(intent);
//            finish();
//        }

    }




}