package com.example.miniproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter2_ForCat extends RecyclerView.Adapter<RecyclerViewAdapter2_ForCat.MyViewHolder>{

    private ArrayList<Category> catList;
    private RecyclerViewClickListner listner;

    public RecyclerViewAdapter2_ForCat(ArrayList<Category> list, RecyclerViewClickListner listner) {
        this.listner = (RecyclerViewClickListner)listner;
        this.catList = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nameCat;

        public MyViewHolder(final View view) {
            super(view);
            nameCat = view.findViewById(R.id.tv_list_catName);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Onclick works", Toast.LENGTH_LONG).show();
            listner.onClick(view, getBindingAdapterPosition());
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_list_item, parent, false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String name = catList.get(position).getName();
        holder.nameCat.setText(name);
    }



    @Override
    public int getItemCount() {
        return catList.size();
    }

    public interface RecyclerViewClickListner{
        void onClick(View v, int position);
    }

}
