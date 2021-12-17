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

public class Registerwithemail extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    FirebaseStorage firebaseStorage;
    private ProgressDialog progressDialog;
    EditText email,password;
    Button registerbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_registerwithemail );

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");
        progressDialog.setCancelable(false);

        email=findViewById( R.id.email );
        password=findViewById( R.id.password );
        registerbtn=findViewById( R.id.registerbtn );
        registerbtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register(email.getText().toString(),password.getText().toString());
            }
        } );



    }

    private void Register(String email, String pass) {
        progressDialog.show();
        if( TextUtils.isEmpty(email) || TextUtils.isEmpty(pass)){
            progressDialog.dismiss();
            Toast.makeText(Registerwithemail.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
        }

        else{

mAuth.createUserWithEmailAndPassword( email, pass ).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
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