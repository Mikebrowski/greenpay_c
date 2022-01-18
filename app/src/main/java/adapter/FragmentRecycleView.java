package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenpayremastered.R;

import java.util.ArrayList;
import java.util.List;

import models.InitiativeDbGoals;

//THIS CLASS WILL USE A LIST INTO FRAGMENTS MAKES IT EASIER TO SEARCH



public class FragmentRecycleView extends RecyclerView.Adapter<FragmentRecycleView.MyViewHolder>
{
    ArrayList<InitiativeDbGoals> databaseList;
    ItemClickListener clickListener;
    List<InitiativeDbGoals> datalist;
    FragRecycleInterface fragRecycleInterface;

    public FragmentRecycleView(ArrayList<InitiativeDbGoals> databaseList, ItemClickListener clickListener) {
        this.databaseList = databaseList;
        this.clickListener = clickListener;
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


        //SUGGESTION ON STACK OVERFLOW based on the error mLastPosition = holder.getAdapterPosition();
        //HOWEVER getAdapterPostion has become now two getBindingAdapterPosition and getAbsoluteAdapterPosition

        //getBindingAdapterPosition should be used when you want to get the adapter position (if it still exists in the adapter). If it no longer exists in the adapter, it will return -1(NO_POSITION).
        //getAbsoluteAdapterPosition should be used to get the position as the RecyclerView sees it. For example, if an item has been deleted, but not yet removed from theViewHolder

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //clickListener.onItemClick(databaseList.get(position));
                fragRecycleInterface.onItemClick(holder.getBindingAdapterPosition()); // This one dont work because it can't grab the correct value or element
            }
        });
    }

    @Override
    public int getItemCount() {
        return databaseList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtNameDb;
        TextView txtPointsDb;
        //TextView txtImgDb;
        TextView txtType;
        //Color

        //db_cardview recycler card


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNameDb = itemView.findViewById(R.id.db_ini_name);
            txtPointsDb = itemView.findViewById(R.id.db_ini_points);
            //txtImgDb = itemView.findViewById(R.id.db_ini_img);
            txtType = itemView.findViewById(R.id.typetxt);
        }
    }

    //CLICKABLE FUNCTION SAME AS recycleadapter BUT WITH ABIT DIFFRENT METHODS


    public interface FragRecycleInterface {
        void onItemClick(int posIntFragRes);
    }
}
