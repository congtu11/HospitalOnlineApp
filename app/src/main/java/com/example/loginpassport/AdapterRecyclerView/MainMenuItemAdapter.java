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
import com.example.loginpassport.Login.TokenManager;
import com.example.loginpassport.MainActiviti.CategoryDoctorList;
import com.example.loginpassport.MainActiviti.MedicineShop;
import com.example.loginpassport.MainActiviti.NewsActivity;
import com.example.loginpassport.R;
import com.example.loginpassport.activites.ChannelSelectionActivity;
import com.example.loginpassport.model.ItemMainMenu_Model;
import com.example.loginpassport.model.User;
import com.example.loginpassport.model.UserModel;
import com.example.loginpassport.network.ApiService;
import com.example.loginpassport.network.RetrofitBuilder;
import com.example.loginpassport.utils.MessageUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainMenuItemAdapter extends RecyclerView.Adapter<MainMenuItemAdapter.DataViewHolder>{
    private List<ItemMainMenu_Model> item;
    private Context context;
    private CardView cardView;
    TokenManager tokenManager;
    public MainMenuItemAdapter(Context context,List<ItemMainMenu_Model> item) {
        this.context=context;
        this.item=item;
    }


    @NonNull
    @Override
    public MainMenuItemAdapter.DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mainmenu, parent, false);
        return new MainMenuItemAdapter.DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MainMenuItemAdapter.DataViewHolder holder, final int position) {
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
                      Intent intent = new Intent(context, CategoryDoctorList.class);
                      context.startActivity(intent);
                      break;
                  case 2:
                        toCall();
                        break;
                  case 3:
                      Intent intent1 = new Intent(context, MedicineShop.class);
                      context.startActivity(intent1);
                  case 4:
                      Intent intent2 = new Intent(context, NewsActivity.class);
                      context.startActivity(intent2);

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
    public void toCall() {
        tokenManager = TokenManager.getInstance(context.getSharedPreferences("prefs",context.MODE_PRIVATE));
        ApiService service = RetrofitBuilder.createServiceAuth(ApiService.class, tokenManager);
        Call<List<UserModel>> call = service.getInfoUser();
        call.enqueue(new Callback<List<UserModel>>() {
            @Override
            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                int id=0;
                String name="";
                if (response.isSuccessful()) {
                    List<UserModel> list = response.body();
                    for (UserModel data : list) {
                        id=data.getId();
                        name=data.getName();
                    }
                }
            String ids = String.valueOf(id);
               handleSignInResult(ids,name);

            }

            @Override
            public void onFailure(Call<List<UserModel>> call, Throwable t) {

            }
        });

    }
    private void handleSignInResult(String id,String name) {
        final User user = new User(id);
        user.setFireDisplayName(name);
        Intent intent = new Intent(context, ChannelSelectionActivity.class);
        intent.putExtra(MessageUtil.INTENT_EXTRA_USER_ID, user);
        context.startActivity(intent);
    }

}
