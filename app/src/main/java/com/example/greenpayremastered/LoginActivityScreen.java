package com.example.greenpayremastered;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.FirebaseDatabase;


public class LoginActivityScreen extends AppCompatActivity {
    FirebaseAuth mAuth;
    private Button logoutBtn;
    private TextView loginText;
    private Button firstButton;
    private Button secondButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loggetscreen);

        logoutBtn = (Button) findViewById(R.id.logoutBtn);
        loginText = (TextView) findViewById(R.id.loggedInTextview);
        firstButton = (Button) findViewById(R.id.firstButton);
        secondButton = (Button) findViewById(R.id.secondButton);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        FirebaseDatabase firebaseDatabase ;


        //UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName("Jane Q. User").setPhotoUri(Uri.parse("https://example.com/jane-q-user/profile.jpg")).build();



        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = user.getEmail();
                String userName= user.getDisplayName();
                loginText.setText("hi " + userName);
            }
        });


        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginText.setText("I HAVE CHANGED INTO " + mAuth.getCurrentUser().getEmail());
                firstButton.setText(mAuth.getCurrentUser().getEmail());
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                startActivity(new Intent(LoginActivityScreen.this, MainActivity.class));
            }
        });

    }//end of onCreate

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            //Intent loggedInside = new Intent(LoginActivity.this,LoginActivityScreen.class);
            startActivity(new Intent(LoginActivityScreen.this, LoginActivity.class));
            //startActivity(loggedInside);

        }
    }
}
