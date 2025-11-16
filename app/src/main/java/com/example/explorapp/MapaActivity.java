package com.example.explorapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.explorapp.models.Localizacion;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapaActivity extends AppCompatActivity {

    private MapView mapView;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private MaterialToolbar toolbar;
    private FloatingActionButton fabCurrentLocation;
    private com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton btnFilters;
    private Marker selectedMarker = null;
    private DatabaseHelper dbHelper;

    // Mapa para asociar marcadores con localizaciones
    private Map<Marker, Localizacion> markerLocalizacionMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_mapa);

        dbHelper = new DatabaseHelper(this);
        initializeViews();
        configurarMapa();
        configurarDrawer();
        configurarBottomNavigation();
        cargarLocalizaciones();
        setupBackPressHandler();
    }

    private void initializeViews() {
        mapView = findViewById(R.id.mapView);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        toolbar = findViewById(R.id.toolbar);
        fabCurrentLocation = findViewById(R.id.fab_current_location);
        btnFilters = findViewById(R.id.btn_filters);

        setSupportActionBar(toolbar);

        // Listener para el botón de filtros
        btnFilters.setOnClickListener(v -> showFiltersDialog());
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
        if (itemId == R.id.nav_languages) {
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

    private void cargarLocalizaciones() {
        List<Localizacion> localizaciones = obtenerLocalizacionesDesdeDB();

        if (localizaciones.isEmpty()) {
            Toast.makeText(this, "No se encontraron lugares en Guadalajara", Toast.LENGTH_SHORT).show();
            return;
        }

        for (Localizacion localizacion : localizaciones) {
            Marker marcador = new Marker(mapView);
            GeoPoint position = new GeoPoint(localizacion.getLatitud(), localizacion.getLongitud());
            marcador.setPosition(position);
            marcador.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            marcador.setTitle(localizacion.getNombre());
            marcador.setSnippet(localizacion.getCategoriaNombre());

            // Ícono predeterminado (azul/gris)
            marcador.setIcon(getResources().getDrawable(android.R.drawable.ic_menu_mylocation));

            // Asociar el marcador con la localización
            markerLocalizacionMap.put(marcador, localizacion);

            // Al hacer clic en el marcador, cambiar color y mostrar bottom sheet
            marcador.setOnMarkerClickListener((marker, mapView) -> {
                // Restaurar el color del marcador anterior
                if (selectedMarker != null && selectedMarker != marker) {
                    selectedMarker.setIcon(getResources().getDrawable(android.R.drawable.ic_menu_mylocation));
                }

                // Cambiar color del marcador actual (amarillo/destacado)
                marker.setIcon(getResources().getDrawable(android.R.drawable.star_big_on));
                selectedMarker = marker;

                // Obtener la localización asociada al marcador
                Localizacion loc = markerLocalizacionMap.get(marker);
                if (loc != null) {
                    showPlaceDetailsBottomSheet(loc);
                }

                mapView.invalidate();
                return true;
            });

            mapView.getOverlays().add(marcador);
        }
    }

    private List<Localizacion> obtenerLocalizacionesDesdeDB() {
        List<Localizacion> localizaciones = new ArrayList<>();
        Cursor cursor = dbHelper.obtenerLocalizacionesPorCiudad("Guadalajara");

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Localizacion loc = new Localizacion();
                loc.setLocalizacionId(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCALIZACION_ID)));
                loc.setNombre(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCALIZACION_NOMBRE)));
                loc.setDescripcion(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCALIZACION_DESCRIPCION)));
                loc.setLatitud(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCALIZACION_LATITUD)));
                loc.setLongitud(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCALIZACION_LONGITUD)));
                loc.setPopularidadScore(cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCALIZACION_POPULARIDAD_SCORE)));

                // Obtener nombre de categoría si existe
                int categoriaIndex = cursor.getColumnIndex("categoria_nombre");
                if (categoriaIndex != -1) {
                    loc.setCategoriaNombre(cursor.getString(categoriaIndex));
                }

                localizaciones.add(loc);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return localizaciones;
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

    private void showFiltersDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        android.view.LayoutInflater inflater = getLayoutInflater();
        android.view.View dialogView = inflater.inflate(R.layout.dialog_filters, null);
        builder.setView(dialogView);

        // Referencias a las vistas del diálogo
        android.widget.CheckBox filterRestaurants = dialogView.findViewById(R.id.filter_restaurants);
        android.widget.CheckBox filterCafes = dialogView.findViewById(R.id.filter_cafes);
        android.widget.CheckBox filterPlazas = dialogView.findViewById(R.id.filter_plazas);
        android.widget.CheckBox filterMuseums = dialogView.findViewById(R.id.filter_museums);
        android.widget.CheckBox filterTheaters = dialogView.findViewById(R.id.filter_theaters);
        android.widget.CheckBox filterHotels = dialogView.findViewById(R.id.filter_hotels);
        android.widget.CheckBox filterBars = dialogView.findViewById(R.id.filter_bars);
        android.widget.CheckBox filterParks = dialogView.findViewById(R.id.filter_parks);

        android.widget.RadioGroup priceRadioGroup = dialogView.findViewById(R.id.price_radio_group);
        android.widget.RatingBar ratingFilter = dialogView.findViewById(R.id.rating_filter);
        android.widget.TextView ratingText = dialogView.findViewById(R.id.rating_text);

        com.google.android.material.button.MaterialButton btnApply = dialogView.findViewById(R.id.btn_apply_filters);
        com.google.android.material.button.MaterialButton btnClear = dialogView.findViewById(R.id.btn_clear_filters);

        // Listener para el rating bar
        ratingFilter.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            if (rating == 0) {
                ratingText.setText("Todas");
            } else {
                ratingText.setText(rating + "+ estrellas");
            }
        });

        android.app.AlertDialog dialog = builder.create();

        // Botón Aplicar
        btnApply.setOnClickListener(v -> {
            StringBuilder filterSummary = new StringBuilder("Filtros aplicados:\n");

            // Categorías seleccionadas
            java.util.ArrayList<String> categories = new java.util.ArrayList<>();
            if (filterRestaurants.isChecked()) categories.add("Restaurantes");
            if (filterCafes.isChecked()) categories.add("Cafeterías");
            if (filterPlazas.isChecked()) categories.add("Plazas");
            if (filterMuseums.isChecked()) categories.add("Museos");
            if (filterTheaters.isChecked()) categories.add("Teatros");
            if (filterHotels.isChecked()) categories.add("Hoteles");
            if (filterBars.isChecked()) categories.add("Bares");
            if (filterParks.isChecked()) categories.add("Parques");

            if (!categories.isEmpty()) {
                filterSummary.append("Categorías: ").append(String.join(", ", categories)).append("\n");
            }

            // Precio seleccionado
            int selectedPriceId = priceRadioGroup.getCheckedRadioButtonId();
            if (selectedPriceId == R.id.price_low) {
                filterSummary.append("Precio: Económico\n");
            } else if (selectedPriceId == R.id.price_medium) {
                filterSummary.append("Precio: Moderado\n");
            } else if (selectedPriceId == R.id.price_high) {
                filterSummary.append("Precio: Costoso\n");
            }

            // Rating seleccionado
            float rating = ratingFilter.getRating();
            if (rating > 0) {
                filterSummary.append("Calificación mínima: ").append(rating).append(" estrellas");
            }

            Toast.makeText(this, filterSummary.toString(), Toast.LENGTH_LONG).show();
            // En el futuro: aplicar filtros reales al mapa
            dialog.dismiss();
        });

        // Botón Limpiar
        btnClear.setOnClickListener(v -> {
            filterRestaurants.setChecked(false);
            filterCafes.setChecked(false);
            filterPlazas.setChecked(false);
            filterMuseums.setChecked(false);
            filterTheaters.setChecked(false);
            filterHotels.setChecked(false);
            filterBars.setChecked(false);
            filterParks.setChecked(false);
            priceRadioGroup.check(R.id.price_all);
            ratingFilter.setRating(0);
            Toast.makeText(this, "Filtros limpiados", Toast.LENGTH_SHORT).show();
        });

        dialog.show();
    }

    /**
     * Mostrar bottom sheet con detalles de la localización
     */
    private void showPlaceDetailsBottomSheet(Localizacion localizacion) {
        com.google.android.material.bottomsheet.BottomSheetDialog bottomSheet =
            new com.google.android.material.bottomsheet.BottomSheetDialog(this);
        bottomSheet.setDismissWithAnimation(true);

        // Desactivar el oscurecimiento por defecto del dialog
        android.view.Window window = bottomSheet.getWindow();
        if (window != null) {
            window.clearFlags(android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        }

        android.view.View sheetView = getLayoutInflater().inflate(R.layout.bottom_sheet_place_detail, null);

        // Referencias a las vistas
        android.widget.TextView placeName = sheetView.findViewById(R.id.place_name);
        android.widget.TextView placeCategory = sheetView.findViewById(R.id.place_category);
        android.widget.TextView placeDescription = sheetView.findViewById(R.id.place_description);
        android.widget.TextView ratingValue = sheetView.findViewById(R.id.rating_value);
        android.widget.RatingBar placeRating = sheetView.findViewById(R.id.place_rating);

        com.google.android.material.button.MaterialButton btnDirections = sheetView.findViewById(R.id.btn_directions);
        com.google.android.material.button.MaterialButton btnSave = sheetView.findViewById(R.id.btn_save);
        com.google.android.material.button.MaterialButton btnShare = sheetView.findViewById(R.id.btn_share);

        // Establecer datos desde la base de datos
        placeName.setText(localizacion.getNombre());
        placeCategory.setText(localizacion.getCategoriaNombre() != null ? localizacion.getCategoriaNombre() : "Sin categoría");
        placeDescription.setText(localizacion.getDescripcion());
        placeRating.setRating(localizacion.getPopularidadScore());
        ratingValue.setText(String.format("%.1f", localizacion.getPopularidadScore()));

        // Listeners de botones
        btnDirections.setOnClickListener(v -> {
            Toast.makeText(this, "Abriendo direcciones - Próximamente", Toast.LENGTH_SHORT).show();
        });

        btnSave.setOnClickListener(v -> {
            Toast.makeText(this, "Lugar guardado - Próximamente", Toast.LENGTH_SHORT).show();
        });

        btnShare.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            String shareBody = "¡Mira este lugar en EXPLORA! " + localizacion.getNombre();
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "EXPLORA - " + localizacion.getNombre());
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(shareIntent, "Compartir lugar"));
        });

        bottomSheet.setContentView(sheetView);

        // Configurar comportamiento del bottom sheet
        bottomSheet.getBehavior().setPeekHeight(720);
        bottomSheet.getBehavior().setState(com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED);

        com.google.android.material.bottomsheet.BottomSheetBehavior<?> behavior = bottomSheet.getBehavior();
        if (behavior != null) {

            // Configurar peek height (altura cuando está colapsado - mostrar imagen + nombre completo)
            behavior.setPeekHeight(720);
            behavior.setState(com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED);
            behavior.setSkipCollapsed(false);
            behavior.setHideable(true);

            // Variable para rastrear si está expandiendo desde colapsado
            final boolean[] isExpanding = {false};

            // Listener para controlar el oscurecimiento según el estado
            behavior.addBottomSheetCallback(new com.google.android.material.bottomsheet.BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@androidx.annotation.NonNull android.view.View bottomSheetView, int newState) {
                    android.view.Window dialogWindow = bottomSheet.getWindow();
                    if (dialogWindow != null) {
                        android.view.WindowManager.LayoutParams params = dialogWindow.getAttributes();

                        if (newState == com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED) {
                            // Oscurecer cuando está expandido
                            params.dimAmount = 0.5f;
                            params.flags |= android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                            isExpanding[0] = false;
                        } else if (newState == com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED) {
                            // No oscurecer cuando está colapsado
                            params.dimAmount = 0f;
                            params.flags &= ~android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                            isExpanding[0] = false;
                        } else if (newState == com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_DRAGGING) {
                            isExpanding[0] = true;
                        } else if (newState == com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_SETTLING) {
                            // Cuando se está asentando después del clic
                            if (!isExpanding[0]) {
                                params.dimAmount = 0f;
                                params.flags &= ~android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                            }
                        }
                        dialogWindow.setAttributes(params);
                    }
                }

                @Override
                public void onSlide(@androidx.annotation.NonNull android.view.View bottomSheetView, float slideOffset) {
                    // Oscurecer gradualmente cuando está expandiendo (arrastrar o clic)
                    android.view.Window dialogWindow = bottomSheet.getWindow();
                    if (dialogWindow != null && isExpanding[0]) {
                        android.view.WindowManager.LayoutParams params = dialogWindow.getAttributes();
                        // Oscurecer gradualmente de 0% a 50% según el deslizamiento
                        // slideOffset va de 0 (colapsado) a 1 (expandido)
                        if (slideOffset > 0.05f) {
                            params.dimAmount = Math.max(0, Math.min(0.5f, (slideOffset - 0.05f) / 0.95f * 0.5f));
                            params.flags |= android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                        } else {
                            params.dimAmount = 0f;
                            params.flags &= ~android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                        }
                        dialogWindow.setAttributes(params);
                    }
                }
            });

            // Hacer clic en el bottom sheet colapsado para expandirlo con animación
            sheetView.setOnClickListener(v -> {
                if (behavior.getState() == com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED) {
                    // Marcar como expandiendo para que se anime el oscurecimiento
                    isExpanding[0] = true;
                    behavior.setState(com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_EXPANDED);
                }
            });
        }

        // Listener para cuando se cierra el bottom sheet
        bottomSheet.setOnDismissListener(dialog -> {
            // Restaurar color del marcador cuando se cierra
            if (selectedMarker != null) {
                selectedMarker.setIcon(getResources().getDrawable(android.R.drawable.ic_menu_mylocation));
                mapView.invalidate();
                selectedMarker = null;
            }

            // Asegurar que el dim se elimine al cerrar
            android.view.Window dialogWindow = bottomSheet.getWindow();
            if (dialogWindow != null) {
                android.view.WindowManager.LayoutParams params = dialogWindow.getAttributes();
                params.dimAmount = 0f;
                params.flags &= ~android.view.WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                dialogWindow.setAttributes(params);
            }
        });

        bottomSheet.show();
    }

    private void setupBackPressHandler() {
        getOnBackPressedDispatcher().addCallback(this, new androidx.activity.OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    // Deshabilitar este callback y dejar que el sistema maneje el back press
                    setEnabled(false);
                    getOnBackPressedDispatcher().onBackPressed();
                }
            }
        });
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

}