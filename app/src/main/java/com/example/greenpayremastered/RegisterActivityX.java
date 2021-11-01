package com.example.greenpayremastered;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import database.UserData;

public class RegisterActivityX extends AppCompatActivity {


    public EditText txtEmailAddress;
    public EditText txtPassword;
    private EditText editTextTextPassword2;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;

    //GUI
    public Button registerButton;
    //private ProgressBar loadingPB;
    UserData userData = new UserData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_x);

        txtEmailAddress =(EditText) findViewById(R.id.editTextViewEmail);
        txtPassword =(EditText) findViewById(R.id.editTextTextPassword1);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase= FirebaseDatabase.getInstance();
        myRef =mFirebaseDatabase.getReference().child("UserData");

        }
        public void btnRegisterClick(View view)
        {
            userData.setEmail(txtEmailAddress.getText().toString());
            userData.setPassword(txtPassword.getText().toString());

            myRef.push().setValue(userData);
            Toast.makeText(RegisterActivityX.this,"Brukeren laget"+txtEmailAddress.getText().toString(),Toast.LENGTH_LONG).show();
            //Toast.makeText(getBaseContext(RegisterActivityX.this), "Reason can not be blank", Toast.LENGTH_SHORT).show();
        }



}