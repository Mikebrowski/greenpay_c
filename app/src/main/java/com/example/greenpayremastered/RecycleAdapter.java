package com.example.greenpayremastered;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import adapter.InitiativeData;
import adapter.Initiatives;
import fragment.FirstFragment;


public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.InitiativesHolder> implements Filterable {



        ArrayList<Initiatives> initiativesData;
        //List<Initiatives> filteredLIst = null;
        LayoutInflater inflater;


        Context context;
        IniClickInterface iniClickInterface;

        public List<InitiativeData> initiativesDatList = new ArrayList<>(); // HERE IS THE ISSUE ITS ALWAYS NULL EVEN IF YOU ADD
        public List<InitiativeData> getInitiativesFilter = new ArrayList<>(); //
        // ALTERNATIVET List<String>


        public RecycleAdapter(ArrayList<Initiatives> initiativesData, Context context,
                              IniClickInterface iniClickInterface, List<InitiativeData> initiativesDatList)
        {
            this.context= context;
            this.initiativesData = initiativesData;
            this.iniClickInterface= iniClickInterface;

            this.initiativesDatList = initiativesDatList;// PROBLEMET ER KANSKJE HER?
            this.getInitiativesFilter = initiativesDatList;// HVORFOR KAN DU IKKE SETTE this.getInitiativesFilter = initiativesData;
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
        public void onBindViewHolder(@NonNull InitiativesHolder holder, int position)
        {
            holder.pointsInitiatives.setText(initiativesData.get(position).getPoints());
            holder.txtInitiatives.setText(initiativesData.get(position).getName());
            holder.imgInitiatives.setImageResource(initiativesData.get(position).getImage());
        }


    class InitiativesHolder extends RecyclerView.ViewHolder{

            TextView txtInitiatives;
            ImageView imgInitiatives;
            TextView pointsInitiatives;

            public InitiativesHolder (@NonNull View itemView) {
                super(itemView);
                 txtInitiatives = itemView.findViewById(R.id.txt_animal_name);//DOES THIS WORK? Random
                 imgInitiatives = itemView.findViewById(R.id.img_animal);//DOES THIS WORK? Random
                 pointsInitiatives = itemView.findViewById(R.id.txtPoints);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        iniClickInterface.onItemClick(getAdapterPosition());
                        //OR
                        //iniClickInterface.onItemClick(getBindingAdapterPosition());
                    }
                });

            }
        }//InitiativesHolder

    @Override
    public Filter getFilter() {
            Filter filter = new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if(constraint == null|| constraint.length() == 0){
                        filterResults.values = getInitiativesFilter;
                        filterResults.count = getInitiativesFilter.size();
                    } else{
                      String searchString = constraint.toString().toLowerCase();
                      List<InitiativeData> initiativesDatLists = new ArrayList<>();
                      for (InitiativeData initiativesDataList:getInitiativesFilter){
                          if(initiativesDataList.getName().toLowerCase().contains(searchString)||initiativesDataList.getPoints().toLowerCase().contains(searchString)){
                              initiativesDatLists.add(initiativesDataList);
                          }
                      }
                      filterResults.values = initiativesDatLists;
                      filterResults.count = initiativesDatLists.size();
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    initiativesDatList = (List<InitiativeData>)results.values;
                    notifyDataSetChanged();}
            };
            return filter;
            //return iniFilter;
    }//end filter

    @Override
    public int getItemCount() {
            return initiativesData.size();
    }
    interface IniClickInterface{
        void onItemClick(int positionOfTheIni);
    }


    private Filter iniFilter = new Filter(){

        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<String> filteredList = new ArrayList<>();

            if(charSequence == null || charSequence.length() == 0){
                for (String name: filteredList) {
                    if (name.toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(name);
                     }
                    }// end of f
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;

            }


        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            //moviesList.clear();
           // moviesList.addAll((Collection<? extends String>) filterResults.values);
            notifyDataSetChanged();
        }
    };





/*

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        filteredLIst.clear();
        if (charText.length() == 0) {
            filteredLIst.addAll(initiativesData);
        } else {
            for (Initiatives wp : initiativesData) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    filteredLIst.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
*/

    /*
    public void filterList(List<initiativesData> filterList){
            initiativesData = filterList;
            notifyDataSetChanged();

            //positionOfItem = InitiativesHolder.getPostion(); This is bullshit??
            //notifyItemChanged(int positionOfItem);

    }
*/

}


