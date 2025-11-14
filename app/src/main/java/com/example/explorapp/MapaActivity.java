package com.example.explorapp;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.List;

public class MapaActivity extends AppCompatActivity {

    private MapView mapView;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private MaterialToolbar toolbar;
    private FloatingActionButton fabCurrentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_mapa);

        initializeViews();
        configurarMapa();
        configurarDrawer();
        configurarBottomNavigation();
        agregarMarcadoresMock();
    }

    private void initializeViews() {
        mapView = findViewById(R.id.mapView);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.toolbar);
        fabCurrentLocation = findViewById(R.id.fab_current_location);

        setSupportActionBar(toolbar);
    }

    private void configurarMapa() {
        mapView.setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);
        mapView.getController().setZoom(14.0);
        mapView.setMinZoomLevel(5.0);
        mapView.setMaxZoomLevel(19.0);

        GeoPoint puntoInicial = new GeoPoint(20.6597, -103.3496);
        mapView.getController().setCenter(puntoInicial);

        // FAB para ubicación actual
        fabCurrentLocation.setOnClickListener(v -> {
            mapView.getController().animateTo(puntoInicial);
            Toast.makeText(this, "Centrando en Guadalajara", Toast.LENGTH_SHORT).show();
        });
    }

    private void configurarDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            handleDrawerItemClick(item.getItemId());
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    private void handleDrawerItemClick(int itemId) {
        if (itemId == R.id.category_restaurants) {
            Toast.makeText(this, "Categoría: Restaurantes", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.category_cafes) {
            Toast.makeText(this, "Categoría: Cafeterías", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.category_plazas) {
            Toast.makeText(this, "Categoría: Plazas", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.category_museums) {
            Toast.makeText(this, "Categoría: Museos", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.category_theaters) {
            Toast.makeText(this, "Categoría: Teatros", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.category_hotels) {
            Toast.makeText(this, "Categoría: Hoteles", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.category_bars) {
            Toast.makeText(this, "Categoría: Bares", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.category_parks) {
            Toast.makeText(this, "Categoría: Parques", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.nav_languages) {
            showLanguageDialog();
        } else if (itemId == R.id.nav_help) {
            Toast.makeText(this, "Ayuda", Toast.LENGTH_SHORT).show();
        } else if (itemId == R.id.nav_settings) {
            Toast.makeText(this, "Configuración", Toast.LENGTH_SHORT).show();
        }
    }

    private void configurarBottomNavigation() {
        bottomNavigationView.setSelectedItemId(R.id.nav_explore);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_explore) {
                // Ya estamos en esta pantalla
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
                startActivity(new Intent(this, ProfileActivity.class));
                finish();
                return true;
            }
            return false;
        });
    }

    private void agregarMarcadoresMock() {
        List<PlaceMock> lugares = obtenerLugaresMock();

        for (PlaceMock lugar : lugares) {
            Marker marcador = new Marker(mapView);
            marcador.setPosition(lugar.position);
            marcador.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            marcador.setTitle(lugar.name);
            marcador.setSnippet(lugar.category);

            // Al hacer clic en el marcador, abrir detalles
            marcador.setOnMarkerClickListener((marker, mapView) -> {
                Intent intent = new Intent(MapaActivity.this, PlaceDetailActivity.class);
                intent.putExtra("place_name", lugar.name);
                intent.putExtra("place_category", lugar.category);
                intent.putExtra("place_description", lugar.description);
                intent.putExtra("place_rating", lugar.rating);
                startActivity(intent);
                return true;
            });

            mapView.getOverlays().add(marcador);
        }
    }

    private List<PlaceMock> obtenerLugaresMock() {
        List<PlaceMock> lugares = new ArrayList<>();

        // Catedral de Guadalajara
        lugares.add(new PlaceMock(
            "Catedral de Guadalajara",
            "Iglesia",
            new GeoPoint(20.6767, -103.3475),
            "Hermosa catedral en el centro histórico de Guadalajara.",
            4.8f
        ));

        // Teatro Degollado
        lugares.add(new PlaceMock(
            "Teatro Degollado",
            "Teatro",
            new GeoPoint(20.6763, -103.3434),
            "Teatro neoclásico emblemático de la ciudad.",
            4.7f
        ));

        // Hospicio Cabañas
        lugares.add(new PlaceMock(
            "Hospicio Cabañas",
            "Museo",
            new GeoPoint(20.6755, -103.3397),
            "Patrimonio de la Humanidad con murales de Orozco.",
            4.9f
        ));

        // Plaza de Armas
        lugares.add(new PlaceMock(
            "Plaza de Armas",
            "Plaza",
            new GeoPoint(20.6770, -103.3467),
            "Plaza principal de Guadalajara con kiosco francés.",
            4.6f
        ));

        // Mercado San Juan de Dios
        lugares.add(new PlaceMock(
            "Mercado San Juan de Dios",
            "Mercado",
            new GeoPoint(20.6748, -103.3454),
            "Uno de los mercados cubiertos más grandes de Latinoamérica.",
            4.5f
        ));

        // Parque Agua Azul
        lugares.add(new PlaceMock(
            "Parque Agua Azul",
            "Parque",
            new GeoPoint(20.6678, -103.3386),
            "Parque urbano con áreas verdes y juegos.",
            4.4f
        ));

        // Rotonda de los Jaliscienses Ilustres
        lugares.add(new PlaceMock(
            "Rotonda de los Jaliscienses Ilustres",
            "Monumento",
            new GeoPoint(20.6730, -103.3615),
            "Monumento en honor a personajes destacados de Jalisco.",
            4.5f
        ));

        // Templo Expiatorio
        lugares.add(new PlaceMock(
            "Templo Expiatorio",
            "Iglesia",
            new GeoPoint(20.6797, -103.3562),
            "Templo de estilo neogótico con hermosos vitrales.",
            4.8f
        ));

        return lugares;
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
            // En el futuro: cambiar el idioma de la app
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null) {
            mapView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mapView != null) {
            mapView.onPause();
        }
    }

    // Clase interna para los datos mock
    private static class PlaceMock {
        String name;
        String category;
        GeoPoint position;
        String description;
        float rating;

        PlaceMock(String name, String category, GeoPoint position, String description, float rating) {
            this.name = name;
            this.category = category;
            this.position = position;
            this.description = description;
            this.rating = rating;
        }
    }
}