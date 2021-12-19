package com.nextgen.wego;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class Loginwithemail extends AppCompatActivity {
    FirebaseAuth mAuth;
    private ProgressDialog progressDialog;
    EditText email,password;
    Button loginbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_loginwithemail );



        mAuth = FirebaseAuth.getInstance();


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        email=findViewById( R.id.email );
        password=findViewById( R.id.password );
        loginbtn=findViewById( R.id.loginbtn );
        loginbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login( email.getText().toString(),password.getText().toString());
            }
        } );

    }

    private void Login(String email, String pass) {
        progressDialog.show();
        if( TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){
            progressDialog.dismiss();
            Toast.makeText(Loginwithemail.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        }
    else{

        mAuth.signInWithEmailAndPassword( email, pass ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {progressDialog.dismiss();
                    Toast.makeText( getApplicationContext(),"Sucess" , Toast.LENGTH_SHORT).show();



                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText( getApplicationContext(),"Fail" , Toast.LENGTH_SHORT).show();


                }
            }
        } );




        }


    }
}