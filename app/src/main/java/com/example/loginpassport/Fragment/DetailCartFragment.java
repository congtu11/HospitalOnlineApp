package com.example.loginpassport.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.loginpassport.R;
import com.example.loginpassport.model.CartModel;
import com.example.loginpassport.model.DetailCartModel;
import com.example.loginpassport.network.ApiService;
import com.example.loginpassport.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailCartFragment extends DialogFragment {
    private int id;
    ApiService service;
    List<CartModel> data;
    TextView tv_amount;
    ImageView img_plus;
    ImageView img_minus;
    Button btn_ok;
    public static final String TAG =" LOG Ở ĐÂY: ";
    Button btn_cancel_cart;


    public static DetailCartFragment newInstance(int id) {
        DetailCartFragment dialog = new DetailCartFragment();
        Bundle args = new Bundle();
        args.putInt("id_cart",id);
        dialog.setArguments(args);
        return dialog;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       return inflater.inflate(R.layout.dialog_amout_cart_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        id= getArguments().getInt("id_cart");
        tv_amount = view.findViewById(R.id.tv_amount_carts);
        Log.d(TAG, "LOG ID CART " + id);
        img_plus= view.findViewById(R.id.img_plus_cart);
        img_minus= view.findViewById(R.id.img_minus_cart);
        service = RetrofitBuilder.createService(ApiService.class);
        btn_ok = view.findViewById(R.id.btn_ok_cart);
        Call<List<DetailCartModel>> call = service.getAmount(id);
      call.enqueue(new Callback<List<DetailCartModel>>() {
          @Override
          public void onResponse(Call<List<DetailCartModel>> call, Response<List<DetailCartModel>> response) {

              List<DetailCartModel> list = response.body();
              for (DetailCartModel c:list) {
                  Log.d(TAG, "LOG AMOUNT : " +c.getAmount());
                  tv_amount.setText(String.valueOf(c.getAmount()));
              }

          }

          @Override
          public void onFailure(Call<List<DetailCartModel>> call, Throwable t) {

          }
      });
      btn_ok.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
    Ok();
          }
      });


    img_plus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            plusAmount();
        }
    });
    img_minus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            minusAmount();
        }
    });
    }
    public void plusAmount() {
        int amount= Integer.parseInt(tv_amount.getText().toString());
        amount++;
        tv_amount.setText(String.valueOf(amount));

    }
    public void minusAmount() {
        int amount = Integer.parseInt(tv_amount.getText().toString());
        amount--;
        tv_amount.setText(String.valueOf(amount));
    }
    public void Ok() {
        int amount = Integer.parseInt(tv_amount.getText().toString());
        Call<String> call = service.updateAmountcart(id,amount);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Cập nhật thành công ", Toast.LENGTH_SHORT).show();
                        getDialog().dismiss();
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
}
