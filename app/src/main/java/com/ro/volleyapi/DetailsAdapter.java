package com.ro.volleyapi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {

    MainActivity mainActivity;
    ArrayList <DetailsModel> detailsModels;

    public DetailsAdapter(MainActivity mainActivity, ArrayList<DetailsModel> detailsModels) {
        this.mainActivity = mainActivity;
        this.detailsModels = detailsModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_user,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText("User Id : " +detailsModels.get(position).getUserId());
        holder.id.setText("Id : " +detailsModels.get(position).getId());
        holder.title.setText("Title : " +detailsModels.get(position).getTitle());
        holder.body.setText("Description : " +detailsModels.get(position).getBody());

    }

    @Override
    public int getItemCount() {
        return detailsModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView,id,title,body;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView=itemView.findViewById(R.id.userId);
            id=itemView.findViewById(R.id.id);
            title=itemView.findViewById(R.id.title);
            body=itemView.findViewById(R.id.body);

        }
    }
}
