package com.example.explorapp.models;

public class ImagenLocalizacion {
    private long imagenId;
    private long localizacionId;
    private String urlImagen;
    private boolean esPrincipal;
    private int orden;
    private long fechaSubida;

    public ImagenLocalizacion() {
        this.esPrincipal = false;
        this.orden = 0;
    }

    public ImagenLocalizacion(long imagenId, long localizacionId, String urlImagen,
                              boolean esPrincipal, int orden, long fechaSubida) {
        this.imagenId = imagenId;
        this.localizacionId = localizacionId;
        this.urlImagen = urlImagen;
        this.esPrincipal = esPrincipal;
        this.orden = orden;
        this.fechaSubida = fechaSubida;
    }

    public long getImagenId() {
        return imagenId;
    }

    public void setImagenId(long imagenId) {
        this.imagenId = imagenId;
    }

    public long getLocalizacionId() {
        return localizacionId;
    }

    public void setLocalizacionId(long localizacionId) {
        this.localizacionId = localizacionId;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public boolean isEsPrincipal() {
        return esPrincipal;
    }

    public void setEsPrincipal(boolean esPrincipal) {
        this.esPrincipal = esPrincipal;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public long getFechaSubida() {
        return fechaSubida;
    }

    public void setFechaSubida(long fechaSubida) {
        this.fechaSubida = fechaSubida;
    }
}
