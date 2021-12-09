package com.nextgen.wego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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




    }


    public void EnterOtp(View view) {
        Toast.makeText(EnterOtp.this, pin.getClipBounds().toString(), Toast.LENGTH_SHORT).show();
        if (!pin.getText().toString().trim().isEmpty()) {
           if(otpbackend!=null){
               PhoneAuthCredential phoneAuthCredential=PhoneAuthProvider.getCredential(otpbackend,Enteredotp);
               FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()){
                           Intent intent=new Intent(EnterOtp.this, UploadDocuments.class);
                           intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                           startActivity(intent);
                       }
                       else{
                           Toast.makeText(EnterOtp.this, "Enter correct opr", Toast.LENGTH_SHORT).show();
                       }

                   }
               });

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
}
