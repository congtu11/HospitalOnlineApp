package com.example.loginpassport.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.loginpassport.AdapterRecyclerView.NotificationAdapter;
import com.example.loginpassport.Login.TokenManager;
import com.example.loginpassport.R;
import com.example.loginpassport.model.NotificationModel;
import com.example.loginpassport.network.ApiService;
import com.example.loginpassport.network.RetrofitBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView rcv_itemNot;
    List<NotificationModel> data;
    TokenManager tokenManager;



    public NotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
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
        View view= inflater.inflate(R.layout.fragment_notification, container, false);
        rcv_itemNot = view.findViewById(R.id.rcv_itemNot);
        data= new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcv_itemNot.setLayoutManager(layoutManager);
        rcv_itemNot.setHasFixedSize(true);
        rcv_itemNot.setItemAnimator(new DefaultItemAnimator());
        getNotification();
        return view;
    }
    public void getNotification() {
        tokenManager = TokenManager.getInstance(getActivity().getSharedPreferences("prefs",getActivity().MODE_PRIVATE));
        ApiService service = RetrofitBuilder.createServiceAuth(ApiService.class, tokenManager);
        Call<List<NotificationModel>> call = service.getNotification();
        call.enqueue(new Callback<List<NotificationModel>>() {
            @Override
            public void onResponse(Call<List<NotificationModel>> call, Response<List<NotificationModel>> response) {
                if (response.isSuccessful()){
                    List<NotificationModel> list = response.body();
                    for(NotificationModel not:list) {
                        data.add(not);
                    }
                }
                rcv_itemNot.setAdapter(new NotificationAdapter(getActivity(),data));
            }

            @Override
            public void onFailure(Call<List<NotificationModel>> call, Throwable t) {
                Log.d("TAG", "onFailure: Tell me why" +t.getMessage());
            }
        });
    }
}
