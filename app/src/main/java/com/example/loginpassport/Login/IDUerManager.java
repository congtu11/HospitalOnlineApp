package com.example.loginpassport.Login;

import android.content.SharedPreferences;

import com.example.loginpassport.entities.IDUser;

public class IDUerManager {
    private SharedPreferences prefuser;
    private SharedPreferences.Editor usereditor;

    private static IDUerManager INSTANCE = null;

    private IDUerManager(SharedPreferences prefs) {
        this.prefuser = prefs;
        this.usereditor = prefs.edit();
    }

    static synchronized IDUerManager getInstance(SharedPreferences prefs) {
        if (INSTANCE == null) {
            INSTANCE = new IDUerManager(prefs);
        }
        return INSTANCE;
    }

    public void saveID(IDUser idUser) {
        usereditor.putInt("ID_USER", idUser.getID_USER()).commit();

    }

    public void deleteID() {
        usereditor.remove("ID_USER").commit();

    }

    public IDUser getID() {
        IDUser idUser = new IDUser();
        idUser.setID_USER(prefuser.getInt("ID_USER",0));
        return idUser;
    }
}
