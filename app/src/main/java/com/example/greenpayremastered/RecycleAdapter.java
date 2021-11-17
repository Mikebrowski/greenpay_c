package com.example.greenpayremastered;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import Adapter.Initiatives;


public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.InitiativesHolder> implements Filterable {


        ArrayList<Initiatives> initiativesData;
        Context context;
        IniClickInterface iniClickInterface;

        public RecycleAdapter(ArrayList<Initiatives> initiativesData, Context context, IniClickInterface iniClickInterface){
            this.context= context;
            this.initiativesData = initiativesData;
            this.iniClickInterface= iniClickInterface;
        }

        //RECYLERVIEW ONLY HAVE 10 VIEWS
        //IF WE HAD MORE ITEMS IT WOULD DELETE THE ITEMS TO PRESERVE MEMORY
        //THIS IS HOW YOU SAVE MEMORY

        @NonNull
        @Override
        public InitiativesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.card_row,parent,false);// attactToRootALWAYS FALSE TO PREVENT CRASH OTHERWISE OBJECTS FLYYYYY WEEE
            return new InitiativesHolder(view);
        }
        @Override
        public void onBindViewHolder(@NonNull InitiativesHolder holder, int position) {
            //POSITION IS WORKS LIKE AN ARRAY 0 STARTS 1
/*
            initiativesData.forEach(item -> {
                holder.txtInitiatives.setText("test");
            });
            SYK FETT METODE
 */
            holder.txtInitiatives.setText(initiativesData.get(position).getName());
            holder.imgInitiatives.setImageResource(initiativesData.get(position).getImage());
        }


    class InitiativesHolder extends RecyclerView.ViewHolder{

            TextView txtInitiatives;
            ImageView imgInitiatives;

            public InitiativesHolder (@NonNull View itemView) {
                super(itemView);
                 txtInitiatives = itemView.findViewById(R.id.txt_animal_name);//DOES THIS WORK? Random
                 imgInitiatives = itemView.findViewById(R.id.img_animal);//DOES THIS WORK? Random

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        iniClickInterface.onItemClick(getAdapterPosition());
                    }
                });

            }
        }//InitiativesHolder

    @Override
    public Filter getFilter() {
        return iniFilter;
    }

    @Override
    public int getItemCount() {
        return initiativesData.size();
    }
    interface IniClickInterface{
        void onItemClick(int positionOfTheIni);
    }


    private Filter iniFilter = new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            //List<initiativesData> filteredList = new ArrayList<>()
            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }
    };


    /*
    public void filterList(List<initiativesData> filterList){
            initiativesData = filterList;
            notifyDataSetChanged();
            //HOW the fuck do I grab that postion
            //positionOfItem = InitiativesHolder.getPostion(); This is bullshit??
            //notifyItemChanged(int positionOfItem);

    }
*/

}


