package com.example.loginpassport.AdapterRecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.loginpassport.MainActiviti.ReadNews;
import com.example.loginpassport.R;
import com.example.loginpassport.model.CartModel;
import com.example.loginpassport.model.NewsModel;
import com.example.loginpassport.network.ApiService;
import com.example.loginpassport.network.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CheckoutedAdapter extends RecyclerView.Adapter<CheckoutedAdapter .DataViewHolder> {
    List<CartModel> data;
    Context context;
    ApiService json;
    RetrofitBuilder r = new RetrofitBuilder();
    public CheckoutedAdapter (Context context, List<CartModel> data) {
        this.context=context;
        this.data=data;
    }
    @NonNull
    @Override
    public CheckoutedAdapter .DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medicine_cart, parent, false);
        return new CheckoutedAdapter .DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        if (data.get(position).getCheckout_code()==1) {
            holder.tv_name_medicine.setText(data.get(position).getName_medicine());
            holder.tv_price.setText(data.get(position).getPrice()+"");
            holder.tv_amount.setText(data.get(position).getAmount()+"");
            Glide.with(context).load(r.getURL()+data.get(position).getAvatar()).into(holder.avatar);
            holder.img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DeleteItem(data.get(position).getId());
                }
            });
        }
    }
    public void DeleteItem(int id) {
        json = RetrofitBuilder.createService(ApiService.class);
        Call<String> call = json.deleteItem(id);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(context, "Xóa item thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }



    @Override
    public int getItemCount() {
        return data== null ? 0 : data.size();
    }
    public static class DataViewHolder extends RecyclerView.ViewHolder {

        private ImageView avatar;
        private TextView tv_amount;
        private TextView tv_name_medicine;
        private TextView tv_price;
        private CardView cv_item;
        private ImageView img_delete;
        public DataViewHolder(View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar_cart);
            tv_name_medicine = itemView.findViewById(R.id.tv_name_cart);
            tv_amount= itemView.findViewById(R.id.tv_amount_cart);
            tv_price= itemView.findViewById(R.id.tv_price_cart);
            cv_item = itemView.findViewById(R.id.cv_cart_item);
            img_delete=itemView.findViewById(R.id.img_delete_item_cart);
        }
    }
}
