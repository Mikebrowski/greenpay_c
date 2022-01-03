package models;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenpayremastered.R;

import java.util.ArrayList;

import adapter.InitiativeDbGoals;

//THIS CLASS WILL USE A LIST INTO FRAGMENTS MAKES IT EASIER TO SEARCH



public class FragmentRecycleView extends RecyclerView.Adapter<FragmentRecycleView.MyViewHolder>
{
    ArrayList<InitiativeDbGoals> databaseList;

    public FragmentRecycleView(ArrayList<InitiativeDbGoals> databaseList) {
        this.databaseList = databaseList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View viewRecycle = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclesearchfragment, parent, false);
        return new MyViewHolder(viewRecycle);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtNameDb.setText(databaseList.get(position).getName());
        holder.txtPointsDb.setText(databaseList.get(position).getPoints());//Why cant i put Double on poitns
        //holder.txtImgDb ????? cant use settext
        holder.txtType.setText(databaseList.get(position).getType());
    }

    @Override
    public int getItemCount() {
        return databaseList.size();
        //return arrayListDb.size;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameDb;
        TextView txtPointsDb;
        TextView txtImgDb;
        TextView txtType;
        //Color

        //db_cardview recycler card


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNameDb = itemView.findViewById(R.id.db_ini_name);
            txtPointsDb = itemView.findViewById(R.id.db_ini_points);
            txtImgDb = itemView.findViewById(R.id.db_ini_img);
            txtType = itemView.findViewById(R.id.typetxt);
        }
    }
}
