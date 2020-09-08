package com.example.loginpassport.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.example.loginpassport.MainActiviti.LoadNavi;
import com.example.loginpassport.R;
import com.example.loginpassport.entities.AccessToken;
import com.example.loginpassport.entities.ApiError;
import com.example.loginpassport.network.ApiService;
import com.example.loginpassport.network.RetrofitBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText tilEmail;
    EditText tilPassword;
    Button btn_login;
    ApiService service;
    TokenManager tokenManager;
    AwesomeValidation validator;
    Call<AccessToken> call;
    Button btn_registter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findView();
        service = RetrofitBuilder.createService(ApiService.class);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));
        validator = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        btn_registter= findViewById(R.id.btn_register);
        btn_registter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    btn_login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Login();
        }
    });

    }
    public void findView()
    {
        tilEmail = findViewById(R.id.til_email_login);
        tilPassword= findViewById(R.id.til_password_login);
        btn_login = findViewById(R.id.btn_login);
        btn_registter= findViewById(R.id.btn_register);

    }
    public void Login() {
        String email = tilEmail.getText().toString();
        String password = tilPassword.getText().toString();
        tilEmail.setError(null);
        tilPassword.setError(null);
        validator.clear();
        call = service.login(email,password);
        call.enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
            if (response.isSuccessful()) {
                Log.w("B","Ngon rồi "+response);
                tokenManager.saveToken(response.body());
                Log.w("B",tokenManager.getToken().getAccessToken());
                Intent intent = new Intent(LoginActivity.this, LoadNavi.class);
                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(LoginActivity.this, "Kiem tra lai tai khoan hoac mat khau", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {
                Log.w("a", "LỖi con mẹ nó rồi ");
            }
        });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call!=null) {
            call.cancel();
            call=null;
        }
    }

    @Override
    public void onBackPressed() {


    }
}
