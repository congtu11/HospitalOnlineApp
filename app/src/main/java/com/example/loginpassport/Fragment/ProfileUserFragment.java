package com.example.loginpassport.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.loginpassport.AdapterRecyclerView.UserManagerUiAdapter;
import com.example.loginpassport.Login.TokenManager;
import com.example.loginpassport.MainActiviti.LoadNavi;
import com.example.loginpassport.R;
import com.example.loginpassport.model.UserManagerUi_Model;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileUserFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView rcv_item;
    List<UserManagerUi_Model> itemlist;
    TokenManager tokenManager;

    public ProfileUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileUserFragment newInstance(String param1, String param2) {
        ProfileUserFragment fragment = new ProfileUserFragment();
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
        tokenManager = TokenManager.getInstance(getActivity().getSharedPreferences("prefs",MODE_PRIVATE));
        // Inflate the layout for this fragment
       View view=  inflater.inflate(R.layout.user_manage, container, false);
        rcv_item = view.findViewById(R.id.rcv_item);
        itemlist = new ArrayList<>();
        itemlist.add(new UserManagerUi_Model(R.drawable.logout,"Thông tin cá nhân",1));
        itemlist.add(new UserManagerUi_Model(R.drawable.logout,"Lịch hẹn thành công",2));
        itemlist.add(new UserManagerUi_Model(R.drawable.logout,"Lịch hẹn chờ xác nhận",3));
        itemlist.add(new UserManagerUi_Model(R.drawable.logout,"Đơn hàng đã thanh toán",4));
        itemlist.add(new UserManagerUi_Model(R.drawable.logout,"Logout",5));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcv_item.setLayoutManager(layoutManager);
        rcv_item.setHasFixedSize(true);
        rcv_item.setItemAnimator(new DefaultItemAnimator());
        rcv_item.setAdapter(new UserManagerUiAdapter(getActivity(),itemlist,tokenManager));
        return view;
    }
}
