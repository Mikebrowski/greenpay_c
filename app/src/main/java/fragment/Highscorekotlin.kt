package fragment

import models.KotlinPointsData
import adapter.PointsKotlinAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.example.greenpayremastered.R
import com.google.firebase.firestore.auth.User
import java.lang.reflect.Array.get

// fragments are modular part of a activity
// YOU CAN SHOW MULTIPLE SCREENS THATS THE KEY HERE
// REUSEABLE IN MUTIPLE ACTIVITIES
// A FRAGMENT MUST RUN INSIDE A ACTIVITY


class Highscorekotlin : Fragment() {

    val rootRef = FirebaseDatabase.getInstance().reference

    private lateinit var dbReference: DatabaseReference
    private lateinit var recyclerviewFrag: RecyclerView
    private lateinit var kotlinPointsData: ArrayList<KotlinPointsData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_highscorekotlin, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerviewFrag = getView()!!.findViewById(R.id.recyclerViewfragX)
        recyclerviewFrag.layoutManager = LinearLayoutManager(activity)
        recyclerviewFrag.layoutManager = LinearLayoutManager(getView()?.context)// whats the diffrence?

        recyclerviewFrag.setHasFixedSize(true)
        kotlinPointsData = arrayListOf<KotlinPointsData>()
        getDbData()
    }

    private fun getDbData() {

        dbReference = FirebaseDatabase.getInstance().getReference("PointsData")
        dbReference.addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (userPointsData in snapshot.children)//Can also do WHILE
                    {
                        val pointsDataSnap = userPointsData.getValue(KotlinPointsData::class.java)
                        kotlinPointsData.add(pointsDataSnap!!)
                    }
                    kotlinPointsData.sortByDescending { it.totalpoints }

                    val highscoreTest = kotlinPointsData.groupBy { it.username}.map { KotlinPointsData().apply {
                        username = it.key
                        totalpoints = it.value.sumOf { it.totalpoints ?: 0 }}}

                    recyclerviewFrag.adapter = PointsKotlinAdapter(highscoreTest)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // TODO: 26.01.2022
            }
        })
    }
}//END OF HIGHSCORE

