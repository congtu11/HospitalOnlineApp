package com.example.loginpassport.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginpassport.AdapterRecyclerView.CartApdapter;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView rcv_cart;
    List<CartModel> data;
    TokenManager tokenManager;
    private TextView totalmoney;
    Button btn_checkout;
    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        tokenManager = TokenManager.getInstance(getActivity().getSharedPreferences("prefs",getActivity().MODE_PRIVATE));
        ApiService service = RetrofitBuilder.createServiceAuth(ApiService.class, tokenManager);
        rcv_cart= (RecyclerView) view.findViewById(R.id.rcv_cart);
        totalmoney = view.findViewById(R.id.totalmoney);
        data= new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcv_cart.setLayoutManager(layoutManager);
        rcv_cart.setHasFixedSize(true);
        rcv_cart.setItemAnimator(new DefaultItemAnimator());
        btn_checkout =view.findViewById(R.id.btn_checkout);
        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<String> call = service.checkoutcart();
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getActivity(), "Bạn đã thanh toán thành công", Toast.LENGTH_SHORT).show();
                            FragmentManager fm =  getActivity().getSupportFragmentManager();
                           RecipientsFragment post = new RecipientsFragment();
                            post.show(fm,null);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });

            }
        });
        getMycart();
        return view;
    }
    public void getMycart() {

        tokenManager = TokenManager.getInstance(getActivity().getSharedPreferences("prefs",getActivity().MODE_PRIVATE));
        ApiService service = RetrofitBuilder.createServiceAuth(ApiService.class, tokenManager);
        Call<List<CartModel>> call = service.getMycart();
        call.enqueue(new Callback<List<CartModel>>() {
            @Override
            public void onResponse(Call<List<CartModel>> call, Response<List<CartModel>> response) {
                if (response.isSuccessful()) {
                    int total=0;
                    List<CartModel> list = response.body();
                    for (CartModel c:list) {
                        data.add(c);

                        total=total+(c.getAmount()*c.getPrice());

                    }

                    totalmoney.setText(String.valueOf(total));
                }

                rcv_cart.setAdapter(new CartApdapter(getActivity(),data));
            }

            @Override
            public void onFailure(Call<List<CartModel>> call, Throwable t) {
                Log.d("TA", "onFailure: "+t.getMessage());
            }
        });

    }
}
