package com.example.explorapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.explorapp.models.Favorite;
import com.example.explorapp.models.Location;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class FavoritesActivity extends AppCompatActivity implements FavoritesAdapter.OnFavoriteClickListener {

    private static final String PREFS_NAME = "ExplorappPrefs";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_IS_GUEST = "isGuest";

    private MaterialToolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerFavorites;
    private LinearLayout emptyView;
    private MaterialButton btnExplore;

    private DatabaseHelper dbHelper;
    private FavoritesAdapter adapter;
    private long userId;
    private boolean isGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        dbHelper = new DatabaseHelper(this);
        loadUserSession();
        initializeViews();
        setupRecyclerView();
        setupBottomNavigation();
        loadFavorites();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Recargar favoritos cuando se vuelve a esta actividad
        loadFavorites();
    }

    private void loadUserSession() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        isGuest = prefs.getBoolean(KEY_IS_GUEST, true);
        userId = prefs.getLong(KEY_USER_ID, -1);
    }

    private void initializeViews() {
        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        recyclerFavorites = findViewById(R.id.recycler_favorites);
        emptyView = findViewById(R.id.empty_view);
        btnExplore = findViewById(R.id.btn_explore);

        setSupportActionBar(toolbar);

        btnExplore.setOnClickListener(v -> {
            Intent intent = new Intent(FavoritesActivity.this, MapaActivity.class);
            startActivity(intent);
        });
    }

    private void setupRecyclerView() {
        adapter = new FavoritesAdapter(this);
        recyclerFavorites.setLayoutManager(new LinearLayoutManager(this));
        recyclerFavorites.setAdapter(adapter);
    }

    private void loadFavorites() {
        if (isGuest) {
            showEmptyView();
            return;
        }

        List<Favorite> favorites = getFavoritesFromDatabase();

        if (favorites.isEmpty()) {
            showEmptyView();
        } else {
            showFavoritesList();
            adapter.setFavorites(favorites);
        }
    }

    private List<Favorite> getFavoritesFromDatabase() {
        List<Favorite> favorites = new ArrayList<>();
        Cursor cursor = dbHelper.getFavoritesByUser(userId);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Favorite favorite = new Favorite();

                // Datos del favorito
                long favoriteId = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_FAVORITE_ID));
                long locationId = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_FAVORITE_LOCATION_ID));
                long dateAdded = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_FAVORITE_DATE_ADDED));

                favorite.setFavoriteId(favoriteId);
                favorite.setUserId(userId);
                favorite.setLocationId(locationId);
                favorite.setDateAdded(dateAdded);

                // Datos de la localización (vienen del JOIN)
                Location location = new Location();
                location.setLocationId(locationId);
                location.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCATION_NAME)));
                location.setCity(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCATION_CITY)));

                // Obtener nombre de categoría si existe
                int categoryIndex = cursor.getColumnIndex("category_name");
                if (categoryIndex != -1) {
                    location.setCategoryName(cursor.getString(categoryIndex));
                }

                favorite.setLocation(location);
                favorites.add(favorite);

            } while (cursor.moveToNext());
            cursor.close();
        }

        return favorites;
    }

    private void showEmptyView() {
        recyclerFavorites.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
    }

    private void showFavoritesList() {
        recyclerFavorites.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public void onFavoriteClick(Favorite favorite) {
        // TODO: Abrir detalles del lugar
        Toast.makeText(this, "Ver detalles de: " + favorite.getLocation().getName(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRemoveClick(Favorite favorite, int position) {
        // Confirmar antes de eliminar
        new android.app.AlertDialog.Builder(this)
                .setTitle("Eliminar de favoritos")
                .setMessage("¿Deseas eliminar '" + favorite.getLocation().getName() + "' de tus favoritos?")
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    int result = dbHelper.removeFavorite(userId, favorite.getLocationId());
                    if (result > 0) {
                        adapter.removeFavorite(position);
                        Toast.makeText(this, "Eliminado de favoritos", Toast.LENGTH_SHORT).show();

                        // Si no quedan más favoritos, mostrar vista vacía
                        if (adapter.getItemCount() == 0) {
                            showEmptyView();
                        }
                    } else {
                        Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
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
