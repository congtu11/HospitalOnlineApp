package com.example.loginpassport.MainActiviti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.loginpassport.Login.TokenManager;
import com.example.loginpassport.R;
import com.example.loginpassport.activites.ChannelSelectionActivity;
import com.example.loginpassport.model.User;
import com.example.loginpassport.utils.MessageUtil;

import io.agora.rtm.RtmClient;

public class CallActivity extends AppCompatActivity {
    TextView statusTextView;
    //    GoogleApiClient mGoogleApiClient;
//    private GoogleSignInAccount acct;
    private RtmClient mRtmClient;

    private static final int RC_SIGN_IN = 9001;
    TokenManager tokenManager;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_call);
//        mChatManager = AGApplication.the().getChatManager();
//        mRtmClient = mChatManager.getRtmClient();
            Intent intent = getIntent();
            String id = intent.getStringExtra("id");
            String name = intent.getStringExtra("name");
            handleSignInResult(id,name);
        }

        private void handleSignInResult(String id,String name) {

            final User user = new User(id);
            user.setFireDisplayName(name);
            Intent intent = new Intent(CallActivity.this, ChannelSelectionActivity.class);
            intent.putExtra(MessageUtil.INTENT_EXTRA_USER_ID, user);
            startActivity(intent);
        }

        public void signOut() {

        }
    }

