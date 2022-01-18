package fragment;

import static com.google.firebase.inappmessaging.internal.Logging.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greenpayremastered.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import adapter.RecycleAdapter;
import models.InitiativeDbGoals;
import adapter.FragmentRecycleView;


public class FirstFragment extends Fragment implements FragmentRecycleView.FragRecycleInterface {

    private RecyclerView recyclerviewFrag;
    private DatabaseReference databaseReference;

    private FirebaseFirestore DbCon;
    private FragmentRecycleView adapter;

    FragmentRecycleView.FragRecycleInterface fragRecycleInterface;

    private final ArrayList<InitiativeDbGoals> datalist = new ArrayList<>();

    FragmentRecycleView fragmentRecycleView = new FragmentRecycleView(ArrayList datalist, ItemClickListener clickListener );




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

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //fetchDatabase();
        //eventChangeListener();

        RecyclerView recyclerviewFrag = getView().findViewById(R.id.recycleViewDb);


        // EITHER LINEAR OR GIRD
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);


        recyclerviewFrag.setLayoutManager(gridLayoutManager);
        recyclerviewFrag.setHasFixedSize(true);



        recyclerviewFrag.setAdapter(fetchDatabase());




        //recyclerviewfrag.setAdapter(recycleAdapter);

    }

    private void setupResCyclerview(){

        // POSSIBLE SORTING SOLUTION
        CollectionReference reference = DbCon.collection("Initiatives-goals");
        Query query = reference.orderBy("name", Query.Direction.ASCENDING);
    }

    public FragmentRecycleView fetchDatabase() {



        adapter = new FragmentRecycleView(datalist, clickListener);

        DbCon = FirebaseFirestore.getInstance();
        DbCon.collection("Initiatives-goals").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        //Com

                        //Collections.sort(datalist);


                        for(DocumentSnapshot d:list) {
                            Map<String, Object> data = d.getData();

                            datalist.add(new InitiativeDbGoals(data.get("name").toString(),data.get("points").toString(),data.get("type").toString(), data.get("imgpath").toString()));

                        }
                        adapter.notifyDataSetChanged();
                        //recyclerviewFrag.setAdapter(adapter);
                    }

                });
/*
        Collections.sort(datalist, new Comparator<InitiativeDbGoals>() {
            @Override
            public int compare(InitiativeDbGoals o1, InitiativeDbGoals o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        Collections.reverse(datalist);
*/
        adapter.notifyDataSetChanged();



        return adapter;


    }

    private void eventChangeListener(){
        DbCon = FirebaseFirestore.getInstance();
        DbCon.collection("Initiatives-goals").orderBy("name", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                for(DocumentChange dc : value.getDocumentChanges()){
                    if (dc.getType() == DocumentChange.Type.ADDED){
                        datalist.add(dc.getDocument().toObject(InitiativeDbGoals.class));
                    }
                    adapter.notifyDataSetChanged();
                }

            }
        });

        }//eventchangelistener

    public void viewFirestore(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Initiatives-goals")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void getListItems() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Initiatives-goals").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {


                                List<DocumentSnapshot> test = documentSnapshots.getDocuments();
                                ArrayList<InitiativeDbGoals> idg = new ArrayList<>();
                                for(DocumentSnapshot ds:test)
                                {
                                Map<String, Object> data = ds.getData();
                                idg.add(new InitiativeDbGoals(data.get("name").toString(),data.get("points").toString(),data.get("type").toString(), (String)data.get("imgpath")));

                                }
                                datalist.addAll(idg);
                                Log.d(TAG, "onSuccess: " + datalist);
                        }
                      }
            }
        );}


        public void onItemClick()


        @Override
        public void onItemClick(int posIntFragRes){


            Fragment fragment = detailsFragment.newInstance(datalist.toString());

            FragmentTransaction transaction = Objects.requireNonNull(getActivity()).getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragmentContainerView2, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }


}//end of frag



