package com.example.loginpassport.Login;

import android.content.SharedPreferences;

import com.example.loginpassport.entities.AccessToken;


public class TokenManager {

    public SharedPreferences prefs;
    public SharedPreferences.Editor editor;

    public static TokenManager INSTANCE = null;

   public TokenManager(SharedPreferences prefs) {
        this.prefs = prefs;
        this.editor = prefs.edit();
    }

    public static synchronized TokenManager getInstance(SharedPreferences prefs) {
        if (INSTANCE == null) {
            INSTANCE = new TokenManager(prefs);
        }
        return INSTANCE;
    }

    public void saveToken(AccessToken token) {
        editor.putString("ACCESS_TOKEN", token.getAccessToken()).commit();
        editor.putString("REFRESH_TOKEN", token.getRefreshToken()).commit();
    }

    public void deleteToken() {
        editor.remove("ACCESS_TOKEN").commit();
        editor.remove("REFRESH_TOKEN").commit();
    }

    public AccessToken getToken() {
        AccessToken token = new AccessToken();
        token.setAccessToken(prefs.getString("ACCESS_TOKEN", null));
        token.setRefreshToken(prefs.getString("REFRESH_TOKEN", null));
        return token;
    }
}
