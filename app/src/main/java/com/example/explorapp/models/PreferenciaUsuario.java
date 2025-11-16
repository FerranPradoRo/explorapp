package com.example.explorapp.models;

public class PreferenciaUsuario {
    private long preferenciaId;
    private long usuarioId;
    private long categoriaId;
    private int nivelInteres;

    private String categoriaNombre;
    private String categoriaIcono;
    private String categoriaColor;

    public PreferenciaUsuario() {
    }

    public PreferenciaUsuario(long preferenciaId, long usuarioId, long categoriaId,
                              int nivelInteres) {
        this.preferenciaId = preferenciaId;
        this.usuarioId = usuarioId;
        this.categoriaId = categoriaId;
        this.nivelInteres = nivelInteres;
    }

    public long getPreferenciaId() {
        return preferenciaId;
    }

    public void setPreferenciaId(long preferenciaId) {
        this.preferenciaId = preferenciaId;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public int getNivelInteres() {
        return nivelInteres;
    }

    public void setNivelInteres(int nivelInteres) {
        this.nivelInteres = nivelInteres;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public String getCategoriaIcono() {
        return categoriaIcono;
    }

    public void setCategoriaIcono(String categoriaIcono) {
        this.categoriaIcono = categoriaIcono;
    }

    public String getCategoriaColor() {
        return categoriaColor;
    }

    public void setCategoriaColor(String categoriaColor) {
        this.categoriaColor = categoriaColor;
    }
}
