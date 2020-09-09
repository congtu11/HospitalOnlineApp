package com.example.loginpassport.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginpassport.MainActiviti.LoadNavi;
import com.example.loginpassport.R;
import com.example.loginpassport.network.ApiService;
import com.example.loginpassport.network.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity {
    TokenManager tokenManager;
    ApiService service;
    Boolean checkLogin;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));
        Log.d("TAG", "doInBackground:Chay vao splas ");
        // Hàm này chính là hàm sẽ được gọi khi thực hiện tiến trình nền và tại đây bạn sẽ viết các công việc mình cần thực hiện tại đây
        //tuyệt đối không được cập nhật giao diện trong hàm này
        if(tokenManager ==null) {
            Intent intent = new Intent(SplashScreen.this,LoginActivity.class);
            startActivity(intent);
        }
        if(tokenManager.getToken().getAccessToken()==null) {
            Intent intent = new Intent(SplashScreen.this,LoginActivity.class);
            startActivity(intent);

        }
        else {
            Log.d("TAG", "doInBackground: Check serve ");
            service = RetrofitBuilder.createServiceAuth(ApiService.class,tokenManager);
            Call<String> call = service.checkLogins();
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        checkLogin=false;
                        Log.d("TAG", "onResponse: Check thanh cong dang nhap");
                    }
                    else if (response.code()==400) {
                        checkLogin =true;
                        Log.d("TAG", "onResponse: Check thaat bai dang nhap");
                    }
                    else {
                        Log.d("TAG", "onResponse: Loi khi dang nhap");
                    }
                    if (checkLogin==true) {
                        Intent intent = new Intent(SplashScreen.this,LoginActivity.class);
                        startActivity(intent);
                    }
                    else if(checkLogin==false) {

                        Intent intent = new Intent(SplashScreen.this, LoadNavi.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("TAG", "onFailure: Loi dang nhap that bai");
                    Intent intent = new Intent(SplashScreen.this,LoginActivity.class);
                    startActivity(intent);
                }
            });


        }


    }

}
