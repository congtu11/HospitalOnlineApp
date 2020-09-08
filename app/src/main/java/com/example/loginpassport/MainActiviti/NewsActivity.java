package com.example.loginpassport.MainActiviti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.loginpassport.AdapterRecyclerView.DoctorListAdapter;
import com.example.loginpassport.AdapterRecyclerView.NewsApdapter;
import com.example.loginpassport.R;
import com.example.loginpassport.model.Doctor_Model;
import com.example.loginpassport.model.NewsModel;
import com.example.loginpassport.network.ApiService;
import com.example.loginpassport.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {
    public static final String TAG = "LOG";
     RecyclerView recyclerView;
    List<NewsModel> news;
    public Context context;
    ApiService json;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        json = RetrofitBuilder.createService(ApiService.class);
        recyclerView = findViewById(R.id.rcv_news);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        news = new ArrayList<>();
        getAllnews();

    }
    public void getAllnews() {
        Log.d(TAG, "getAllnews: Tơi getall ");
        Call<List<NewsModel>> call = json.getNews();
        call.enqueue(new Callback<List<NewsModel>>() {
            @Override
            public void onResponse(Call<List<NewsModel>> call, Response<List<NewsModel>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: CO respon");
                    List<NewsModel> listdoc=response.body();
                    for (NewsModel d: listdoc) {
                        news.add(d);
                    }
                    recyclerView.setAdapter(new NewsApdapter(news,NewsActivity.this));
                }
              
                Log.d(TAG, "onResponse: sau setadap");
            }


            @Override
            public void onFailure(Call<List<NewsModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: Load k duoc tin tuc");
            }
        });
    }
}
