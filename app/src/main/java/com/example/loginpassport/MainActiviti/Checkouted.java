package com.example.loginpassport.MainActiviti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginpassport.AdapterRecyclerView.CartApdapter;
import com.example.loginpassport.AdapterRecyclerView.CheckoutedAdapter;
import com.example.loginpassport.Login.TokenManager;
import com.example.loginpassport.R;
import com.example.loginpassport.model.CartModel;
import com.example.loginpassport.network.ApiService;
import com.example.loginpassport.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Checkouted extends AppCompatActivity {
    private RecyclerView rcv_checkouted;
    List<CartModel> data;
    TokenManager tokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkouted);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));
    ApiService service = RetrofitBuilder.createServiceAuth(ApiService.class, tokenManager);
    rcv_checkouted= (RecyclerView) findViewById(R.id.rcv_checkouted);
    data= new ArrayList<>();
    LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcv_checkouted.setLayoutManager(layoutManager);
        rcv_checkouted.setHasFixedSize(true);
        rcv_checkouted.setItemAnimator(new DefaultItemAnimator());
    getMycart();
}
    public void getMycart() {

        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));
        ApiService service = RetrofitBuilder.createServiceAuth(ApiService.class, tokenManager);
        Call<List<CartModel>> call = service.getMycheckout();
        call.enqueue(new Callback<List<CartModel>>() {
            @Override
            public void onResponse(Call<List<CartModel>> call, Response<List<CartModel>> response) {
                if (response.isSuccessful()) {
                    List<CartModel> list = response.body();
                    for (CartModel c:list) {
                        data.add(c);


                    }

                }

                rcv_checkouted.setAdapter(new CheckoutedAdapter(Checkouted.this,data));
            }

            @Override
            public void onFailure(Call<List<CartModel>> call, Throwable t) {
                Log.d("TA", "onFailure: "+t.getMessage());
            }
        });

    }
}
