package com.example.explorapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class LoginActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "ExplorappPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_IS_GUEST = "isGuest";

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private MaterialButton btnGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (checkUserSession()) {
            navigateToMainApp();
            return;
        }

        setContentView(R.layout.activity_login);

        initializeViews();
        setupViewPager();
        setupGuestButton();
    }

    private void initializeViews() {
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        btnGuest = findViewById(R.id.btn_guest);
    }

    private void setupViewPager() {
        LoginPagerAdapter adapter = new LoginPagerAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText("Iniciar Sesión");
                    } else {
                        tab.setText("Registrarse");
                    }
                }
        ).attach();
    }

    private void setupGuestButton() {
        btnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAsGuest();
            }
        });
    }

    private boolean checkUserSession() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    private void loginAsGuest() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putBoolean(KEY_IS_GUEST, true);
        editor.putLong(KEY_USER_ID, -1); // -1 indica usuario invitado
        editor.apply();

        navigateToMainApp();
    }

    public void saveUserSession(long userId) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putBoolean(KEY_IS_GUEST, false);
        editor.putLong(KEY_USER_ID, userId);
        editor.apply();

        navigateToMainApp();
    }

    private void navigateToMainApp() {
        Intent intent = new Intent(this, BuscadorActivity.class);
        startActivity(intent);
        finish();
    }

    private static class LoginPagerAdapter extends FragmentStateAdapter {

        public LoginPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 0) {
                return new LoginFragment();
            } else {
                return new RegisterFragment();
            }
        }

        @Override
        public int getItemCount() {
            return 2; // Login y Registro
        }
    }
}
