package com.example.explorapp;

import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MapaActivity extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_mapa);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mapa), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        mapView = findViewById(R.id.mapView);


        configurarMapa();
    }

    private void configurarMapa() {

        mapView.setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK);


        mapView.setMultiTouchControls(true);


        mapView.getController().setZoom(15.0);


        mapView.setMinZoomLevel(5.0);
        mapView.setMaxZoomLevel(19.0);


        GeoPoint puntoInicial = new GeoPoint(20.6597, -103.3496);
        mapView.getController().setCenter(puntoInicial);

        agregarMarcador(puntoInicial, "Guadalajara", "¡Estás aquí!");
    }

    private void agregarMarcador(GeoPoint punto, String titulo, String descripcion) {
        Marker marcador = new Marker(mapView);
        marcador.setPosition(punto);
        marcador.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marcador.setTitle(titulo);
        marcador.setSnippet(descripcion);
        mapView.getOverlays().add(marcador);
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