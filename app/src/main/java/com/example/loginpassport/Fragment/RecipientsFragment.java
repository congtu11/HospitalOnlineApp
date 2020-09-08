package com.example.loginpassport.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.loginpassport.Login.TokenManager;
import com.example.loginpassport.R;
import com.example.loginpassport.network.ApiService;
import com.example.loginpassport.network.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class RecipientsFragment extends DialogFragment {
    EditText edt_address;
    EditText edt_phone;
    Button btn_done;
    TokenManager tokenManager;
    ApiService service;
    public static final String TAG =" LOG Ở ĐÂY: ";
    public static RecipientsFragment  newInstance() {
        RecipientsFragment dialog = new RecipientsFragment();
        Bundle args = new Bundle();
        dialog.setArguments(args);
        return dialog;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recipients_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edt_address = view.findViewById(R.id.edt_address_rec);
        edt_phone = view.findViewById(R.id.edt_phone_rec);
        btn_done = view.findViewById(R.id.btn_done_cart);
        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Done();
            }
        });
    }
    private void Done() {
        String add = edt_address.getText().toString();
        String phone = edt_phone.getText().toString();
        tokenManager = TokenManager.getInstance(getActivity().getSharedPreferences("prefs",MODE_PRIVATE));
        service = RetrofitBuilder.createServiceAuth(ApiService.class, tokenManager);
        Call<String> call = service.updateRecipients(add,phone);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getActivity(), "Thành công", Toast.LENGTH_SHORT).show();
                    getDialog().dismiss();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }
}
