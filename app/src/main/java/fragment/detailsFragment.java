package fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import java.util.Objects;

import database.UserData;
import models.PointsData;

public class detailsFragment extends Fragment {

    private static final String ARG_PARAM1 ="param1";
    private static final String ARG_PARAM2 ="param2";
    private static final String ARG_PARAM3 ="param3";
    private static final String ARG_PARAM4 ="param4";

    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;

    //private View mainView;

    private int counter = 0;



    public static detailsFragment newInstance(String param1, String param2, String param3, String param4){
        detailsFragment fragment = new detailsFragment();
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
            mParam3 = getArguments().getString(ARG_PARAM4);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View mainView = inflater.inflate(R.layout.details_fragment, container, false);

        TextView typeDetails = mainView.findViewById(R.id.typeDetails);
        TextView nameDetails = mainView.findViewById(R.id.nameDetails);
        TextView pointsDetails = mainView.findViewById(R.id.pointsDetails);
        TextView infoDetails = mainView.findViewById(R.id.infoText);

        Button plusButton = mainView.findViewById(R.id.plusB);
        Button minusButton = mainView.findViewById(R.id.minusB);
        Button sendToDbButton = mainView.findViewById(R.id.sendIntoDBB);

        TextView amountPresssesBox =mainView.findViewById(R.id.pointsAmount);
        TextView totalvaluebox =mainView.findViewById(R.id.totalValuedetails);


        typeDetails.setText(mParam1);
        nameDetails.setText(mParam2);
        pointsDetails.setText(mParam3);
        infoDetails.setText(mParam4);

        totalvaluebox.setVisibility(View.INVISIBLE);

               sendToDbButton.setText("Regn ut poeng");
        sendToDbButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendToDbButton.setText("Send dataen til databasen");
                totalvaluebox.setVisibility(View.VISIBLE);
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
                if (counter > 1) {
                    counter--;
                } else {
                    minusButton.setVisibility(View.INVISIBLE);
                }
                amountPresssesBox.setText(Integer.toString(counter));
            }
        });


        return mainView;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        //seeTotalPoints(totalvaluebox.setText());


    }// END Of onViewCreated

    /*CAULCULATING AND ADDTION METHODS*/


    /*
    private void seeTotalPoints() {
        //mainView.


        String correctPointFormat = Objects.requireNonNull(getView()).findViewById(R.id.pointsDetails).toString();
        int num = Integer.parseInt(correctPointFormat);

        String s = getView().findViewById(R.id.pointsAmount).toString();
        if (s.toLowerCase().startsWith("t"))
        {
            s = "1";
        }
        int pointsFromR2 = Integer.valueOf(s);
        int calculatedValue = Integer.valueOf(pointsFromR2 * num);


        //View result = getView().findViewById(R.id.totalValuedetails);

        //result.setText(String.valueOf(calculatedValue));
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
        userNameField.setText(currentDate);
    }
    */

}