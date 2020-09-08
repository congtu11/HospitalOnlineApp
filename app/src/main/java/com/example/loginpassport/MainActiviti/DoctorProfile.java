package com.example.loginpassport.MainActiviti;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.loginpassport.R;
import com.example.loginpassport.model.Doctor_Model;
import com.example.loginpassport.network.ApiService;
import com.example.loginpassport.network.RetrofitBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorProfile extends AppCompatActivity {
    public static final String TAG = "LOG";
    ApiService json;
    List<Doctor_Model> doctor;
    public Context context;
    private int id_doctor;
    private TextView tv_name;
    private TextView tv_category;
    private TextView tv_voting;
    private TextView tv_phone;
    private TextView tv_email;
    private TextView tv_address;
    private Button bookticket;
    private Button bookvideocall;
    private TextView tv_aboutme;
    private ImageView img_dt_profile;
    RetrofitBuilder r = new RetrofitBuilder();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_dt);
        findXml();
        json =RetrofitBuilder.createService(ApiService.class);
    Intent intent = this.getIntent();
      String id= intent.getStringExtra("id_doctor");
        id_doctor = Integer.parseInt(id);
        bookticket = findViewById(R.id.btn_bookticket);
        bookvideocall = findViewById(R.id.btn_bookvideo);
        bookvideocall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DoctorProfile.this,BookVideoCall.class);
                intent.putExtra("id_doctor",id_doctor);
                startActivity(intent);
            }
        });
        bookticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookTickets();
            }
        });
       getDoctor();


    }
    public void getDoctor() {
        Call<List<Doctor_Model>> call = json.getDoctorbyId(id_doctor);
        call.enqueue(new Callback<List<Doctor_Model>>() {
            @Override
            public void onResponse(Call<List<Doctor_Model>> call, Response<List<Doctor_Model>> response) {
                List<Doctor_Model> listdoc=response.body();
                Log.d(TAG, "onResponse: Lấy được response");
                for (Doctor_Model doctor:listdoc) {
                    tv_name.setText(doctor.getName());
                    tv_address.setText(doctor.getAddress());
                    tv_email.setText(doctor.getEmail());
                    tv_category.setText(doctor.getCategory());
                    tv_aboutme.setText(doctor.getDescription());
                    Glide.with(DoctorProfile.this).load(r.getURL()+doctor.getAvt_dt()).into(img_dt_profile);

                }

            }

            @Override
            public void onFailure(Call<List<Doctor_Model>> call, Throwable t) {
                Log.d(TAG, "onFailure: Lỗi gì rồi");
            }
        });
    }
    public void findXml() {
        tv_name= findViewById(R.id.tv_name);
        tv_category= findViewById(R.id.tv_category);
        tv_address=findViewById(R.id.tv_address);
        tv_email=findViewById(R.id.tv_email);
        tv_phone=findViewById(R.id.edt_phoneb);
        tv_voting= findViewById(R.id.tv_voting);
        tv_aboutme=findViewById(R.id.tv_aboutme);
        img_dt_profile=findViewById(R.id.img_dt_profile);



    }
    public void BookTickets(){
        Intent intent = new Intent(DoctorProfile.this,BookTiket.class);
        intent.putExtra("id_doctor",id_doctor);
        startActivity(intent);
    }
}
