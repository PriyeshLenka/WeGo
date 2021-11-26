package com.nextgen.wego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       // getSupportActionBar().hide();
        sharedPreferences1 = getSharedPreferences("WeGoLogin", MODE_PRIVATE);


        Thread td = new Thread() {

            public void run() {
                try {
                    sleep(3600);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (sharedPreferences1.getString("profile", "").contentEquals("sp") || sharedPreferences1.getString("profile", "").contentEquals("cp")) {
                        Intent intent = new Intent(SplashActivity.this, GenerateOTP.class);
                        startActivity(intent);
                        finish();


                    } else {
                        Intent intent = new Intent(SplashActivity.this, ChooserActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }

        };
        td.start();

    }
}