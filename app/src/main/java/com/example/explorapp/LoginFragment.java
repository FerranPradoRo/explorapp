package com.example.explorapp;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginFragment extends Fragment {

    private TextInputLayout tilEmail, tilPassword;
    private TextInputEditText etEmail, etPassword;
    private MaterialButton btnLogin;
    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        dbHelper = new DatabaseHelper(requireContext());
        initializeViews(view);
        setupListeners();

        return view;
    }

    private void initializeViews(View view) {
        tilEmail = view.findViewById(R.id.til_email);
        tilPassword = view.findViewById(R.id.til_password);
        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        btnLogin = view.findViewById(R.id.btn_login);
    }

    private void setupListeners() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });
    }

    private void attemptLogin() {
        tilEmail.setError(null);
        tilPassword.setError(null);

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();

        if (!validateEmail(email)) {
            tilEmail.setError("Ingresa un correo electrónico válido");
            etEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            tilPassword.setError("Ingresa tu contraseña");
            etPassword.requestFocus();
            return;
        }

        Cursor cursor = dbHelper.buscarUsuarioPorEmail(email);

        if (cursor != null && cursor.moveToFirst()) {
            long userId = cursor.getLong(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USUARIO_ID));
            String storedPasswordHash = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USUARIO_PASSWORD_HASH));
            boolean activo = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_USUARIO_ACTIVO)) == 1;

            cursor.close();

            if (!activo) {
                Toast.makeText(requireContext(), "Esta cuenta está desactivada", Toast.LENGTH_SHORT).show();
                return;
            }

            String passwordHash = hashPassword(password);
            if (passwordHash.equals(storedPasswordHash)) {
                Toast.makeText(requireContext(), "Bienvenido!", Toast.LENGTH_SHORT).show();
                ((LoginActivity) requireActivity()).saveUserSession(userId);
            } else {
                tilPassword.setError("Contraseña incorrecta");
            }
        } else {
            if (cursor != null) {
                cursor.close();
            }
            tilEmail.setError("No existe una cuenta con este correo");
        }
    }

    private boolean validateEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }
}
