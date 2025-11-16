package com.example.explorapp.models;

/**
 * Modelo de datos para la tabla favoritos
 */
public class Favorito {
    private long favoritoId;
    private long usuarioId;
    private long localizacionId;
    private long fechaAgregado;
    private String notasPersonales;

    // Objeto Localizacion completo del JOIN
    private Localizacion localizacion;

    // Constructor vacío
    public Favorito() {
    }

    // Constructor completo
    public Favorito(long favoritoId, long usuarioId, long localizacionId,
                    long fechaAgregado, String notasPersonales) {
        this.favoritoId = favoritoId;
        this.usuarioId = usuarioId;
        this.localizacionId = localizacionId;
        this.fechaAgregado = fechaAgregado;
        this.notasPersonales = notasPersonales;
    }

    // Getters y Setters
    public long getFavoritoId() {
        return favoritoId;
    }

    public void setFavoritoId(long favoritoId) {
        this.favoritoId = favoritoId;
    }

    public long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public long getLocalizacionId() {
        return localizacionId;
    }

    public void setLocalizacionId(long localizacionId) {
        this.localizacionId = localizacionId;
    }

    public long getFechaAgregado() {
        return fechaAgregado;
    }

    public void setFechaAgregado(long fechaAgregado) {
        this.fechaAgregado = fechaAgregado;
    }

    public String getNotasPersonales() {
        return notasPersonales;
    }

    public void setNotasPersonales(String notasPersonales) {
        this.notasPersonales = notasPersonales;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
    }
}
