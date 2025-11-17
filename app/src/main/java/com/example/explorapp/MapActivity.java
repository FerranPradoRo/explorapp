package com.example.explorapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.explorapp.models.Location;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "ExplorappPrefs";
    private static final String KEY_USER_ID = "userId";
    private static final String KEY_IS_GUEST = "isGuest";

    private MapView mapView;
    private BottomNavigationView bottomNavigationView;
    private FloatingActionButton fabCurrentLocation;
    private com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton btnFilters;
    private Marker selectedMarker = null;
    private DatabaseHelper dbHelper;

    // Mapa para asociar marcadores con localizaciones
    private Map<Marker, Location> markerLocationMap = new HashMap<>();

    // Filtros activos
    private List<Long> selectedCategoryIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().load(this, getSharedPreferences("osmdroid", MODE_PRIVATE));

        setContentView(R.layout.activity_map);

        dbHelper = new DatabaseHelper(this);
        initializeViews();
        setupMap();
        setupBottomNavigation();
        loadLocations();
    }

    private void initializeViews() {
        mapView = findViewById(R.id.mapView);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        fabCurrentLocation = findViewById(R.id.fab_current_location);
        btnFilters = findViewById(R.id.btn_filters);

        // Listener para el botón de filtros
        btnFilters.setOnClickListener(v -> showFiltersDialog());
    }

    private void setupMap() {
        mapView.setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK);
        mapView.setMultiTouchControls(true);
        mapView.getController().setZoom(14.0);
        mapView.setMinZoomLevel(5.0);
        mapView.setMaxZoomLevel(19.0);

        GeoPoint initialPoint = new GeoPoint(20.6597, -103.3496);
        mapView.getController().setCenter(initialPoint);

        // FAB para ubicación actual
        fabCurrentLocation.setOnClickListener(v -> {
            mapView.getController().animateTo(initialPoint);
            Toast.makeText(this, "Centrando en Guadalajara", Toast.LENGTH_SHORT).show();
        });
    }

    private void setupBottomNavigation() {
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
                startActivity(new Intent(this, SearchActivity.class));
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

    private void loadLocations() {
        List<Location> locations = getLocationsFromDB();

        if (locations.isEmpty()) {
            Toast.makeText(this, "No se encontraron lugares en Guadalajara", Toast.LENGTH_SHORT).show();
            return;
        }

        addMarkersToMap(locations);
    }

    private List<Location> getLocationsFromDB() {
        List<Location> locations = new ArrayList<>();
        Cursor cursor = dbHelper.getLocationsByCity("Guadalajara");

        if (cursor != null && cursor.moveToFirst()) {
            do {
                Location loc = new Location();
                loc.setLocationId(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCATION_ID)));
                loc.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCATION_NAME)));
                loc.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCATION_DESCRIPTION)));
                loc.setLatitude(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCATION_LATITUDE)));
                loc.setLongitude(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCATION_LONGITUDE)));
                loc.setPopularityScore(cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCATION_POPULARITY_SCORE)));

                // Obtener nombre de categoría si existe
                int categoryIndex = cursor.getColumnIndex("category_name");
                if (categoryIndex != -1) {
                    loc.setCategoryName(cursor.getString(categoryIndex));
                }

                locations.add(loc);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return locations;
    }

    private void addMarkersToMap(List<Location> locations) {
        for (Location location : locations) {
            Marker marker = new Marker(mapView);
            GeoPoint position = new GeoPoint(location.getLatitude(), location.getLongitude());
            marker.setPosition(position);
            marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
            marker.setTitle(location.getName());
            marker.setSnippet(location.getCategoryName());
            marker.setIcon(ContextCompat.getDrawable(this, android.R.drawable.ic_menu_mylocation));

            markerLocationMap.put(marker, location);

            marker.setOnMarkerClickListener((m, mapView) -> {
                // Restaurar el color del marcador anterior
                if (selectedMarker != null && selectedMarker != m) {
                    selectedMarker.setIcon(ContextCompat.getDrawable(this, android.R.drawable.ic_menu_mylocation));
                }

                // Cambiar color del marcador actual (amarillo/destacado)
                m.setIcon(ContextCompat.getDrawable(this, android.R.drawable.star_big_on));
                selectedMarker = m;

                // Obtener la localización asociada al marcador
                Location loc = markerLocationMap.get(m);
                if (loc != null) {
                    showPlaceDetailsBottomSheet(loc);
                }

                mapView.invalidate();
                return true;
            });

            mapView.getOverlays().add(marker);
        }
    }

    private void showFiltersDialog() {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        android.view.View dialogView = getLayoutInflater().inflate(R.layout.dialog_filters, null);
        builder.setView(dialogView);

        // Referencias a las vistas
        android.widget.LinearLayout categoriesHeader = dialogView.findViewById(R.id.categories_header);
        android.widget.ImageView expandIcon = dialogView.findViewById(R.id.categories_expand_icon);
        android.widget.LinearLayout categoriesContainer = dialogView.findViewById(R.id.categories_container);
        android.widget.LinearLayout categoriesCheckboxes = dialogView.findViewById(R.id.categories_checkboxes);
        android.widget.TextView selectedCount = dialogView.findViewById(R.id.selected_count);
        com.google.android.material.button.MaterialButton btnApply = dialogView.findViewById(R.id.btn_apply_filters);
        com.google.android.material.button.MaterialButton btnClear = dialogView.findViewById(R.id.btn_clear_filters);

        // Cargar categorías desde la base de datos
        List<android.util.Pair<Long, String>> categories = loadCategoriesFromDatabase();
        Map<Long, android.widget.CheckBox> categoryCheckboxes = new HashMap<>();

        // Crear checkboxes dinámicamente
        for (android.util.Pair<Long, String> category : categories) {
            android.widget.CheckBox checkBox = new android.widget.CheckBox(this);
            checkBox.setText(category.second);
            checkBox.setTextSize(16);
            checkBox.setPadding(16, 12, 16, 12);

            // Marcar si ya estaba seleccionado previamente
            checkBox.setChecked(selectedCategoryIds.contains(category.first));

            // Listener para actualizar el contador
            checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> updateSelectedCount(categoryCheckboxes, selectedCount));

            categoryCheckboxes.put(category.first, checkBox);
            categoriesCheckboxes.addView(checkBox);
        }

        // Actualizar el contador inicial
        updateSelectedCount(categoryCheckboxes, selectedCount);

        // Toggle colapsar/expandir categorías
        final boolean[] isExpanded = {false};
        categoriesHeader.setOnClickListener(v -> {
            isExpanded[0] = !isExpanded[0];
            if (isExpanded[0]) {
                categoriesContainer.setVisibility(android.view.View.VISIBLE);
                expandIcon.setRotation(180);
            } else {
                categoriesContainer.setVisibility(android.view.View.GONE);
                expandIcon.setRotation(0);
            }
        });

        android.app.AlertDialog dialog = builder.create();

        // Botón Aplicar
        btnApply.setOnClickListener(v -> {
            selectedCategoryIds.clear();
            for (Map.Entry<Long, android.widget.CheckBox> entry : categoryCheckboxes.entrySet()) {
                if (entry.getValue().isChecked()) {
                    selectedCategoryIds.add(entry.getKey());
                }
            }

            // Aplicar filtros al mapa
            applyFilters(selectedCategoryIds);
            dialog.dismiss();
        });

        // Botón Limpiar
        btnClear.setOnClickListener(v -> {
            for (android.widget.CheckBox checkBox : categoryCheckboxes.values()) {
                checkBox.setChecked(false);
            }
            updateSelectedCount(categoryCheckboxes, selectedCount);

            // Limpiar filtros y mostrar todos los lugares
            selectedCategoryIds.clear();
            applyFilters(new ArrayList<>());
        });

        dialog.show();
    }

    private List<android.util.Pair<Long, String>> loadCategoriesFromDatabase() {
        List<android.util.Pair<Long, String>> categories = new ArrayList<>();
        Cursor cursor = dbHelper.getAllCategories();

        if (cursor != null && cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_CATEGORY_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_CATEGORY_NAME));
                categories.add(new android.util.Pair<>(id, name));
            } while (cursor.moveToNext());
            cursor.close();
        }

        return categories;
    }

    private void updateSelectedCount(Map<Long, android.widget.CheckBox> checkboxes, android.widget.TextView countView) {
        int count = 0;
        for (android.widget.CheckBox checkBox : checkboxes.values()) {
            if (checkBox.isChecked()) {
                count++;
            }
        }
        countView.setText(count + " seleccionada" + (count != 1 ? "s" : ""));
    }

    private void applyFilters(List<Long> selectedCategoryIds) {
        // Limpiar marcadores actuales
        mapView.getOverlays().clear();
        markerLocationMap.clear();

        // Obtener localizaciones filtradas
        List<Location> filteredLocations;
        if (selectedCategoryIds.isEmpty()) {
            // Si no hay filtros, mostrar todos
            filteredLocations = getLocationsFromDB();
        } else {
            // Filtrar por categorías seleccionadas
            filteredLocations = getLocationsByCategories(selectedCategoryIds);
        }

        // Agregar marcadores filtrados
        addMarkersToMap(filteredLocations);
        mapView.invalidate();

        String message = selectedCategoryIds.isEmpty()
            ? "Mostrando todos los lugares"
            : "Filtros aplicados: " + filteredLocations.size() + " lugares encontrados";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private List<Location> getLocationsByCategories(List<Long> categoryIds) {
        List<Location> locations = new ArrayList<>();
        for (Long categoryId : categoryIds) {
            Cursor cursor = dbHelper.getLocationsByCategory(categoryId);
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    Location loc = new Location();
                    loc.setLocationId(cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCATION_ID)));
                    loc.setName(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCATION_NAME)));
                    loc.setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCATION_DESCRIPTION)));
                    loc.setLatitude(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCATION_LATITUDE)));
                    loc.setLongitude(cursor.getDouble(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCATION_LONGITUDE)));
                    loc.setPopularityScore(cursor.getFloat(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCATION_POPULARITY_SCORE)));

                    int categoryIndex = cursor.getColumnIndex("category_name");
                    if (categoryIndex != -1) {
                        loc.setCategoryName(cursor.getString(categoryIndex));
                    }

                    locations.add(loc);
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        return locations;
    }

    private void showPlaceDetailsBottomSheet(Location location) {
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

        com.google.android.material.button.MaterialButton btnSave = sheetView.findViewById(R.id.btn_save);
        com.google.android.material.button.MaterialButton btnShare = sheetView.findViewById(R.id.btn_share);

        // Establecer datos desde la base de datos
        placeName.setText(location.getName());
        placeCategory.setText(location.getCategoryName() != null ? location.getCategoryName() : "Sin categoría");
        placeDescription.setText(location.getDescription());
        placeRating.setRating(location.getPopularityScore());
        ratingValue.setText(String.format("%.1f", location.getPopularityScore()));

        // Obtener información de sesión
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        boolean isGuest = prefs.getBoolean(KEY_IS_GUEST, true);
        long userId = prefs.getLong(KEY_USER_ID, -1);

        // Verificar si ya está en favoritos
        boolean isFavorite = !isGuest && dbHelper.isFavorite(userId, location.getLocationId());

        // Cambiar texto del botón según el estado
        if (isFavorite) {
            btnSave.setText("Eliminar de favoritos");
        } else {
            btnSave.setText("Añadir a favoritos");
        }

        // Listeners de botones
        btnSave.setOnClickListener(v -> {
            if (isGuest) {
                Toast.makeText(this, "Inicia sesión para guardar favoritos", Toast.LENGTH_LONG).show();
                return;
            }

            boolean currentlyFavorite = dbHelper.isFavorite(userId, location.getLocationId());

            if (currentlyFavorite) {
                // Eliminar de favoritos
                int result = dbHelper.removeFavorite(userId, location.getLocationId());
                if (result > 0) {
                    Toast.makeText(this, "Eliminado de favoritos", Toast.LENGTH_SHORT).show();
                    btnSave.setText("Añadir a favoritos");
                } else {
                    Toast.makeText(this, "Error al eliminar de favoritos", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Añadir a favoritos
                long result = dbHelper.addFavorite(userId, location.getLocationId(), null);
                if (result != -1) {
                    Toast.makeText(this, "Añadido a favoritos", Toast.LENGTH_SHORT).show();
                    btnSave.setText("Eliminar de favoritos");
                } else {
                    Toast.makeText(this, "Error al añadir a favoritos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShare.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            String shareBody = "¡Mira este lugar en EXPLORA! " + location.getName();
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "EXPLORA - " + location.getName());
            shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(shareIntent, "Compartir lugar"));
        });

        bottomSheet.setContentView(sheetView);

        // Configurar comportamiento del bottom sheet
        com.google.android.material.bottomsheet.BottomSheetBehavior<?> behavior = bottomSheet.getBehavior();
        if (behavior != null) {
            behavior.setPeekHeight(720);
            behavior.setState(com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED);
            behavior.setSkipCollapsed(false);
            behavior.setHideable(true);

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
            if (selectedMarker != null) {
                selectedMarker.setIcon(ContextCompat.getDrawable(this, android.R.drawable.ic_menu_mylocation));
                mapView.invalidate();
                selectedMarker = null;
            }

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