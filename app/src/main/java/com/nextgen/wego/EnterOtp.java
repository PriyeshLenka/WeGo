package com.nextgen.wego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class EnterOtp extends AppCompatActivity {
    PinView pin;
    String otpbackend,Enteredotp;
    EditText phone_no;
    private static final String KEY_VERIFICATION_ID = "key_verification_id";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_otp);
        pin = findViewById(R.id.firstPinView);
        phone_no = findViewById(R.id.phone_no);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        String getName = "+91" + bd.getString("phone_no");
        phone_no.setText(getName);
        otpbackend = getIntent().getStringExtra("otp");
        Enteredotp=pin.getText().toString();
        Button eotp = findViewById(R.id.enterotp);

        eotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(otpbackend == null && savedInstanceState!=null)
                    onRestoreInstanceState(savedInstanceState);
                EnterOtp(view);
            }
        });



    }


    public void EnterOtp(View view) {
        //Toast.makeText(EnterOtp.this, pin.getText().toString(), Toast.LENGTH_SHORT).show();

        if (!pin.getText().toString().trim().isEmpty()) {
           if(otpbackend!=null){


                   try {
                       PhoneAuthCredential phoneAuthCredential=PhoneAuthProvider.getCredential(otpbackend,Enteredotp);
                       signInWithPhoneAuthCredential(phoneAuthCredential);
                   }catch (Exception e){
                       Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                   }





           }
           else{
               Toast.makeText(EnterOtp.this, "check ur connection", Toast.LENGTH_SHORT).show();
           }
            // Toast.makeText(EnterOtp.this, "verify", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(EnterOtp.this, "Enter code", Toast.LENGTH_SHORT).show();
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential phoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(EnterOtp.this, "complete", Toast.LENGTH_SHORT).show();
                        Log.d("Complete log", "onComplete: Complete");
                        if(task.isSuccessful()){
                           Intent intent=new Intent(EnterOtp.this, UploadDocuments.class);
                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(intent);
                            Toast.makeText(EnterOtp.this, "verified", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(EnterOtp.this, "Enter correct oto", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_VERIFICATION_ID,otpbackend);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        otpbackend = savedInstanceState.getString(KEY_VERIFICATION_ID);
    }
}
