package com.example.loginpassport.AdapterRecyclerView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginpassport.MainActiviti.DoctorList;
import com.example.loginpassport.MainActiviti.DoctorProfile;
import com.example.loginpassport.R;
import com.example.loginpassport.model.Doctor_Model;
import com.example.loginpassport.network.RetrofitBuilder;


import java.util.List;

public class DoctorListAdapter  extends RecyclerView.Adapter<DoctorListAdapter.DataViewHolder> {
    private List<Doctor_Model> doctor;
    private Context context;
    private CardView cardView;
    RetrofitBuilder r = new RetrofitBuilder();
    public DoctorListAdapter(Context context,List<Doctor_Model> doctor) {
        this.context=context;
        this.doctor=doctor;
    }


    @NonNull
    @Override
    public DoctorListAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor_list, parent, false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {

        Glide.with(context).load(r.getURL()+doctor.get(position).getAvt_dt()).into(holder.imageView_doctor);
         holder.name_doctor.setText(doctor.get(position).getName());
         holder.category_doctor.setText(doctor.get(position).getCategory());
         holder.description_doctor.setText(doctor.get(position).getDescription());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = String.valueOf(doctor.get(position).getId());
                        Intent intent = new Intent(context, DoctorProfile.class);
                     intent.putExtra("id_doctor",id);
                        context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return doctor== null ? 0 : doctor.size();
    }
    public static class DataViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView_doctor;
        private TextView name_doctor;
        private TextView category_doctor;
        private TextView description_doctor;
        private RatingBar voting_doctor;
        private CardView cardView;

        public DataViewHolder(View itemView) {
            super(itemView);
            imageView_doctor = itemView.findViewById(R.id.imageView_doctor);
            name_doctor = itemView.findViewById(R.id.name_doctor);
            category_doctor= itemView.findViewById(R.id.category_doctor);
            description_doctor= itemView.findViewById(R.id.description_doctor);

            cardView= itemView.findViewById(R.id.cardview_dt);


        }
    }
}
