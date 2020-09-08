package com.example.loginpassport.MainActiviti;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.loginpassport.AdapterRecyclerView.MedicineShopAdapter;
import com.example.loginpassport.Login.TokenManager;
import com.example.loginpassport.R;
import com.example.loginpassport.model.MedicineModel;
import com.example.loginpassport.network.ApiService;
import com.example.loginpassport.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicineShop extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public static final String TAG ="LOG";
    Spinner spinner;
    private RecyclerView rcv_medicine;
    ApiService service = RetrofitBuilder.createService(ApiService.class);
    Context context;
    TokenManager tokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_shop);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));
       spinner = findViewById(R.id.spinner);
       rcv_medicine= findViewById(R.id.rcv_medicine);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rcv_medicine.setLayoutManager(layoutManager);
        rcv_medicine.setHasFixedSize(true);
        rcv_medicine.setItemAnimator(new DefaultItemAnimator());
        spinner.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position==0) {
            getAllMedicine();
        }
        else if (position!=0) {
            getMedicinebyCategory(position+1);
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        getAllMedicine();
    }
    public void getAllMedicine()
    {
        Call<List<MedicineModel>> call = service.getAllMedicine();
        call.enqueue(new Callback<List<MedicineModel>>() {
            @Override
            public void onResponse(Call<List<MedicineModel>> call, Response<List<MedicineModel>> response) {
                List<MedicineModel> medicine = new ArrayList<>();
                if (response.isSuccessful()) {
                    List<MedicineModel> list = response.body();
                    for (MedicineModel m:list) {
                        medicine.add(m);

                    }
                }
                rcv_medicine.setAdapter(new MedicineShopAdapter(medicine,MedicineShop.this,tokenManager));
            }

            @Override
            public void onFailure(Call<List<MedicineModel>> call, Throwable t) {

            }
        });

    }
    public void getMedicinebyCategory(int id_category) {
        Log.d(TAG, "getMedicinebyCategory: "+id_category);
        Call<List<MedicineModel>> call = service.getMedicinebyCategory(id_category);
        call.enqueue(new Callback<List<MedicineModel>>() {
            @Override
            public void onResponse(Call<List<MedicineModel>> call, Response<List<MedicineModel>> response) {
                List<MedicineModel>  data = new ArrayList<>();
                if (response.isSuccessful()) {

                    List<MedicineModel> list = response.body();
                    for (MedicineModel m:list) {
                        data.add(m);

                    }
                }
                rcv_medicine.setAdapter(new MedicineShopAdapter(data,MedicineShop.this,tokenManager));
            }

            @Override
            public void onFailure(Call<List<MedicineModel>> call, Throwable t) {
                Log.d("A", "onFailure: "+t.getMessage());
            }
        });
    }
}
