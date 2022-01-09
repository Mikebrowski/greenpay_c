package com.example.greenpayremastered;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import models.PointsData;
import database.UserData;

public class InitiativesInfo extends AppCompatActivity {

    ImageView imgFromIni;
    TextView txtFromIni;
    TextView txtPointsFromIni;
    TextView showTotalValue;
    TextView textView12x;
    TextView textView13x;

    TextView userNameField;

    Button addPlusBtn;
    Button deductMinusBtn;
    Button addToValue;

    FirebaseDatabase mFirebaseDatabase;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference mDatabase;


    String userID;
    String initiativeName;
    Date currentDateS;


    int counter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initiatives_info);

        imgFromIni = findViewById(R.id.imgCircled);
        txtFromIni = findViewById(R.id.txtIniInfo);
        txtPointsFromIni = findViewById(R.id.txtPointsIni);
        showTotalValue = findViewById(R.id.totalValue);
        userNameField = findViewById(R.id.loginText);
        textView13x = findViewById(R.id.textView13);

        addPlusBtn = findViewById(R.id.plusBtn);
        deductMinusBtn = findViewById(R.id.minusBtn);
        addToValue = findViewById(R.id.addToValue);

        Intent intent = getIntent();

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        imgFromIni.setImageResource(intent.getIntExtra("image", R.drawable.ic_launcher_foreground));
        txtFromIni.setText(intent.getStringExtra("name"));
        txtPointsFromIni.setText(intent.getStringExtra("points"));
        userNameField.setText(intent.getStringExtra("username"));

        showTotalValue.setVisibility(View.GONE);
        textView13x.setVisibility(View.GONE);
        //HENT BRUKERNAVNET
        // REMEMBER TO DO TOTAL SUM TIMES points AKA that is the nummber so if the user presses it 5 times it will be 5 x 20 = 100

        addPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTotalValue.setVisibility(View.VISIBLE);
                textView13x.setVisibility(View.VISIBLE);
                addNumber();
            }
        });

        deductMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTotalValue.setVisibility(View.VISIBLE);
                textView13x.setVisibility(View.VISIBLE);
                deductNumber();
            }
        });
        addToValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                seeTotalPoints(); }
        });
    }

    private void seeTotalPoints() {
        String correctPointFormat = txtPointsFromIni.getText().toString();
        String correct = correctPointFormat.split(" ")[0];
        int num = Integer.parseInt(correct);

        String s = showTotalValue.getText().toString();
        if (s.toLowerCase().startsWith("t")){
            s = "1";
        }
        int pointsFromR2 = Integer.valueOf(s);
        int calculatedValue = Integer.valueOf(pointsFromR2 * num);

        textView13x.setText(String.valueOf(calculatedValue));
        addToDatabase(calculatedValue);
    }

    private void addNumber() {
        counter++;
        showTotalValue.setText(Integer.toString(counter));
    }

    private void deductNumber() {
        counter--;
        showTotalValue.setText(Integer.toString(counter));
    }

    private void addToDatabase(int calculatedValue) {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        initiativeName = txtFromIni.getText().toString();
        Calendar calendar = Calendar.getInstance();

        currentDateS = calendar.getTime();
        //String currentDateS = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.getTime());
        //userNameField.setText(user.getUid()); GIR EN ANNEN ID EN FORVENTET

        //TODO: Might need to use string agian to use easier display method.

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = rootRef.child("user").child(uid);
        ValueEventListener valueEventListener = new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String usernameOnProfile = snapshot.getValue(UserData.class).getUsername();
                //userNameField.setText(usernameOnProfile);
                addIntoDB(calculatedValue, snapshot.getValue(UserData.class).getUsername(),initiativeName,currentDateS);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                userNameField.setText("NOPE");
            }
        };
        uidRef.addValueEventListener(valueEventListener);
    }

    public Task<Void> addIntoDB(Integer totalpoints, String username, String initiativeName,Date currentDateS) {

        //DatabaseReference uidRef = rootRef.child("user");
        //mFirebaseDatabase.getInstance().getReference("pointsData");

        mAuth = FirebaseAuth.getInstance();
        PointsData pointsData = new PointsData(totalpoints,username,initiativeName,currentDateS);
        currentDateM();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = db.getReference(PointsData.class.getSimpleName());

        return databaseReference.push().setValue(new PointsData(totalpoints,username,initiativeName,currentDateS));
    }
    public void currentDateM(){
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.getTime());
        userNameField.setText(currentDate);
    }
}