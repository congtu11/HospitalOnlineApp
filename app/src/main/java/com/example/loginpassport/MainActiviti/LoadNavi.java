package com.example.loginpassport.MainActiviti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.loginpassport.Fragment.CartFragment;
import com.example.loginpassport.Fragment.HomeFragment;
import com.example.loginpassport.Fragment.NotificationFragment;
import com.example.loginpassport.Fragment.ProfileUserFragment;
import com.example.loginpassport.Interface.OnFragmentManager;
import com.example.loginpassport.Login.TokenManager;
import com.example.loginpassport.R;
import com.example.loginpassport.model.ItemMainMenu_Model;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class LoadNavi extends AppCompatActivity implements OnFragmentManager {
    BottomNavigationView bottomNavigation;
    RecyclerView rvItems;
    List<ItemMainMenu_Model> itemMainMenus;
    private ActionBar toolbar;
    public TokenManager tokenManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_navi);
        toolbar = getSupportActionBar();
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        toolbar.setTitle("Home");
        loadFragment(new HomeFragment());
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs",MODE_PRIVATE));

    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment;
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            toolbar.hide();
                            fragment = new HomeFragment();
                            loadFragment(fragment);
                            return true;
                        case R.id.navigation_cart:
                            toolbar.setTitle("Cart");
                          fragment= new CartFragment();
                            loadFragment(fragment);
                            return true;
                        case R.id.navigation_profile:
                            toolbar.setTitle("Profile");
                            fragment = new ProfileUserFragment();
                            loadFragment(fragment);
                            return true;
                        case R.id.navigation_notification:
                            toolbar.setTitle("Notification");
                            fragment = new NotificationFragment();
                            loadFragment(fragment);
                            return true;
                    }
                    return false;
                }
            };
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void onDataSelected(TokenManager tokenManager) {

    }
}
