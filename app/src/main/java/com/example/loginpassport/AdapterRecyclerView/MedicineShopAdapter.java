package com.example.loginpassport.AdapterRecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.loginpassport.Login.TokenManager;
import com.example.loginpassport.R;
import com.example.loginpassport.model.MedicineModel;
import com.example.loginpassport.network.ApiService;
import com.example.loginpassport.network.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class MedicineShopAdapter extends RecyclerView.Adapter<MedicineShopAdapter.ViewHolder>{
    List<MedicineModel> medicine;
    Context context;
    TokenManager tokenManager;
    RetrofitBuilder r = new RetrofitBuilder();
    public MedicineShopAdapter(List<MedicineModel> medicine, Context context,TokenManager tokenManager) {
        this.medicine = medicine;
        this.context = context;
        this.tokenManager=tokenManager;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_medicine_shop,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(r.getURL()+medicine.get(position).getAvatar()).into(holder.img_medicine);
        holder.tv_name_medicine.setText(medicine.get(position).getName_medicine());
        holder.tv_type_medicine.setText(medicine.get(position).getName_category());
        holder.tv_review_medicine.setText(medicine.get(position).getQickreview());
        holder.btn_addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addtocart( medicine.get(position).getId(),1);

            }
        });
        holder.cv_medicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return medicine== null ? 0 : medicine.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_medicine;
        TextView tv_name_medicine, tv_type_medicine,tv_review_medicine;
        CardView cv_medicine;
        Button btn_addtocart;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_medicine = (ImageView) itemView.findViewById(R.id.img_medicine);
            tv_name_medicine = (TextView) itemView.findViewById(R.id.tv_name_medicine);
            tv_type_medicine = (TextView) itemView.findViewById(R.id.tv_type_medicine);
            tv_review_medicine = (TextView) itemView.findViewById(R.id.tv_review_medicine);
            cv_medicine= itemView.findViewById(R.id.cv_medicine);
            btn_addtocart= itemView.findViewById(R.id.btn_addtocart);
        }
    }
    public void addtocart(int id_medicine,int amount ) {
        Log.d("TAG", "addtocart: Chay cai nay rrrrrr");
        ApiService service = RetrofitBuilder.createServiceAuth(ApiService.class,tokenManager);
        Call<String> call = service.addtocart(id_medicine,amount);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    String t = response.body();
                    Toast.makeText(context, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                    Log.d("TAG", "onResponse: Thêm vào giỏ hàng thành công ");
                }
                else {
                    Log.d("A", "onResponse: Loi gi do "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

}
