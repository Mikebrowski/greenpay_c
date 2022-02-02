package fragment

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.greenpayremastered.R
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import database.UserData
import models.KotlinPiePointsWithDate
import java.util.*

class SecondFragment : Fragment() {
    private var pieChart: PieChart? = null
    private var WhosLoggedIn: TextView? = null
    private var mAuth: FirebaseAuth? = null
    private var dBRefUser: DatabaseReference? = null
    private var dBRefPoints: DatabaseReference? = null
    private var uid: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_second, container, false)
        pieChart = v.findViewById(R.id.profilePieChart)
        WhosLoggedIn = v.findViewById(R.id.WhosLoggedIn)
        getDbData()
        setupPieChart()
        setProfileName()
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupPieChart() {
        pieChart!!.isDrawHoleEnabled = true
        pieChart!!.setUsePercentValues(true)
        pieChart!!.setEntryLabelTextSize(12f)
        pieChart!!.setEntryLabelColor(Color.BLACK)
        pieChart!!.centerText = "Spending by Category"
        pieChart!!.setCenterTextSize(24f)
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
        points.forEach { it ->
            entries.add(PieEntry(it.totalpoints!!.toFloat(), it.name))
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
        val dataSet = PieDataSet(entries, "Expense Category")
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

    fun setProfileName() {
        mAuth = FirebaseAuth.getInstance()
        dBRefUser = FirebaseDatabase.getInstance().getReference("user")
        uid = mAuth!!.currentUser!!.uid
        if (uid != null) {
            dBRefUser!!.child(uid!!).addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val usernameOnProfile = snapshot.getValue(UserData::class.java)!!.username
                    //String picture
                    WhosLoggedIn!!.text = usernameOnProfile
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(context, "Noe gikk galt", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun getDbData() {
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

                        piePoints.addAll(points.groupBy { it.name }.map { it ->
                            KotlinPiePointsWithDate(it.key, it.value.sumOf { it.totalpoints ?: 0 })
                        })

                    }
                    loadPieChartData(piePoints)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // TODO: 26.01.2022
            }
        })

    }
}