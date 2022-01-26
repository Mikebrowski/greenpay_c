package fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.greenpayremastered.R;
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

import ViewModel.CalculatePointsViewModel;
import database.UserData;
import models.PointsData;

public class DetailsFragment extends Fragment {

    private static final String ARG_PARAM1 ="param1";
    private static final String ARG_PARAM2 ="param2";
    private static final String ARG_PARAM3 ="param3";
    private static final String ARG_PARAM4 ="param4";

    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;

    TextView amountPresssesBox;
    TextView totalvaluebox;

    private CalculatePointsViewModel viewModel;

    private int counter = 0;

    FirebaseDatabase mFirebaseDatabase;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    DatabaseReference mDatabase;

    String userID;
    String initiativeName;
    Date currentDateS;

    public static DetailsFragment newInstance(String param1, String param2, String param3, String param4){
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,param1);
        args.putString(ARG_PARAM2,param2);
        args.putString(ARG_PARAM3,param3);
        args.putString(ARG_PARAM4,param4);

        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mParam3 = getArguments().getString(ARG_PARAM3);
            mParam4 = getArguments().getString(ARG_PARAM4);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View mainView = inflater.inflate(R.layout.details_fragment, container, false);
        return mainView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView typeDetails = view.findViewById(R.id.typeDetails);
        TextView nameDetails = view.findViewById(R.id.nameDetails);
        TextView pointsDetails = view.findViewById(R.id.pointsDetails);
        TextView infoDetails = view.findViewById(R.id.infoText);

        Button plusButton = view.findViewById(R.id.plusB);
        Button minusButton = view.findViewById(R.id.minusB);
        Button sendToDbButton = view.findViewById(R.id.sendIntoDBB);

        TextView amountPresssesBox =view.findViewById(R.id.pointsAmount);
        TextView totalvaluebox =view.findViewById(R.id.totalValuedetails);


        typeDetails.setText(mParam1);
        nameDetails.setText(mParam2);
        pointsDetails.setText(mParam3);
        infoDetails.setText(mParam4);

        totalvaluebox.setVisibility(View.INVISIBLE);

        sendToDbButton.setText("Regn ut poeng");
        sendToDbButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (counter > 0){
                    sendToDbButton.setText("Send dataen til databasen");
                    totalvaluebox.setVisibility(View.VISIBLE);
                    //totalvaluebox.setText(calculatedValue);
                    seeTotalpoints();

                }
            }
        });


        if (counter == 0) {
            minusButton.setVisibility(View.INVISIBLE);
        }
        plusButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                counter++;
                amountPresssesBox.setText(Integer.toString(counter));
                minusButton.setVisibility(View.VISIBLE);
            }
        });
        minusButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (counter > 0) {
                    counter--;
                } else {
                    minusButton.setVisibility(View.INVISIBLE);
                }
                amountPresssesBox.setText(Integer.toString(counter));
            }
        });
        TextWatcher listener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //textChanged();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
                /*
                editTextA.addTextChangedListener(listener);
                totalvaluebox.addTextChangedListener(listener);
                editTextC.addTextChangedListener(listener);
                */



        //seeTotalPoints(totalvaluebox.setText());


    }// END Of onViewCreated

    /*CAULCULATING AND ADDTION METHODS*/



    private void addToDatabase(int calculatedValue) {
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        initiativeName = mParam2;
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
                return;
            }
        };
        uidRef.addValueEventListener(valueEventListener);
    }

    public Task<Void> addIntoDB(Integer totalpoints, String username, String initiativeName, Date currentDateS) {

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
    }

    private void seeTotalpoints()
    {
        String correctPointsFormat = amountPresssesBox.getText().toString();
        int changeToInt = Integer.parseInt(correctPointsFormat);
        int calculatedValue = Integer.valueOf(counter * changeToInt);
        totalvaluebox.setText(calculatedValue);
        //addToDatabase(calculatedValue);
    }



}