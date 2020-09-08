package com.example.loginpassport.AdapterRecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginpassport.MainActiviti.DoctorList;
import com.example.loginpassport.R;
import com.example.loginpassport.model.CategoryDoctorList_Model;


import java.util.List;

public class CategoryDoctorListAdapter extends RecyclerView.Adapter<CategoryDoctorListAdapter.DataViewHolder>{
    private List<CategoryDoctorList_Model> item;
    private Context context;
    public CategoryDoctorListAdapter(Context context,List<CategoryDoctorList_Model> item) {
        this.context=context;
        this.item=item;
    }


    @NonNull
    @Override
    public CategoryDoctorListAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mainmenu, parent, false);
        return new CategoryDoctorListAdapter.DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryDoctorListAdapter.DataViewHolder holder, final int position) {
        holder.title_item.setText(item.get(position).getTitle().toString());
        Glide.with(context)
                .load(item.get(position).getImage_item())
                .into(holder.imageView_item);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = item.get(position).getNumberEvent();
                switch (i) {
                    case 1:
                        Intent intent = new Intent(context, DoctorList.class);
                        intent.putExtra("numberEvent",i);
                        context.startActivity(intent);

                }
            }
        });



    }


    @Override
    public int getItemCount() {
        return item== null ? 0 : item.size();
    }
    public static class DataViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView_item;
        private TextView title_item;
        private CardView cardView;


        public DataViewHolder(View itemView) {
            super(itemView);
            imageView_item = itemView.findViewById(R.id.image_item_mainmenu);
            title_item = itemView.findViewById(R.id.title_item_mainmenu);
            cardView= itemView.findViewById(R.id.cardView);



        }
    }
}
