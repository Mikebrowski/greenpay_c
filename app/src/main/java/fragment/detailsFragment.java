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
    private String ARG_PARAM2 ="param2";

    private String mParam1;
    private String mParam2;

    public detailsFragment() {
        // Required empty public constructor
    }

    public static detailsFragment newInstance(String param1){
        detailsFragment fragment = new detailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1,param1);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null){
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.details_fragment, container, false);
        TextView typeDetails = view.findViewById(R.id.typeDetails);
        typeDetails.setText(mParam1);
        return view;
    }
}