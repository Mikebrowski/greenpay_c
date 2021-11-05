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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import database.UserData;

public class RegisterActivityScreen extends AppCompatActivity {


    private EditText txtEmailAddress;
    private EditText txtPassword;
    private EditText txtUsername;
    //private EditText editTextTextPassword2; SJEKKE OM BEGGE PASSWORD ER RIKTIG

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference myRef;
    private ProgressBar regprogressBar;


    //GUI
    private Button loginButton;    //loginBtnReg
    private Button registerButton;
    private Object UserData;
    //private ProgressBar loadingPB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        txtEmailAddress = (EditText) findViewById(R.id.editTextViewEmail);
        txtPassword = (EditText) findViewById(R.id.editTextTextPassword1);
        txtUsername = (EditText) findViewById(R.id.usernameInput);  //usernameInput


        registerButton = (Button) findViewById(R.id.registerButton);
        loginButton = (Button) findViewById(R.id.insideLoginBtn);
        regprogressBar = (ProgressBar) findViewById(R.id.regprogressBar);

        regprogressBar.setVisibility(View.GONE);


        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference().child("UserData");

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validateEmailAdresse(txtEmailAddress,txtPassword);
                createUser();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntentRegLogin = new Intent(RegisterActivityScreen.this, LoginActivity.class);
                startActivity(newIntentRegLogin);
            }
        });


    }//end of onCreate

    private void createUser() {

        //String username =

        final String email = txtEmailAddress.getText().toString();//ALTERNATIVE CHECK IF matches(emailPattern)
        String passwordCheck = txtPassword.getText().toString(); //String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z.]+";
        final String username= txtUsername.getText().toString();

        if (TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEmailAddress.setError("Epost kan ikke være blank og riktig skrevet");
            txtEmailAddress.requestFocus();
        } else if (TextUtils.isEmpty(passwordCheck)) {
            txtPassword.setError("Passord feltet må være fult");
            txtPassword.requestFocus();
        } else if(TextUtils.isEmpty(username)){
            txtUsername.getText().toString();
            txtUsername.setError("Brukernavnet må være fult");
            txtUsername.requestFocus();
        }
        else {
            regprogressBar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email, passwordCheck).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {


                    if (task.isSuccessful())
                    {
                        UserData userData = new UserData(email, username);
                        //HVORFOR KAN JEG IKKE PUTTE INN getReference() INN HER myRef
                        FirebaseDatabase.getInstance().getReference(userData.getUsername()).child(FirebaseAuth.getInstance().getUid()).setValue(userData).addOnCompleteListener(new OnCompleteListener<Void>()
                                {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task)
                                    {
                                        regprogressBar.setVisibility(View.GONE);
                                        if (task.isSuccessful()){
                                            Toast.makeText(RegisterActivityScreen.this, "Brukeren laget " + txtEmailAddress.getText().toString(), Toast.LENGTH_LONG).show();
                                            Intent loggedInside = new Intent(RegisterActivityScreen.this, LoginActivityScreen.class);
                                            startActivity(loggedInside);
                                        }else{
                                            //display error
                                        }

                                    }
                        });
                    } else {
                        Toast.makeText(RegisterActivityScreen.this, "Ugyldig epost,passord eller brukernavn", Toast.LENGTH_LONG).show();
                    }

                }
            });

        }

    }

    private boolean validateEmailAdresse(EditText txtEmailAddress, EditText txtPassword) {
        String emailString = txtEmailAddress.getText().toString();//ALTERNATIVE CHECK IF matches(emailPattern)
        String passwordCheck = txtPassword.getText().toString(); //String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z.]+";

        if (!emailString.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailString).matches() && !passwordCheck.isEmpty()) {

            //userData.setEmail(txtEmailAddress.getText().toString());
            //userData.setPassword(txtPassword.getText().toString());

            //myRef.push().setValue(userData);
            Toast.makeText(this, "Brukeren laget " + txtEmailAddress.getText().toString(), Toast.LENGTH_LONG).show();
            return true;
        } else {
            Toast.makeText(this, "Ugyldig epost eller passord", Toast.LENGTH_LONG).show();
            return false;
        }//end else

    } //end validateEmailAdresse

}//end of create user
