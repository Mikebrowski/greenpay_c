package adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greenpayremastered.R
import models.KotlinPointsData

class PointsKotlinAdapter (private val KotlinPointsData: ArrayList<KotlinPointsData>) : RecyclerView.Adapter<PointsKotlinAdapter.MyViewHolder>()
{

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.highscorelist, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = KotlinPointsData[position]

        holder.usernameFromDb.text = currentItem.username
        holder.totalpointsFromDb.text = currentItem.totalpoints.toString()
        //holder.datafromDb.text = currentItem.currentDateS.toString()
    }

    override fun getItemCount(): Int {
        return KotlinPointsData.size
    }
    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        val usernameFromDb : TextView = itemView.findViewById(R.id.usernametxt)
        val totalpointsFromDb : TextView = itemView.findViewById(R.id.totalpointsTxt)
        //val datafromDb : TextView = itemView.findViewById(R.id.currentdateDb)

    }

    fun caculateTotalPointsByUser(): Int {
        var totalpointsTogether: Int = 10;
        //ADD CODE THAT GRABS FROM DB
        //val map = dataSnapshot.getValue(Map::class.java) as Map<String, String>
        return totalpointsTogether
    }





}