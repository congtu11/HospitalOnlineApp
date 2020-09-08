package com.example.loginpassport.MainActiviti;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.loginpassport.AdapterRecyclerView.CategoryDoctorListAdapter;
import com.example.loginpassport.AdapterRecyclerView.DoctorListAdapter;
import com.example.loginpassport.AdapterRecyclerView.ReviewAdapter;
import com.example.loginpassport.Login.RegisterActivity;
import com.example.loginpassport.R;
import com.example.loginpassport.model.CategoryDoctorList_Model;
import com.example.loginpassport.model.Doctor_Model;
import com.example.loginpassport.network.ApiService;
import com.example.loginpassport.network.RetrofitBuilder;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DoctorList extends AppCompatActivity {
    public static final String TAG = "LOG";
    private RecyclerView recyclerView;
    List<Doctor_Model> doctor;
    public Context context;
    ApiService json;
    int eventNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_list);
        Intent intent = getIntent();
        eventNumber = intent.getIntExtra("numberEvent",0);
        json = RetrofitBuilder.createService(ApiService.class);
        recyclerView = findViewById(R.id.recyclerview_doctorlist);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        doctor = new ArrayList<>();
     getAlldoctor();
        Log.d(TAG, "onCreate: Chay sau cung");

    }
    public void getAlldoctor() {

        Call<List<Doctor_Model>> call = json.getDoctorbyCategory(eventNumber);
        call.enqueue(new Callback<List<Doctor_Model>>() {
            @Override
            public void onResponse(Call<List<Doctor_Model>> call, Response<List<Doctor_Model>> response) {
                if (response.isSuccessful()) {
                    List<Doctor_Model> listdoc=response.body();
                        for (Doctor_Model d: listdoc) {
                            doctor.add(d);
                        }
                }
                recyclerView.setAdapter(new DoctorListAdapter(DoctorList.this,doctor));
            }


            @Override
            public void onFailure(Call<List<Doctor_Model>> call, Throwable t) {

            }
        });
    }

}
