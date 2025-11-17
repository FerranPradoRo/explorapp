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

public class RegisterFragment extends Fragment {

    private TextInputLayout tilNombre, tilApellido, tilEmail, tilPassword, tilConfirmPassword, tilPais;
    private TextInputEditText etNombre, etApellido, etEmail, etPassword, etConfirmPassword, etPais;
    private MaterialButton btnRegister;
    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        dbHelper = new DatabaseHelper(requireContext());
        initializeViews(view);
        setupListeners();

        return view;
    }

    private void initializeViews(View view) {
        tilNombre = view.findViewById(R.id.til_nombre);
        tilApellido = view.findViewById(R.id.til_apellido);
        tilEmail = view.findViewById(R.id.til_email);
        tilPassword = view.findViewById(R.id.til_password);
        tilConfirmPassword = view.findViewById(R.id.til_confirm_password);
        tilPais = view.findViewById(R.id.til_pais);

        etNombre = view.findViewById(R.id.et_nombre);
        etApellido = view.findViewById(R.id.et_apellido);
        etEmail = view.findViewById(R.id.et_email);
        etPassword = view.findViewById(R.id.et_password);
        etConfirmPassword = view.findViewById(R.id.et_confirm_password);
        etPais = view.findViewById(R.id.et_pais);

        btnRegister = view.findViewById(R.id.btn_register);
    }

    private void setupListeners() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptRegister();
            }
        });
    }

    private void attemptRegister() {
        clearErrors();

        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();
        String confirmPassword = etConfirmPassword.getText().toString();
        String pais = etPais.getText().toString().trim();

        if (TextUtils.isEmpty(nombre)) {
            tilNombre.setError("Ingresa tu nombre");
            etNombre.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(apellido)) {
            tilApellido.setError("Ingresa tu apellido");
            etApellido.requestFocus();
            return;
        }

        if (!validateEmail(email)) {
            tilEmail.setError("Ingresa un correo electrónico válido");
            etEmail.requestFocus();
            return;
        }

        if (!validatePassword(password)) {
            tilPassword.setError("La contraseña debe tener al menos 6 caracteres");
            etPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            tilConfirmPassword.setError("Las contraseñas no coinciden");
            etConfirmPassword.requestFocus();
            return;
        }

        if (emailExists(email)) {
            tilEmail.setError("Este correo ya está registrado");
            etEmail.requestFocus();
            return;
        }

        String passwordHash = LoginFragment.hashPassword(password);
        long userId = dbHelper.insertUser(nombre, apellido, email, passwordHash,
                pais.isEmpty() ? null : pais, null);

        if (userId != -1) {
            Toast.makeText(requireContext(), "Cuenta creada exitosamente!", Toast.LENGTH_SHORT).show();
            ((LoginActivity) requireActivity()).saveUserSession(userId);
        } else {
            Toast.makeText(requireContext(), "Error al crear la cuenta. Intenta de nuevo.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean validatePassword(String password) {
        return !TextUtils.isEmpty(password) && password.length() >= 6;
    }

    private boolean emailExists(String email) {
        Cursor cursor = dbHelper.findUserByEmail(email);
        boolean exists = cursor != null && cursor.getCount() > 0;
        if (cursor != null) {
            cursor.close();
        }
        return exists;
    }

    private void clearErrors() {
        tilNombre.setError(null);
        tilApellido.setError(null);
        tilEmail.setError(null);
        tilPassword.setError(null);
        tilConfirmPassword.setError(null);
        tilPais.setError(null);
    }
}
