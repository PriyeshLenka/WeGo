package com.nextgen.wego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import java.util.ResourceBundle;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences onBoardingScreen;
    SharedPreferences sharedPreferences1;
    ImageView splashImage;
    Animation sideAnim;
    private static int SPLASH_SCREEN = 3600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        // getSupportActionBar().hide();

        splashImage = findViewById(R.id.splashImage);
        sharedPreferences1 = getSharedPreferences("WeGoLogin", MODE_PRIVATE);

        //Animation
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        splashImage.setAnimation(sideAnim);

//        Thread td = new Thread() {
//
//            public void run() {
//                try {
//                    sleep(3600);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//
//                    Intent intent = new Intent(SplashActivity.this, OnBoarding.class);
//                    startActivity(intent);
//                    finish();
//
//                }
//            }
//
//        };
//        td.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                onBoardingScreen = getSharedPreferences("onBoardingScreen",MODE_PRIVATE);
                boolean isFirstTime = onBoardingScreen.getBoolean("firstTime",true);

                if(isFirstTime){

                    SharedPreferences.Editor editor = onBoardingScreen.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();

                    Intent intent = new Intent(SplashActivity.this, OnBoarding.class);
                    startActivity(intent);
                    finish();
                }
                else   if (sharedPreferences1.getString("profile", "").contentEquals("sp") || sharedPreferences1.getString("profile", "").contentEquals("cp")) {
                    Intent intent = new Intent(SplashActivity.this, DashBoardActivity.class);//GenerateOTP
                    startActivity(intent);
                    finish();


                }
                else{
                    Intent intent = new Intent(SplashActivity.this, ChooserActivity.class);
                    startActivity(intent);
                    finish();
                }


            }
        }, SPLASH_SCREEN);

    }
}