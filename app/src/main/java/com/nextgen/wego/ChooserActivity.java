package com.nextgen.wego;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChooserActivity extends AppCompatActivity {
ImageView ServiceProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
        getSupportActionBar().hide();
        ServiceProvider=findViewById(R.id.ServiceProvider);
        ServiceProvider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChooserActivity.this,GenerateOTP.class);
                startActivity(intent);


            }
        });
    }
}