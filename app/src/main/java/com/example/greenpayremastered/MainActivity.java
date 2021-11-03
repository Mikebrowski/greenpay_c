package com.example.greenpayremastered;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    /*GUI*/
    private Button regButton;
    private Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        regButton=(Button)findViewById(R.id.regButton);
        loginButton=(Button)findViewById(R.id.insideLoginBtn);

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntentRegWindow = new Intent(MainActivity.this, RegisterActivityScreen.class);
                startActivity(newIntentRegWindow);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntentLoginWindow = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(newIntentLoginWindow);
            }
        });

    }
    // In order to move from one window to another you have to use Intents. These are objects that are used for tranferring
    // This method will be void and go another object in this case RegisterActivity
    public void goToRegisterActivity()
    {
        Intent newIntentRegWindow = new Intent(MainActivity.this, RegisterActivityScreen.class);
        //INSERT THE intent ObjectName
        startActivity(newIntentRegWindow);
    }
    public void registerWindow (View view)
    {
        goToRegisterActivity();
    }
    public void goToLoginActivity()
    {
        Intent newIntentLoginWindow = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(newIntentLoginWindow);
    }
    public void loginActivity(View view)
    {
        goToLoginActivity();
    }
}