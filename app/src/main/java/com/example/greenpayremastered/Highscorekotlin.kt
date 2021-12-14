package com.example.greenpayremastered

import adapter.InitiativeData
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import database.UserData


// fragments are modular part of a activity
// YOU CAN SHOW MULTIPLE SCREENS THATS THE KEY HERE
// REUSEABLE IN MUTIPLE ACTIVITIES
// A FRAGMENT MUST RUN INSIDE A ACTIVITY



class Highscorekotlin : Fragment() {

    private val dbinidata = FirebaseDatabase.getInstance().getReference("PointsData/")
    private var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        print("does this work?")
        //whatisthis()
       // getRealtimeupdate()
        showDbData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_highscorekotlin, container, false)
    }

    fun showDbData(){
        mAuth = FirebaseAuth.getInstance()
        //val dbReference = FirebaseDatabase.getInstance().getReference("PointsData/").child(mAuth!!.uid!!)
        val childEventListener = object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val initiativeData = snapshot.getValue(InitiativeData::class.java)
                    initiativeData?.name = snapshot.key
                    initiativeData?.points = snapshot.key
                }
        override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onChildRemoved(snapshot: DataSnapshot) {}

        override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}

        override fun onCancelled(error: DatabaseError) {}

            } //Child eventlistener
        }// showDbData




    fun getRealtimeupdate(){
        //dbinidata.addChildEventListener(childEventListener)
    }
    //override ONCLEAR?


/*
    fun whatisthis(){
        var a = 10
        var b = 20 // GOOOD

        //c = 2019// BAD
        //
        //var 123abc = 20 BAD
        //var 123ABCSFKLSJDF = 50;
        var c = a +b
        print(c)
        print("confused")
    }
*/

    }

