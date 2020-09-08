package com.example.loginpassport.AdapterRecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.loginpassport.R;
import com.example.loginpassport.model.CartModel;
import com.example.loginpassport.network.RetrofitBuilder;

import java.util.List;

public class CheckoutedAdapter extends RecyclerView.Adapter<CheckoutedAdapter .DataViewHolder> {
    List<CartModel> data;
    Context context;
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
        }
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

        public DataViewHolder(View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar_cart);
            tv_name_medicine = itemView.findViewById(R.id.tv_name_cart);
            tv_amount= itemView.findViewById(R.id.tv_amount_cart);
            tv_price= itemView.findViewById(R.id.tv_price_cart);
            cv_item = itemView.findViewById(R.id.cv_cart_item);
        }
    }
}
