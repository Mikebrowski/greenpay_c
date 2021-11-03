package com.example.greenpayremastered;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import database.UserData;

public class RegisterActivityScreen extends AppCompatActivity {


    private EditText txtEmailAddress;
    private EditText txtPassword;
    //private EditText editTextTextPassword2; SJEKKE OM BEGGE PASSWORD ER RIKTIG

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;


    //GUI
    private Button loginButton;    //loginBtnReg
    private Button registerButton;
    //private ProgressBar loadingPB;
    UserData userData = new UserData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        txtEmailAddress =(EditText) findViewById(R.id.editTextViewEmail);
        txtPassword =(EditText) findViewById(R.id.editTextTextPassword1);
        registerButton =(Button) findViewById(R.id.registerButton);
        loginButton=(Button) findViewById(R.id.insideLoginBtn);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase= FirebaseDatabase.getInstance();
        myRef =mFirebaseDatabase.getReference().child("UserData");

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateEmailAdresse(txtEmailAddress,txtPassword);
            }

        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntentRegLogin = new Intent(RegisterActivityScreen.this,LoginActivity.class);
                startActivity(newIntentRegLogin);
            }
        });


        }//end of onCreate


        private boolean validateEmailAdresse(EditText txtEmailAddress, EditText txtPassword)
        {
            String emailString = txtEmailAddress.getText().toString();//ALTERNATIVE CHECK IF matches(emailPattern)
            String passwordCheck = txtPassword.getText().toString(); //String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z.]+";

             if(!emailString.isEmpty()  && Patterns.EMAIL_ADDRESS.matcher(emailString).matches() && !passwordCheck.isEmpty())
             {

                 userData.setEmail(txtEmailAddress.getText().toString());
                 userData.setPassword(txtPassword.getText().toString());

                 myRef.push().setValue(userData);
                 Toast.makeText(this,"Brukeren laget "+txtEmailAddress.getText().toString(),Toast.LENGTH_LONG).show();
                 return true;
             }else {
                 Toast.makeText(this,"Ugyldig epost", Toast.LENGTH_LONG).show();
                 return false;
             }//end else

        } //end validateEmailAdresse



}