package com.example.loginpassport.MainActiviti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.loginpassport.AdapterRecyclerView.NewsApdapter;
import com.example.loginpassport.R;
import com.example.loginpassport.model.NewsModel;
import com.example.loginpassport.network.ApiService;
import com.example.loginpassport.network.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadNews extends AppCompatActivity {
    private int id_news;
    ApiService json;
    public static final  String TAG ="";
    TextView title;
    TextView content1;
    TextView content2;
    ImageView img1;
    ImageView img2;
    RetrofitBuilder r = new RetrofitBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_news);
        Intent intent = this.getIntent();
        id_news= intent.getIntExtra("id_news",0);
        title = findViewById(R.id.tv_tt_read);
        content1  = findViewById(R.id.tv_ct1);
        content2 = findViewById(R.id.tv_ct2);
        img1 = findViewById(R.id.img_read1);
        img2 = findViewById(R.id.img_read2);
        getNews();

    }
    public void getNews() {
        json = RetrofitBuilder.createService(ApiService.class);
        Call<List<NewsModel>> call = json.getNewsbyid(id_news);
        call.enqueue(new Callback<List<NewsModel>>() {
            @Override
            public void onResponse(Call<List<NewsModel>> call, Response<List<NewsModel>> response) {
                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: CO respon");
                    List<NewsModel> listdoc=response.body();
                    for (NewsModel d: listdoc) {
                        title.setText(d.getTitle());
                        Glide.with(ReadNews.this).load(r.getURL()+d.getImg1()).into(img1);
                        content1.setText(d.getContent1());
                        content2.setText(d.getContent2());
                        Glide.with(ReadNews.this).load(r.getURL()+d.getImg2()).into(img2);
                    }

                }
            }


            @Override
            public void onFailure(Call<List<NewsModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: Load k duoc tin tuc");
            }
        });
    }
}
