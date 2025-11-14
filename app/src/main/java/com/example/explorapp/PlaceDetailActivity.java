package com.example.explorapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PlaceDetailActivity extends AppCompatActivity {

    private TextView placeName;
    private TextView placeCategory;
    private TextView placeDescription;
    private TextView placeSchedule;
    private TextView ratingValue;
    private RatingBar placeRating;
    private MaterialButton btnDirections;
    private MaterialButton btnSave;
    private MaterialButton btnShare;
    private MaterialButton btnReviews;
    private FloatingActionButton fabFavorite;
    private MaterialToolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;

    private boolean isFavorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_detail);

        initializeViews();
        loadPlaceData();
        setupListeners();
    }

    private void initializeViews() {
        placeName = findViewById(R.id.place_name);
        placeCategory = findViewById(R.id.place_category);
        placeDescription = findViewById(R.id.place_description);
        placeSchedule = findViewById(R.id.place_schedule);
        ratingValue = findViewById(R.id.rating_value);
        placeRating = findViewById(R.id.place_rating);
        btnDirections = findViewById(R.id.btn_directions);
        btnSave = findViewById(R.id.btn_save);
        btnShare = findViewById(R.id.btn_share);
        btnReviews = findViewById(R.id.btn_reviews);
        fabFavorite = findViewById(R.id.fab_favorite);
        toolbar = findViewById(R.id.toolbar);
        collapsingToolbar = findViewById(R.id.collapsing_toolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void loadPlaceData() {
        // Obtener datos del Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("place_name");
        String category = intent.getStringExtra("place_category");
        String description = intent.getStringExtra("place_description");
        float rating = intent.getFloatExtra("place_rating", 0.0f);

        // Establecer los datos en las vistas
        if (name != null) {
            placeName.setText(name);
            collapsingToolbar.setTitle(name);
        }

        if (category != null) {
            placeCategory.setText(category);
        }

        if (description != null) {
            placeDescription.setText(description);
        }

        if (rating > 0) {
            placeRating.setRating(rating);
            ratingValue.setText(String.format("%.1f", rating));
        }
    }

    private void setupListeners() {
        // Botón de navegación (cerrar)
        toolbar.setNavigationOnClickListener(v -> finish());

        // Botón "Cómo llegar"
        btnDirections.setOnClickListener(v -> {
            Toast.makeText(this, "Abriendo direcciones - Próximamente", Toast.LENGTH_SHORT).show();
            // En el futuro: Integrar con Google Maps para mostrar rutas
        });

        // Botón "Guardar"
        btnSave.setOnClickListener(v -> {
            Toast.makeText(this, "Lugar guardado - Próximamente", Toast.LENGTH_SHORT).show();
            // En el futuro: Guardar en base de datos local
        });

        // Botón "Compartir"
        btnShare.setOnClickListener(v -> {
            sharePlace();
        });

        // Botón "Ver reseñas"
        btnReviews.setOnClickListener(v -> {
            Toast.makeText(this, "Mostrando reseñas - Próximamente", Toast.LENGTH_SHORT).show();
            // En el futuro: Abrir pantalla de reseñas
        });

        // FAB Favorito
        fabFavorite.setOnClickListener(v -> {
            toggleFavorite();
        });
    }

    private void sharePlace() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareBody = "¡Mira este lugar en EXPLORA! " + placeName.getText().toString();
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "EXPLORA - " + placeName.getText().toString());
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(shareIntent, "Compartir lugar"));
    }

    private void toggleFavorite() {
        isFavorite = !isFavorite;
        if (isFavorite) {
            fabFavorite.setImageResource(android.R.drawable.star_big_on);
            Toast.makeText(this, "Agregado a favoritos", Toast.LENGTH_SHORT).show();
        } else {
            fabFavorite.setImageResource(android.R.drawable.star_big_off);
            Toast.makeText(this, "Removido de favoritos", Toast.LENGTH_SHORT).show();
        }
        // En el futuro: Guardar estado en base de datos
    }
}
