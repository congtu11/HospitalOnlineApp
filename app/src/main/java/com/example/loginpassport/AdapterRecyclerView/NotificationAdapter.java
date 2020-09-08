package com.example.loginpassport.AdapterRecyclerView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginpassport.R;
import com.example.loginpassport.model.NotificationModel;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.DataViewHolder> {
    private  Context context;
    private List<NotificationModel> data;
    public NotificationAdapter(Context context, List<NotificationModel> data){
        this.context=context;
        this.data=data;
    }
    @NonNull
    @Override
    public NotificationAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new NotificationAdapter.DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.DataViewHolder holder, int position) {
        if (data.get(position).getCheck_code()==0) {
            holder.content.setText(data.get(position).getNotification_1());
            holder.tv_time.setText(data.get(position).getCreated_at());
        }
        else if (data.get(position).getCheck_code()==1) {
            holder.content.setText(data.get(position).getNotification_2());
            holder.tv_time.setText(data.get(position).getCreated_at());
        }
        Log.d("TG", "onBindViewHolder: "+data.size());
    }

    @Override
    public int getItemCount() {
        return data== null ? 0 : data.size();
    }
    public static class DataViewHolder extends RecyclerView.ViewHolder {

        private ImageView icon;
        private TextView content;
        private TextView tv_time;
        private CardView cardView;


        public DataViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.img_iconNot);
            content = itemView.findViewById(R.id.tv_content);
            tv_time = itemView.findViewById(R.id.tv_timeNot);
            cardView= itemView.findViewById(R.id.cv_not);

        }
    }
    public void show() {
        Log.d("TAG", "show: "+ data.toString() );

    }
}
