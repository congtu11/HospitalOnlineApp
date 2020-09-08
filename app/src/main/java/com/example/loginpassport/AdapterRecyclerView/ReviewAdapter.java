package com.example.loginpassport.AdapterRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginpassport.R;
import com.example.loginpassport.model.Review_model;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private ArrayList<Review_model> review;
    Context context;

    public ReviewAdapter(ArrayList<Review_model> review, Context context) {
        this.review = review;
        this.context = context;
    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_review,parent,false);
        return new ViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder holder, int position) {
         holder.Hinh.setImageResource(review.get(position).getHinh());
         holder.Mota.setText(review.get(position).getMota());
    }

    @Override
    public int getItemCount() {
        return review.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView Hinh;
        TextView Mota;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Hinh = itemView.findViewById(R.id.hinh);
            Mota = itemView.findViewById(R.id.mota);
        }
    }
}
