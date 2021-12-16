package adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greenpayremastered.R

class PointsKotlinAdapter (private val items: List<PointsData>) :
    RecyclerView.Adapter<PointsKotlinAdapter.MyRecyclerViewDataHolder>()
{
    inner class MyRecyclerViewDataHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerViewDataHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.highscorelist, parent, false)
        return MyRecyclerViewDataHolder(view)
    }

    override fun onBindViewHolder(holder: MyRecyclerViewDataHolder, position: Int) {
        val currentItem: PointsData = items[position]

        val tvNumber: TextView = holder.itemView.findViewById(R.id.tvNumber)
        tvNumber.text = currentItem.totalpoints.toString()

        //IDEA HERE WILL BE TO ADD ALL TOTAL POINTS SO I MIGHT NEED A METHOD

        val tvNumbersInText: TextView = holder.itemView.findViewById(R.id.tvNumbersInText)
        tvNumbersInText.text = currentItem.username
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun caculateTotalPointsByUser(): Int {
        var totalpointsTogether: Int = 10;
        //ADD CODE THAT GRABS FROM DB


        //val map = dataSnapshot.getValue(Map::class.java) as Map<String, String>

        return totalpointsTogether
    }





}