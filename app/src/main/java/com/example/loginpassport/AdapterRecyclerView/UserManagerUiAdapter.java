package com.example.loginpassport.AdapterRecyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginpassport.Login.LoginActivity;
import com.example.loginpassport.Login.Logout;
import com.example.loginpassport.Login.TokenManager;
import com.example.loginpassport.MainActiviti.Checkouted;
import com.example.loginpassport.R;
import com.example.loginpassport.entities.AccessToken;
import com.example.loginpassport.model.UserManagerUi_Model;
import com.example.loginpassport.network.ApiService;
import com.example.loginpassport.network.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class UserManagerUiAdapter extends RecyclerView.Adapter <UserManagerUiAdapter.DataViewHolder> {
    private List<UserManagerUi_Model> item;
    private Context context;
    public TokenManager tokenManager;
    ApiService json;
    public static final String TAG="Lỗi";
    public UserManagerUiAdapter(Context context,List<UserManagerUi_Model> item,TokenManager tokenManager) {
        this.context=context;
        this.item=item;
        this.tokenManager=tokenManager;
    }


    @NonNull
    @Override
    public UserManagerUiAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ui_manager, parent, false);
        return new UserManagerUiAdapter.DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserManagerUiAdapter.DataViewHolder holder, int position) {
        holder.tv_title.setText(item.get(position).getTitle());
        Glide.with(context)
                .load(item.get(position).getIcon())
                .into(holder.icon);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = item.get(position).getId_event();
                switch (i) {
                    case 4:
                       getCheckout();
                       break;
                    case 5:
                        logout();
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return item== null ? 0 : item.size();
    }

    public static class DataViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout item_layout;
        private ImageView icon;
        private TextView tv_title;
        private CardView cardView;


        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.img_icon_pro);
            tv_title=itemView.findViewById(R.id.tv_title_pro);
            cardView=itemView.findViewById(R.id.cv_item_pro);

        }
    }
    public void logout() {
        Intent intent = new Intent(context, Logout.class);
        context.startActivity(intent);


    }
    public void getCheckout(){
        Intent intent = new Intent(context, Checkouted.class);
        context.startActivity(intent);
    }


}
