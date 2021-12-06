package com.example.greenpayremastered;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import com.example.greenpayremastered.databinding.ActivityImprovedMainBinding;


public class MainActivity extends AppCompatActivity {


    /*GUI*/
    private Button regButton;
    private Button loginButton;

    /*Action bar */
    // Define ActionBar object
    ActionBar actionBar;

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityImprovedMainBinding binding;

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
    }// ON CREATE END

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
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




/*
*         //actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        //ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#0F9D58"));

        // Set BackgroundDrawable
        // actionBar.setBackgroundDrawable(colorDrawable);
*
*
*
* */