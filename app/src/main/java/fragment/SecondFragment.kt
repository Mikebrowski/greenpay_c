package fragment

import android.app.ActionBar
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
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
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
    private var horizontalBarChart: HorizontalBarChart? = null

    private var userLoggedIn: TextView? = null
    private var userEmail: TextView? = null
    private var poengFraDB: TextView? = null

    private var mAuth: FirebaseAuth? = null
    private var dBRefUser: DatabaseReference? = null
    private var dBRefPoints: DatabaseReference? = null
    private var uid: String? = null

    private lateinit var dbReference: DatabaseReference
    private lateinit var kotlinPointsData: ArrayList<KotlinPointsData>

    private var errorText: String? = "Noe gikk galt"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_second, container, false)
        pieChart = v.findViewById(R.id.profilePieChart)
        userLoggedIn = v.findViewById(R.id.WhosLoggedIn)
        userEmail = v.findViewById(R.id.emailOfUser)
        poengFraDB = v.findViewById(R.id.showPointsDB)
        horizontalBarChart = v.findViewById(R.id.h_barchart)



        getDbData()
        setupPieChart()
        //initializeBarChart()

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
        if (uid != null) {
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

    private fun setTotalPoints() {

//   dBRefPoints =  FirebaseDatabase.getInstance().getReference("PointsData").child( FirebaseAuth.getInstance().currentUser!!.uid)
        // calculate the totalpoints of user that is logged in
        mAuth = FirebaseAuth.getInstance()
        uid = mAuth!!.currentUser!!.uid
        val piePoints: ArrayList<KotlinPiePointsWithDate> =
            arrayListOf<KotlinPiePointsWithDate>()
        if (uid != null) {
            dbReference = FirebaseDatabase.getInstance().getReference("PointsData")
            dbReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {


                        val points: ArrayList<KotlinPiePointsWithDate> =
                            arrayListOf<KotlinPiePointsWithDate>()

                        for (userPointsData in snapshot.children)//Can also do WHILE
                        {
                            val pointsDataSnap =
                                userPointsData.getValue(KotlinPiePointsWithDate::class.java)
                            points.add(pointsDataSnap!!)

                            piePoints.addAll(points.groupBy { it.username }.map { it ->
                                KotlinPiePointsWithDate(
                                    it.key,
                                    it.value.sumOf { it.totalpoints ?: 0 },
                                    it.key
                                )
                            })

                        }
                        val sumOf = piePoints.sumOf { it.totalpoints ?: 0 }
                        poengFraDB?.setText("test")



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
        //hasMap = {"Ali", listof(), "Mikal", listOf()}

        points.groupBy { it.initiativeName }.map { map ->
            entries.add(PieEntry(map.value.sumOf {
                it.totalpoints ?: 0 }.toFloat(),
                map.key
            ))
        }

        /*
         //hasMap = {"Ali", listof(), "Mikal", listOf()}
        points.groupBy { it.username }.map { map ->
            entries.add(PieEntry(map.value.sumOf { it.totalpoints ?: 0 }.toFloat(), map.key))
        }
        * */



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


    private fun getDbData() {
        val piePoints: ArrayList<KotlinPiePointsWithDate> = arrayListOf<KotlinPiePointsWithDate>()
        dBRefPoints =  FirebaseDatabase.getInstance().getReference("PointsData").child( FirebaseAuth.getInstance().currentUser!!.uid)

        dBRefPoints!!.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val points: ArrayList<KotlinPiePointsWithDate> =
                        arrayListOf<KotlinPiePointsWithDate>()

                    for (userPointsData in snapshot.children)//Can also do WHILE
                    {
                        val pointsDataSnap =
                            userPointsData.getValue(KotlinPiePointsWithDate::class.java)
                        points.add(pointsDataSnap!!)

/*
                        piePoints.addAll(points.distinctBy{it.initiativeName}.apply{ it ->
                            KotlinPiePointsWithDate(it.key, it.value.sumOf { it.totalpoints ?: 0 }, it.key)
                        })
*/


                        piePoints.addAll(points.groupBy { it.initiativeName }.map { it ->
                            KotlinPiePointsWithDate(
                                it.key,
                                it.value.sumOf { it.totalpoints ?: 0 },
                                it.value.first().username
                            )
                        })
                    }
                    loadPieChartData(piePoints)

                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, errorText, Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun initializeBarChart() {
        // Create bars
        val points: ArrayList<BarEntry> = ArrayList()
        points.add(BarEntry(0f, 883f))
        points.add(BarEntry(0f, 77f))
        points.add(BarEntry(0f, 66f))
        points.add(BarEntry(0f, 44f))
        points.add(BarEntry(0f, 55f))
        points.add(BarEntry(0f, 33f))
        // Add bars to a bar set
        val barSet = BarDataSet(points, "Tenses")
        // Create a BarData object and assign it to the chart

        val barData = BarData(barSet)
        // Display it as a percentage

        barData.setValueFormatter(PercentFormatter())
        //barChart!!.data = barData

        barSet.setDrawValues(true)
        //barChart.invalidate()

        horizontalBarChart!!.invalidate()
    }

}//end of secondfragment