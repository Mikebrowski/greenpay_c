package com.example.greenpayremastered;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
//import fragments;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/*
private FirebaseDatabase firebaseDatabase;
private DatabaseReference databaseReference;
private static ArrayList<DBHelper> arrayList  = new ArrayList<>();
private RecyclerView list;
private Button btnCreate;
public static Activity Fa;
*/

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







/*
        ConstraintLayout constraintLayout = findViewById(R.id.MainActivity);

        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1500);
        animationDrawable.setExitFadeDuration(3000);
        animationDrawable.start();
*/
        //MainFragment

    }
    // In order to move from one window to another you have to use Intents. These are objects that are used for tranferring
    // This method will be void and go another object in this case RegisterActivity
    public void goToRegisterActivity()
    {
        Intent newIntentWindow = new Intent(MainActivity.this,RegisterActivityX.class);
        //INSERT THE intent ObjectName
        startActivity(newIntentWindow);
    }
    public void registerWindow (View view)
    {
        goToRegisterActivity();
    }

}