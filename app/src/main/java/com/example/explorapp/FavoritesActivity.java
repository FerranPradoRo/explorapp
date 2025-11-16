package com.example.explorapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

public class FavoritesActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private MaterialButton btnExplore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        initializeViews();
        setupBottomNavigation();
    }

    private void initializeViews() {
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        btnExplore = findViewById(R.id.btn_explore);

        setSupportActionBar(toolbar);

        btnExplore.setOnClickListener(v -> {
            Intent intent = new Intent(FavoritesActivity.this, MapaActivity.class);
            startActivity(intent);
        });
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setSelectedItemId(R.id.nav_favorites);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_explore) {
                startActivity(new Intent(this, MapaActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_favorites) {
                return true;
            } else if (itemId == R.id.nav_search) {
                startActivity(new Intent(this, BuscadorActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                finish();
                return true;
            }
            return false;
        });
    }
}
