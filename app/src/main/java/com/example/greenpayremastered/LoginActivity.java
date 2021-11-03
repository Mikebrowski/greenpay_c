package com.example.greenpayremastered;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private Button insideLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        insideLoginBtn=findViewById(R.id.insideLoginBtn);

        insideLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntentInsideLogin = new Intent(LoginActivity.this,LoginActivityScreen.class);
                startActivity(newIntentInsideLogin);
            }
        });


    }

}