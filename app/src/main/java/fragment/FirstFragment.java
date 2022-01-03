package fragment;

import static com.google.firebase.inappmessaging.internal.Logging.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.greenpayremastered.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.InitiativeData;
import adapter.Initiatives;


public class FirstFragment extends Fragment {

    private RecyclerView recyclerviewfrag;
    private DatabaseReference databaseReference;
    private ArrayList<Initiatives> initiatives = new ArrayList<>();
    private List<InitiativeData> initiativesDatList = new ArrayList<>();
    private FirebaseFirestore DbCon;



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


        //recyclerviewfrag.setLayoutManager(new GridLayoutManager(getContext()));
        //RecycleAdapter recycleAdapter = new RecycleAdapter(initiatives,  initiativesDatList);
        recyclerviewfrag.setHasFixedSize(true);
        //recyclerviewfrag.setAdapter(recycleAdapter);

        DbCon = FirebaseFirestore.getInstance();
        DbCon.collection("Initiatives-goals").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

                    @Override
                    public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots) {
                        List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                    }
                });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //RecyclerView recyclerviewfrag = view.findViewById(R.id.recyclerviewfrag);
        //RecycleAdapter recycleAdapter = new RecycleAdapter(initiatives, this, this, initiativesDatList);
        //recyclerviewfrag.setHasFixedSize(true);
        //recyclerviewfrag.setAdapter(recycleAdapter);
        getFirestore();
        viewFirestore();

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


}