package com.example.greenpayremastered;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import database.UserData;


public class LoginActivity extends AppCompatActivity {

    private Button insideLoginBtn;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressBar progressBar;

    /*DB and ADAPTER*/

    UserData userData= new UserData();
    private FirebaseAuth mAuth;
    //private DatabaseReference myRef;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);


        inputEmail= (EditText) findViewById(R.id.inputEmail);
        inputPassword= (EditText) findViewById(R.id.inputPassword);
        progressBar = (ProgressBar)findViewById(R.id.progressBarBot);
        progressBar.setVisibility(View.GONE);



        mAuth = FirebaseAuth.getInstance();
        //myRef = mFirebaseDatabase.getReference().child("UserData");

        insideLoginBtn=(Button) findViewById(R.id.insideLoginBtn);

        /*SELECT username FROM epost WHERE '@inputfield'='@dbvalue'*/

        insideLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

    }


    private void loginUser(){
        String emailString = inputEmail.getText().toString();//ALTERNATIVE CHECK IF matches(emailPattern)
        String passwordCheck = inputPassword.getText().toString(); //String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z.]+";

        if(TextUtils.isEmpty(emailString)  && Patterns.EMAIL_ADDRESS.matcher(emailString).matches())
        {
            inputEmail.setError("Epost kan ikke være blank og riktig skrevet");
            inputPassword.requestFocus();
        } else if(TextUtils.isEmpty(passwordCheck) ){
            inputPassword.setError("Passord feltet må skrevet");
            inputPassword.requestFocus();
        }else {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(emailString,passwordCheck).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"Logget velykkende med følgende: "+emailString,Toast.LENGTH_LONG).show();
                        startActivity(new Intent(LoginActivity.this, LoginActivityScreen.class));
                        progressBar.setVisibility(View.VISIBLE);
                    }

                }
            });
        }

    }


}