package com.example.loginpassport.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.loginpassport.R;
import com.example.loginpassport.entities.AccessToken;
import com.example.loginpassport.network.ApiService;
import com.example.loginpassport.network.RetrofitBuilder;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    ApiService json;
   EditText tilName;
    EditText tilemail;
    TextView tv_gotologin;
    EditText tilpassword;
    ApiService service;
    Call<AccessToken> call;
    private AccessToken accessToken;
    AwesomeValidation validator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        json = RetrofitBuilder.createService(ApiService.class);
        tilName =  findViewById(R.id.tv_name);
        tilemail =   findViewById(R.id.tv_email);
        tilpassword=  findViewById(R.id.tv_password);
        tv_gotologin=findViewById(R.id.tv_gotologin);
        tv_gotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        Button button = findViewById(R.id.btn_register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        validator = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        setupRules();
    }

        void register () {


            String name = tilName.getText().toString();
            String email = tilemail.getText().toString();
            String password = tilpassword.getText().toString();
            tilName.setError(null);
            tilemail.setError(null);
            tilpassword.setError(null);
            validator.clear();
            if(validator.validate()) {
                Call<AccessToken> call = json.register(name, email, password, password);
                Log.w("n", "Chay vao toi day roi ");
                call.enqueue(new Callback<AccessToken>() {
                    @Override
                    public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                        if (response.isSuccessful()) {
                            Log.w("aa", "onResponse: " + response);
                            Toast.makeText(RegisterActivity.this,"Thanh cong ",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);

                        } else {
                            try {

                                Toast.makeText(RegisterActivity.this,  response.errorBody().toString(),Toast.LENGTH_LONG).show();
                            }
                            catch (Exception e) {
                                Log.d("ss", "dm lại lỗi ");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AccessToken> call, Throwable t) {
                        Log.w("a", t.toString());
                    }
                });
            }

    }


    public void setupRules(){

        validator.addValidation(this, R.id.til_name, RegexTemplate.NOT_EMPTY, R.string.err_name);
        validator.addValidation(this, R.id.til_email, Patterns.EMAIL_ADDRESS, R.string.err_email);
        validator.addValidation(this, R.id.til_password, "[a-zA-Z0-9]{6,}", R.string.err_password);
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (call!=null) call.cancel();
        call = null;
    }
    public void updateInfoUser() {

    }
}
