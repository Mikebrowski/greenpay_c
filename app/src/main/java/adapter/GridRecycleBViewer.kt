package adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greenpayremastered.R
import com.squareup.picasso.Picasso
import fragment.VisBaekraft
import models.BaerkraftDataModel

class GridRecycleBViewer(var BaarkraftList: ArrayList<BaerkraftDataModel>?, visBaekraft: VisBaekraft): RecyclerView.Adapter<GridRecycleBViewer.MyViewHolderGrid>() {

    //var baarkraftList = emptyList<BaerkraftDataModel>()

    internal fun setBaerkraftDataModel(baarkraftList: ArrayList<BaerkraftDataModel>) {
        this.BaarkraftList = baarkraftList
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridRecycleBViewer.MyViewHolderGrid
    {
        var view =LayoutInflater.from(parent.context).inflate(R.layout.grid_card_row, parent, false)
        return MyViewHolderGrid(view)
    }

    override fun onBindViewHolder(holder: GridRecycleBViewer.MyViewHolderGrid, position: Int) {
        var dataFromBlist = BaarkraftList!![position]
        holder.title.text = dataFromBlist.title
        Picasso.get()
            .load(dataFromBlist.imgPath!!)
            .into(holder.image)
        holder.beskrivelse.text = dataFromBlist.beskrivelse
    }
    class MyViewHolderGrid(itemView : View) : RecyclerView.ViewHolder(itemView){
        var image:ImageView = itemView.findViewById(R.id.gridCardImage)
        var title:TextView = itemView.findViewById(R.id.titleGrid)
        var beskrivelse:TextView = itemView.findViewById(R.id.bbeskrivelse)
    }


    override fun getItemCount(): Int {
        return BaarkraftList!!.size
    }

}