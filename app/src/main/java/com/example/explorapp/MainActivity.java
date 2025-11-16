package com.example.explorapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final long SPLASH_MIN_DURATION = 3000; // Tiempo minimo de pantalla de inicio

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeDatabaseAndNavigate();
    }

    private void initializeDatabaseAndNavigate() {
        final long startTime = System.currentTimeMillis();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d(TAG, "Iniciando inicialización de base de datos...");
                    DataSeeder seeder = new DataSeeder(MainActivity.this);
                    seeder.seedDatabase();

                    Log.d(TAG, "Base de datos inicializada correctamente");
                } catch (Exception e) {
                    Log.e(TAG, "Error al inicializar base de datos", e);
                }

                long elapsedTime = System.currentTimeMillis() - startTime;
                long remainingTime = SPLASH_MIN_DURATION - elapsedTime;

                if (remainingTime > 0) {
                    try {
                        Thread.sleep(remainingTime);
                    } catch (InterruptedException e) {
                        Log.e(TAG, "Error en sleep", e);
                    }
                }

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        navigateToLogin();
                    }
                });
            }
        }).start();
    }

    private void navigateToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
