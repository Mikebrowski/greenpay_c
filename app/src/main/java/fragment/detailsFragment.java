package fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.greenpayremastered.R;

public class detailsFragment extends Fragment {

    private static final String ARG_PARAM1 ="param1";
    private static final String ARG_PARAM2 ="param2";
    private static final String ARG_PARAM3 ="param3";
    private static final String ARG_PARAM4 ="param4";

    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;

    public detailsFragment() {
        // Required empty public constructor
    }

    public static detailsFragment newInstance(String param1, String param2, String param3, String param4){
        detailsFragment fragment = new detailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,param1);
        args.putString(ARG_PARAM2,param2);
        args.putString(ARG_PARAM3,param3);
        args.putString(ARG_PARAM3,param4);
        //args.putString(ARG);
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
        View view = inflater.inflate(R.layout.details_fragment, container, false);
        TextView typeDetails = view.findViewById(R.id.typeDetails);
        TextView nameDetails = view.findViewById(R.id.nameDetails);
        TextView pointsDetails = view.findViewById(R.id.pointsDetails);
        TextView infoDetails = view.findViewById(R.id.infoText);

        typeDetails.setText(mParam1);
        nameDetails.setText(mParam2);
        pointsDetails.setText(mParam3);
        infoDetails.setText(mParam4);


        return view;
    }
}