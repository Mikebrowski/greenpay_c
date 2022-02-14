package fragment

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity.apply
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat.apply
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.greenpayremastered.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.common.io.Files.append
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import database.UserData
import models.KotlinPiePointsWithDate
import models.KotlinPointsData
import java.util.*
import kotlin.collections.ArrayList

class SecondFragment : Fragment() {
    private var pieChart: PieChart? = null

    private var userLoggedIn: TextView? = null
    private var userEmail: TextView? = null
    private var poengFraDB: TextView? = null

    private var mAuth: FirebaseAuth? = null
    private var dBRefUser: DatabaseReference? = null
    private var dBRefPoints: DatabaseReference? = null
    private var uid: String? = null

    private lateinit var dbReference : DatabaseReference
    private lateinit var kotlinPointsData: ArrayList<KotlinPointsData>

    private var errorText: String? = "Noe gikk galt"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_second, container, false)
        pieChart = v.findViewById(R.id.profilePieChart)
        userLoggedIn = v.findViewById(R.id.WhosLoggedIn)
        userEmail = v.findViewById(R.id.emailOfUser)
        poengFraDB = v.findViewById(R.id.showPointsDB)
        //poengFraDB = v.findViewById(R.id.displayDbPoeng)
        //poengFraDB = v.findViewById(R.id.Poengfradb)

        getDbData()
        setupPieChart()

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUserEmail()
        setProfileName()
        setTotalPoints()
    }

    private fun setProfileName() {
        mAuth = FirebaseAuth.getInstance()
        dBRefUser = FirebaseDatabase.getInstance().getReference("user")
        uid = mAuth!!.currentUser!!.uid
        if (uid != null) {
            dBRefUser!!.child(uid!!).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val usernameOnProfile = snapshot.getValue(UserData::class.java)!!.username
                    userLoggedIn!!.text = usernameOnProfile
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, errorText, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun setUserEmail() {
        mAuth = FirebaseAuth.getInstance()
        dBRefUser = FirebaseDatabase.getInstance().getReference("user")
        uid = mAuth!!.currentUser!!.uid
        if (uid != null)
        {
            dBRefUser!!.child(uid!!).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userEmailString = snapshot.getValue(UserData::class.java)!!.email
                    //String picture
                    userEmail!!.text = userEmailString
                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, errorText, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun setTotalPoints()
    {
        // calculate the totalpoints of user that is logged in
        mAuth = FirebaseAuth.getInstance()
        uid = mAuth!!.currentUser!!.uid
        if (uid != null)
        {
            dbReference = FirebaseDatabase.getInstance().getReference("PointsData")
            dbReference.addValueEventListener(object : ValueEventListener
            {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists())
                    {

                        /*
                        val test = arrayListOf<Int>()
                        val counter = intArrayOf()

                        for (userPointsData in snapshot.children)//Can also do WHILE
                        {
                            val pointsDataSnap = snapshot.getValue(KotlinPointsData::class.java)!!.totalpoints
                            //val pointsPointsDb = snapshot.getValue(KotlinPointsData::class.java)?.totalpoints
                            //val counter = pointsDataSnap!!.toInt()
                            if (pointsDataSnap != null) {
                                test.add(pointsDataSnap)
                                val counter =test.toString().toInt()
                            }
                            ///                            kotlinPointsData.add(pointsDataSnap!!)
                        }

                        poengFraDB?.setText(counter.sum())

*/
/*
                        kotlinPointsData
                        val totalPointsGained = kotlinPointsData.groupBy { it.username}.map {
                            KotlinPointsData().apply {
                                totalpoints = it.value.sumOf { it.totalpoints!! }

                            }
                        }
                        poengFraDB!!.setText(totalPointsGained.toString())
                        */


                        //poengFraDB.setText(totalPointsGained)


                        //poengFraDB!!.setText(pointsPointsDb.toString().sumOf { pointsPointsDb!! })
/*
                        if(usernameFromUserInput.equals(userLoggedIn))
                        {
                          poengFraDB!!.setText(pointsPointsDb.toString().sumOf { pointsPointsDb!! })
                        }
*/
                        /*
                        for (userPointsData in snapshot.children)//Can also do WHILE
                        {
                            val pointsDataSnap = userPointsData.getValue(KotlinPointsData::class.java)?.totalpoints
                            if (pointsDataSnap != null) {
                                poengFraDB.setText(pointsDataSnap)
                            }
                        }

                         */


                    }


                }
                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, errorText, Toast.LENGTH_SHORT).show()
                }
            })
        }


    }//setupemail end




    private fun setupPieChart() {
        pieChart!!.isDrawHoleEnabled = true
        pieChart!!.setUsePercentValues(true)

        pieChart!!.setEntryLabelTextSize(12f)
        pieChart!!.setEntryLabelColor(Color.BLACK)
        pieChart!!.centerText = "Poeng fordeling"
        pieChart!!.setCenterTextSize(10f)
        pieChart!!.description.isEnabled = false
        val l = pieChart!!.legend
        l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        l.orientation = Legend.LegendOrientation.VERTICAL
        l.setDrawInside(false)
        l.isEnabled = true
    }

    private fun loadPieChartData(points: ArrayList<KotlinPiePointsWithDate>) {
        val entries = ArrayList<PieEntry>()
        points.forEach {
            entries.add(PieEntry(it.totalpoints!!.toFloat(), it.username))
        }

//        entries.add(PieEntry(0.2f, "Food & Dining"))
//        entries.add(PieEntry(0.15f, "Medical"))
//        entries.add(PieEntry(0.10f, "Entertainment"))
//        entries.add(PieEntry(0.25f, "Electricity and Gas"))
//        entries.add(PieEntry(0.3f, "Housing"))
        val colors = ArrayList<Int>()
        for (color in ColorTemplate.MATERIAL_COLORS) {
            colors.add(color)
        }
        for (color in ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color)
        }
        val dataSet = PieDataSet(entries, "Poeng fordeling")
        dataSet.colors = colors
        val data = PieData(dataSet)
        data.setDrawValues(true)
        data.setValueFormatter(PercentFormatter(pieChart))
        data.setValueTextSize(12f)
        data.setValueTextColor(Color.BLACK)
        pieChart!!.data = data
        pieChart!!.invalidate()
        pieChart!!.animateY(1400, Easing.EaseInOutQuad)
    }


    private fun getDbData(){
        val piePoints: ArrayList<KotlinPiePointsWithDate> = arrayListOf<KotlinPiePointsWithDate>()
        dBRefPoints = FirebaseDatabase.getInstance().getReference("PointsData")
        dBRefPoints!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val points: ArrayList<KotlinPiePointsWithDate> = arrayListOf<KotlinPiePointsWithDate>()

                    for (userPointsData in snapshot.children)//Can also do WHILE
                    {
                        val pointsDataSnap = userPointsData.getValue(KotlinPiePointsWithDate::class.java)
                        points.add(pointsDataSnap!!)

/*
                        piePoints.addAll(points.distinctBy{it.initiativeName}.apply{ it ->
                            KotlinPiePointsWithDate(it.key, it.value.sumOf { it.totalpoints ?: 0 }, it.key)
                        })
*/

/*
                        piePoints.addAll(points.groupBy{ it.username}.map{ it ->
                            KotlinPiePointsWithDate(it.key, it.value.sumOf{ it.totalpoints ?: 0 },it.key)
                        })
                        */


                        piePoints.groupBy { it.username}.map { KotlinPiePointsWithDate().apply {
                            username = it.key
                            initiativeName = it.key
                            totalpoints = it.value.sumOf { it.totalpoints ?: 0 }
                        }}
                        /* DENNE SKAL være mest riktig
                        piePoints.addAll(points.groupBy{it.username}.map{KotlinPiePointsWithDate().apply {
                            username =it.key
                            initiativeName = it.key
                            totalpoints = it.value
                        }})
                        */
/*
                        points.groupBy { it.username}.map { KotlinPiePointsWithDate().apply {
                            username = it.key
                            initiativeName = it.key
                            totalpoints = it.value.sumOf { it.totalpoints ?: 0 }

                        }}

*/

/*
                        points.sortByDescending {it.totalpoints}
                        piePoints.groupBy { it.initiativeName }.map { points.apply {
                            it.key
                            it.value.sumOf { it.totalpoints ?: 0 }
                        }}
*/


                    }
                    loadPieChartData(piePoints)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, errorText, Toast.LENGTH_SHORT).show()
            }
        })

     }
}//end of secondfragment
