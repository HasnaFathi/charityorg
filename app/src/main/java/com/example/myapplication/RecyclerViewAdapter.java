package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.TestLooperManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private ArrayList<Project> projectList;
    private RecyclerViewClickListner listner;

    public RecyclerViewAdapter(ArrayList<Project> list, RecyclerViewClickListner listner) {
        this.listner = listner;
        this.projectList = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView nametv;
        private TextView destv;

        public MyViewHolder(final View view) {
            super(view);
            nametv = view.findViewById(R.id.tv_rv_name);
            destv = view.findViewById(R.id.tv_rv_des);

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
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {
        String name = projectList.get(position).getName();
        String des = projectList.get(position).getDescription();
        holder.nametv.setText(name);
        holder.destv.setText(des);

    }

    @Override
    public int getItemCount() {
        return projectList.size();
    }

    public interface RecyclerViewClickListner{
        void onClick(View v, int position);
    }
}
