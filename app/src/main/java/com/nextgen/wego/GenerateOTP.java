package com.nextgen.wego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class GenerateOTP extends AppCompatActivity {
    private  static  String vid;
    private static final String KEY_VERIFICATION_ID = "key_verification_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_otp);
//        getSupportActionBar().hide();
        Button generateOTP = (Button)findViewById(R.id.GenerateOTP);
        EditText phone=(EditText)findViewById(R.id.phone);

        generateOTP.setOnClickListener(v -> {
            if(!phone.getText().toString().trim().isEmpty()){
                if(phone.getText().toString().trim().length()==10){
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+91" + phone.getText().toString(),
                            60,
                            TimeUnit.SECONDS,
                            GenerateOTP.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    Toast.makeText(GenerateOTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }

                                @Override
                                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    super.onCodeSent(s, forceResendingToken);
                                    vid = s;
                                    Intent intent=new Intent(GenerateOTP.this, EnterOtp.class);
                                    intent.putExtra("phone_no",phone.getText().toString());
                                    if (s == null && savedInstanceState != null) {
                                        onRestoreInstanceState(savedInstanceState);
                                    }
                                    intent.putExtra("otp",vid);
                                    startActivity(intent);
                                }
                            }
                    );


                }
                else
                {
                    Toast.makeText(GenerateOTP.this, "enter correct phonne no", Toast.LENGTH_SHORT).show();
                }

            }
            else{
                Toast.makeText(GenerateOTP.this, "enter  phone no", Toast.LENGTH_SHORT).show();
            }
//                startActivity(new Intent(GenerateOTP.this, EnterOtp.class));
        });

    }
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_VERIFICATION_ID,vid);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        vid = savedInstanceState.getString(KEY_VERIFICATION_ID);
    }
}