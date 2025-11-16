package com.example.explorapp.models;

public class Usuario {
    private long usuarioId;
    private String nombre;
    private String apellido;
    private String email;
    private String passwordHash;
    private long fechaRegistro;
    private String paisOrigen;
    private String fotoPerfil;
    private boolean activo;

    public Usuario() {
        this.activo = true;
    }

    public Usuario(long usuarioId, String nombre, String apellido, String email,
                   String passwordHash, long fechaRegistro, String paisOrigen,
                   String fotoPerfil, boolean activo) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.passwordHash = passwordHash;
        this.fechaRegistro = fechaRegistro;
        this.paisOrigen = paisOrigen;
        this.fotoPerfil = fotoPerfil;
        this.activo = activo;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public long getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(long fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}
