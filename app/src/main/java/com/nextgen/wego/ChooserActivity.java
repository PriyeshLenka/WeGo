package com.nextgen.wego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChooserActivity extends AppCompatActivity {
    ImageView ServiceProvider, Customer;
    SharedPreferences sharedPreferences1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
        //getSupportActionBar().hide();
        sharedPreferences1 = getSharedPreferences("WeGoLogin", MODE_PRIVATE);
        ServiceProvider = findViewById(R.id.service_login_page);
        ServiceProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor myedit = sharedPreferences1.edit();
                myedit.putString("profile", "sp");
                myedit.commit();
                Intent intent = new Intent(ChooserActivity.this, GenerateOTP.class);
                startActivity(intent);
            }
        });

        Customer = findViewById(R.id.customer_login_page);
        Customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor myedit = sharedPreferences1.edit();
                myedit.putString("profile", "cp");
                myedit.commit();
                Intent intent = new Intent(ChooserActivity.this, GenerateOTP.class);
                startActivity(intent);
            }
        });
    }
}