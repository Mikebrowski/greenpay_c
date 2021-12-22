package fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greenpayremastered.R;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

import adapter.InitiativeData;
import adapter.Initiatives;


public class FirstFragment extends Fragment {

    private RecyclerView recyclerviewfrag;
    private DatabaseReference databaseReference;
    private ArrayList<Initiatives> initiatives = new ArrayList<>();
    private List<InitiativeData> initiativesDatList = new ArrayList<>();

    public FirstFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_first, container, false);


        View view = inflater.inflate(R.layout.fragment_first,container, false);
        RecyclerView recyclerviewfrag = view.findViewById(R.id.recycle_view2);
        //RecycleAdapter recycleAdapter = new RecycleAdapter(initiatives,  initiativesDatList);
        recyclerviewfrag.setHasFixedSize(true);
        //recyclerviewfrag.setAdapter(recycleAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //RecyclerView recyclerviewfrag = view.findViewById(R.id.recyclerviewfrag);
        //RecycleAdapter recycleAdapter = new RecycleAdapter(initiatives, this, this, initiativesDatList);
        //recyclerviewfrag.setHasFixedSize(true);
        //recyclerviewfrag.setAdapter(recycleAdapter);


    }


}