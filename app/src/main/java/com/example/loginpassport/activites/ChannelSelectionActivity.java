package com.example.loginpassport.activites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginpassport.AdapterRecyclerView.NotificationCodeCallAdapter;
import com.example.loginpassport.Login.TokenManager;
import com.example.loginpassport.R;
import com.example.loginpassport.model.BookCallModel;
import com.example.loginpassport.model.User;
import com.example.loginpassport.network.ApiService;
import com.example.loginpassport.network.RetrofitBuilder;
import com.example.loginpassport.utils.Constant;
import com.example.loginpassport.utils.MessageUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChannelSelectionActivity extends AppCompatActivity {

    private User user;
    private TextView mTitleTextView;
    private TextView mChatButton, mCallButton;
    private EditText mNameEditText;
    private String mTargetName;
    private boolean mIsPeerToPeerMode = true;
    private TokenManager tokenManager;
    private RecyclerView rcv_notCall;
    ApiService service;
    public static final String TAG = "TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_selection);

        initUIAndData();
    }

    private void initUIAndData() {
        Intent intent = getIntent();
        user = intent.getParcelableExtra(MessageUtil.INTENT_EXTRA_USER_ID);
        mTitleTextView = findViewById(R.id.selection_title);
        mNameEditText = findViewById(R.id.selection_name);
        mCallButton = findViewById(R.id.selection_call_btn);
        mIsPeerToPeerMode = false;
        LoadData();
    }

    public void onClickFinish(View view) {
        finish();
    }

    public void onClickCall(View view) {
        String myName = user.getFireDisplayName();
        mTargetName = mNameEditText.getText().toString();
        if (mTargetName.equals("")) {
            showToast(getString(mIsPeerToPeerMode ? R.string.account_empty : R.string.channel_name_empty));
        } else if (mTargetName.length() >= MessageUtil.MAX_INPUT_NAME_LENGTH) {
            showToast(getString(mIsPeerToPeerMode ? R.string.account_too_long : R.string.channel_name_too_long));
        } else if (mTargetName.startsWith(" ")) {
            showToast(getString(mIsPeerToPeerMode ? R.string.account_starts_with_space : R.string.channel_name_starts_with_space));
        } else if (mTargetName.equals("null")) {
            showToast(getString(mIsPeerToPeerMode ? R.string.account_literal_null : R.string.channel_name_literal_null));
        } else if (mIsPeerToPeerMode && mTargetName.equals(user.getFireDisplayName())) {
            showToast(getString(R.string.account_cannot_be_yourself));
        } else {
            String channelName = "";
            if (mIsPeerToPeerMode) {
                channelName = myName.compareTo(mTargetName) < 0 ? myName + mTargetName : mTargetName + myName;

            }else {
                channelName = mTargetName;
            }
            Intent intent = new Intent(this, VideoCallActivity.class);
            intent.putExtra("User", user);
            intent.putExtra("Channel", channelName);
            intent.putExtra(MessageUtil.INTENT_EXTRA_IS_PEER_MODE, mIsPeerToPeerMode);
            intent.putExtra("Actual Target", mTargetName);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.CHAT_REQUEST_CODE) {
            if (resultCode == MessageUtil.ACTIVITY_RESULT_CONN_ABORTED) {
                finish();
            }
        }
    }

    private void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    public void LoadData() {
        Log.d("TAG", "LoadData: Chay cai nay");
        rcv_notCall = findViewById(R.id.rcv_notCall);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));
        LinearLayoutManager layoutManager = new LinearLayoutManager(ChannelSelectionActivity.this, LinearLayoutManager.VERTICAL, false);
        rcv_notCall.setLayoutManager(layoutManager);
        rcv_notCall.setHasFixedSize(true);
        rcv_notCall.setItemAnimator(new DefaultItemAnimator());
        service = RetrofitBuilder.createServiceAuth(ApiService.class,tokenManager);
        Call<List<BookCallModel>> call = service.getBookcallUser();
        Log.d(TAG, "LoadData: Chay cai nay truoc ham caall");
        Log.d(TAG, "LoadData: "+tokenManager.getToken().getAccessToken());
        call.enqueue(new Callback<List<BookCallModel>>() {
            @Override
            public void onResponse(Call<List<BookCallModel>> call, Response<List<BookCallModel>> response) {
                List<BookCallModel> data = new ArrayList<>();
                if (response.isSuccessful()) {
                    List<BookCallModel> list = response.body();
                    Log.d(TAG, "List size: "+ list.size());
                    for (BookCallModel b:list) {
                        data.add(b);
                        Log.d(TAG, "onResponse: dang load du lieu ");
                    }
                }
                else {
                    Log.d(TAG, "Code:"+response.code());
                    Log.d(TAG, "error"+response.errorBody());
                }
                Log.d(TAG, "Data size : "+data.size());
                rcv_notCall.setAdapter(new NotificationCodeCallAdapter(ChannelSelectionActivity.this,data));
                Log.d(TAG, "onResponse: Set adapter hoan tat ");
            }

            @Override
            public void onFailure(Call<List<BookCallModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }
}
