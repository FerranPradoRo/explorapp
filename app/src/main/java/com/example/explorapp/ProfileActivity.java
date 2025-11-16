package com.example.explorapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

public class ProfileActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "ExplorappPrefs";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_IS_GUEST = "isGuest";

    private MaterialToolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private TextView tvUserName;
    private TextView tvUserEmail;
    private MaterialButton btnLogin;
    private MaterialButton btnLogout;
    private LinearLayout optionNotifications;
    private LinearLayout optionLanguage;
    private LinearLayout optionAbout;

    private DatabaseHelper dbHelper;
    private boolean isGuest;
    private long userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        dbHelper = new DatabaseHelper(this);
        loadUserSession();
        initializeViews();
        loadUserData();
        setupBottomNavigation();
        setupListeners();
    }

    /**
     * Cargar datos de sesión desde SharedPreferences
     */
    private void loadUserSession() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        isGuest = prefs.getBoolean(KEY_IS_GUEST, true);
        userId = prefs.getLong(KEY_USER_ID, -1);
    }

    private void initializeViews() {
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        tvUserName = findViewById(R.id.tv_user_name);
        tvUserEmail = findViewById(R.id.tv_user_email);
        btnLogin = findViewById(R.id.btn_login);
        btnLogout = findViewById(R.id.btn_logout);
        optionNotifications = findViewById(R.id.option_notifications);
        optionLanguage = findViewById(R.id.option_language);
        optionAbout = findViewById(R.id.option_about);

        setSupportActionBar(toolbar);
    }

    private void loadUserData() {
        if (isGuest) {
            tvUserName.setText("Usuario Invitado");
            tvUserEmail.setText("Inicia sesión para guardar tus favoritos");
            btnLogin.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.GONE);
        } else {
            Cursor cursor = dbHelper.buscarUsuarioPorId(userId);

            if (cursor != null && cursor.moveToFirst()) {
                String nombre = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USUARIO_NOMBRE));
                String apellido = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USUARIO_APELLIDO));
                String email = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USUARIO_EMAIL));

                tvUserName.setText(nombre + " " + apellido);
                tvUserEmail.setText(email);

                cursor.close();
            } else {
                tvUserName.setText("Error");
                tvUserEmail.setText("No se pudo cargar la información");
                if (cursor != null) {
                    cursor.close();
                }
            }

            btnLogin.setVisibility(View.GONE);
            btnLogout.setVisibility(View.VISIBLE);
        }
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
                return true;
            }
            return false;
        });
    }

    private void setupListeners() {
        btnLogin.setOnClickListener(v -> {
            navigateToLogin();
        });

        btnLogout.setOnClickListener(v -> {
            logout();
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

    private void navigateToLogin() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        prefs.edit().clear().apply();

        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void logout() {
        new android.app.AlertDialog.Builder(this)
            .setTitle("Cerrar Sesión")
            .setMessage("¿Estás seguro que deseas cerrar sesión?")
            .setPositiveButton("Sí, cerrar sesión", (dialog, which) -> {
                SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
                prefs.edit().clear().apply();

                Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            })
            .setNegativeButton("Cancelar", null)
            .show();
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
            .setTitle("Acerca de EXPLORA")
            .setMessage("EXPLORA v1.0\n\nAplicación de turismo para descubrir los mejores lugares de Guadalajara.\n\nDesarrollado en 2025")
            .setPositiveButton("Cerrar", null)
            .show();
    }
}
