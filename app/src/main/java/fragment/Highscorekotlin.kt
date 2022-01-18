package fragment

import models.KotlinPointsData
import adapter.PointsKotlinAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.example.greenpayremastered.R

// fragments are modular part of a activity
// YOU CAN SHOW MULTIPLE SCREENS THATS THE KEY HERE
// REUSEABLE IN MUTIPLE ACTIVITIES
// A FRAGMENT MUST RUN INSIDE A ACTIVITY



class Highscorekotlin : Fragment() {

    val rootRef = FirebaseDatabase.getInstance().reference

    //var pointsData : List<PointsData>? = null


    private lateinit var dbReference : DatabaseReference
    private lateinit var recyclerviewFrag : RecyclerView
    private lateinit var kotlinPointsData: ArrayList<KotlinPointsData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
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




        /* COMPERATOR KOTLIN
        myItems.sortWith(Comparator { lhs, rhs ->

            if (lhs.name > rhs.name) -1 else if (lhs.id < rhs.id) 1 else 0
        })
        */



        dbReference = FirebaseDatabase.getInstance().getReference("PointsData")
        dbReference.addValueEventListener(object : ValueEventListener
        {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (userPointsData in snapshot.children)//Can also do WHILE
                    {
                        val pointsDataSnap = userPointsData.getValue(KotlinPointsData::class.java)
                        kotlinPointsData.add(pointsDataSnap!!)
                        //LIST HAD NO ADD FUNCTION
                    }
                    kotlinPointsData.sortBy {pointsData -> pointsData.totalpoints }
                    recyclerviewFrag.adapter = PointsKotlinAdapter(kotlinPointsData)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


/*

    fun sortList(){\
        //TODO: MUTABLE LIST for sorting with COMPARATOR to value tv1 to tv2 ect
        //MUTABLE LIST NOT UNMUTABLE BECAUSE LISTOF IS NOTABLE


        b1!!.setOnClickListener(View.OnClickListener {
            fun onClick(v: View?) {
                b1!!.setText("test")
            }
        })
    }
*/
    fun addRecyclerView(){
        //val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        // create list of RecyclerViewData


        //var recyclerViewData = mutableListOf<PointsData>()

        //PointsData (String totalpoitns, String Name)
        //var recyclerViewData2 = MutableList<PointsData>()
        //recyclerViewData = ad(snap, "One")
        /*
        recyclerViewData = recyclerViewData + RecyclerViewData("1", "One")
        recyclerViewData = recyclerViewData + RecyclerViewData("2", "Two")
        recyclerViewData = recyclerViewData + RecyclerViewData("3", "Three")
        recyclerViewData = recyclerViewData + RecyclerViewData("4", "Four")
        recyclerViewData = recyclerViewData + RecyclerViewData("5", "Five")
        recyclerViewData = recyclerViewData + RecyclerViewData("6", "Six")
        recyclerViewData = recyclerViewData + RecyclerViewData("7", "Seven")
        recyclerViewData = recyclerViewData + RecyclerViewData("8", "Eight")
        recyclerViewData = recyclerViewData + RecyclerViewData("9", "Nine")
        recyclerViewData = recyclerViewData + RecyclerViewData("10", "Ten")
        recyclerViewData = recyclerViewData + RecyclerViewData("11", "Eleven")
        recyclerViewData = recyclerViewData + RecyclerViewData("12", "Twelve")
        recyclerViewData = recyclerViewData + RecyclerViewData("13", "Thirteen")
        recyclerViewData = recyclerViewData + RecyclerViewData("14", "Fourteen")
        recyclerViewData = recyclerViewData + RecyclerViewData("15", "Fifteen")
        */
        // create a vertical layout manager

        //val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        // create instance of MyRecyclerViewAdapter
        //val myRecyclerViewAdapter = MyRecyclerViewAdapter(recyclerViewData)

        // attach the adapter to the recycler view
        //recyclerView.adapter = myRecyclerViewAdapter

        // also attach the layout manager
        //recyclerView.layoutManager = layoutManager

        // make the adapter the data set
        // changed for the recycler view
        //myRecyclerViewAdapter.notifyDataSetChanged()
    }
/*
    fun showDbData(){
        mAuth = FirebaseAuth.getInstance()
        //val dbReference = FirebaseDatabase.getInstance().getReference("PointsData/").child(mAuth!!.uid!!)
        val childEventListener = object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val initiativeData = snapshot.getValue(InitiativeData::class.java)
                    initiativeData?.name = snapshot.key
                    //initiativeData?.points = snapshot.key

                }
        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onChildRemoved(snapshot: DataSnapshot) {}

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onCancelled(error: DatabaseError) {}

            } //Child eventlistener
        }// showDbData
    */




}


