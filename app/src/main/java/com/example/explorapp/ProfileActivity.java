package com.example.explorapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

public class ProfileActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private MaterialButton btnLogin;
    private LinearLayout optionNotifications;
    private LinearLayout optionLanguage;
    private LinearLayout optionAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initializeViews();
        setupBottomNavigation();
        setupListeners();
    }

    private void initializeViews() {
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        btnLogin = findViewById(R.id.btn_login);
        optionNotifications = findViewById(R.id.option_notifications);
        optionLanguage = findViewById(R.id.option_language);
        optionAbout = findViewById(R.id.option_about);

        setSupportActionBar(toolbar);
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setSelectedItemId(R.id.nav_profile);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_explore) {
                startActivity(new Intent(this, MapaActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_favorites) {
                startActivity(new Intent(this, FavoritesActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_search) {
                startActivity(new Intent(this, BuscadorActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_profile) {
                // Ya estamos aquí
                return true;
            }
            return false;
        });
    }

    private void setupListeners() {
        btnLogin.setOnClickListener(v -> {
            Toast.makeText(this, "Inicio de sesión - Próximamente", Toast.LENGTH_SHORT).show();
        });

        optionNotifications.setOnClickListener(v -> {
            Toast.makeText(this, "Configuración de notificaciones - Próximamente", Toast.LENGTH_SHORT).show();
        });

        optionLanguage.setOnClickListener(v -> {
            showLanguageDialog();
        });

        optionAbout.setOnClickListener(v -> {
            showAboutDialog();
        });
    }

    private void showLanguageDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        android.view.LayoutInflater inflater = getLayoutInflater();
        android.view.View dialogView = inflater.inflate(R.layout.dialog_language, null);
        builder.setView(dialogView);

        android.widget.RadioGroup radioGroup = dialogView.findViewById(R.id.language_radio_group);

        builder.setPositiveButton("Aplicar", (dialog, which) -> {
            int selectedId = radioGroup.getCheckedRadioButtonId();
            String language = "Español";

            if (selectedId == R.id.radio_spanish) {
                language = "Español";
            } else if (selectedId == R.id.radio_english) {
                language = "English";
            } else if (selectedId == R.id.radio_french) {
                language = "Français";
            } else if (selectedId == R.id.radio_german) {
                language = "Deutsch";
            }

            Toast.makeText(this, "Idioma seleccionado: " + language, Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    private void showAboutDialog() {
        new android.app.AlertDialog.Builder(this)
            .setTitle("Acerca de ExploreApp")
            .setMessage("ExploreApp v1.0\n\nAplicación de turismo para descubrir los mejores lugares de Guadalajara.\n\nDesarrollado en 2025")
            .setPositiveButton("Cerrar", null)
            .show();
    }
}
