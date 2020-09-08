package com.example.loginpassport.AdapterRecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginpassport.R;
import com.example.loginpassport.model.NewsModel;
import com.example.loginpassport.network.RetrofitBuilder;

import java.util.List;

import static com.example.loginpassport.MainActiviti.MedicineShop.TAG;

public class NewsApdapter extends RecyclerView.Adapter<NewsApdapter.ViewHolder> {
    List<NewsModel> data;
    Context context;
    RetrofitBuilder r = new RetrofitBuilder();
    public NewsApdapter(List<NewsModel> data,Context context) {
        this.context=context;
        this.data=data;

    }

    @NonNull
    @Override
    public NewsApdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.item_news,parent,false);
        return new NewsApdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsApdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Vao day k ");
        holder.title.setText(data.get(position).getTitle());
        Glide.with(context).load(r.getURL()+data.get(position).getImg1()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return data== null ? 0 : data.size();
    }
    public  class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView img;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_title_news);
            img = itemView.findViewById(R.id.img_news);
            cardView = itemView.findViewById(R.id.cv_item_news);

        }
    }

}
