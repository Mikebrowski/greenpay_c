package com.example.greenpayremastered;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InitiativesInfo extends AppCompatActivity {

    ImageView imgFromIni;
    TextView txtFromIni;
    TextView txtPointsFromIni;
    TextView showTotalValue;
    TextView textView12x;
    TextView textView13x;

    Button addPlusBtn;
    Button deductMinusBtn;
    Button addToValue;

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initiatives_info);

        imgFromIni= findViewById(R.id.imgCircled);
        txtFromIni = findViewById(R.id.txtIniInfo);
        txtPointsFromIni= findViewById(R.id.txtPointsIni);
        showTotalValue = findViewById(R.id.totalValue);
        //textView12x = findViewById(R.id.textView12);
        textView13x = findViewById(R.id.textView13);



        addPlusBtn = findViewById(R.id.plusBtn);
        deductMinusBtn = findViewById(R.id.minusBtn);
        addToValue = findViewById(R.id.addToValue);

        Intent intent = getIntent();


        imgFromIni.setImageResource(intent.getIntExtra("image",R.drawable.ic_launcher_foreground));
        txtFromIni.setText(intent.getStringExtra("name"));
        txtPointsFromIni.setText(intent.getStringExtra("points"));

        // REMEMBER TO DO TOTAL SUM TIMES points AKA that is the nummber so if the user presses it 5 times it will be 5 x 20 = 100


        addPlusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber();
            }
        });

        deductMinusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deductNumber();
            }
        });
        addToValue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeTotalPoints();
            }
        });
    }

    private void seeTotalPoints() {
        int pointsFromR2 = Integer.valueOf(showTotalValue.getText().toString());
        String correctPointFormat = txtPointsFromIni.getText().toString();
        String correct = correctPointFormat.split(" ")[0];
        int num = Integer.parseInt(correct);

        int calculatedValue = Integer.valueOf(pointsFromR2*num);

        textView13x.setText(String.valueOf(calculatedValue)+ " Totalt poeng");
    }


    private void addNumber() {
        counter++;
        showTotalValue.setText(Integer.toString(counter));
    }

    private void deductNumber() {
        counter--;
        showTotalValue.setText(Integer.toString(counter));
    }



}