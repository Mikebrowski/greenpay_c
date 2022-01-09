package fragment;

import static com.google.firebase.inappmessaging.internal.Logging.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.InitiativeDbGoals;
import adapter.FragmentRecycleView;


public class FirstFragment extends Fragment {

    private RecyclerView recyclerviewFrag;
    private DatabaseReference databaseReference;
    //private ArrayList<Initiatives> initiatives = new ArrayList<>();
    //private List<InitiativeData> initiativesDatList = new ArrayList<>();
    private FirebaseFirestore DbCon;
    private FragmentRecycleView adapter;
    private ArrayList<InitiativeDbGoals> datalist = new ArrayList<>();

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

        RecyclerView recyclerviewFrag = getView().findViewById(R.id.recycleViewDb);

        recyclerviewFrag.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerviewFrag.setHasFixedSize(true);
        recyclerviewFrag.setAdapter(new FragmentRecycleView(datalist));
        
        fetchDatabase();

        //recyclerviewfrag.setAdapter(adapter);
        //recyclerviewfrag.setHasFixedSize(true);
        //recyclerviewfrag.setAdapter(recycleAdapter);




        //fetchDatabase();
        //getFirestore();
        //viewFirestore();
        //getListItems();
    }

    public void fetchDatabase() {
        adapter = new FragmentRecycleView(datalist);

        DbCon = FirebaseFirestore.getInstance();
        DbCon.collection("Initiatives-goals").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                        for(DocumentSnapshot d:list) {
                            Map<String, Object> data = d.getData();
                            datalist.add(new InitiativeDbGoals(data.get("name").toString(),data.get("points").toString(),data.get("type").toString(), (String)data.get("imgpath")));
                        }
                    }
                });


    }

    public void getFirestore() {

        /*
        Map<String, Object> user = new HashMap<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
*/
    }
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



                            // Convert the whole Query Snapshot to a list of objects directly! No need to fetch each document.
                            //List<InitiativeDbGoals> types = documentSnapshots.toObjects(InitiativeDbGoals.class);
                            // Add all to your list
                            //datalist.addAll(types);
                            //Log.d(TAG, "onSuccess: " + datalist);
                        }
                      }
            }
        );}
}//end of frag



