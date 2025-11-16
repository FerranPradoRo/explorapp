package com.example.explorapp.models;

public class Review {
    private long reviewId;
    private long usuarioId;
    private long localizacionId;
    private int calificacion;
    private String comentario;
    private long fechaVisita;
    private long fechaReview;
    private int likes;
    private boolean verificado;

    private String usuarioNombre;
    private String usuarioApellido;
    private String usuarioFotoPerfil;

    public Review() {
        this.likes = 0;
        this.verificado = false;
    }

    public Review(long reviewId, long usuarioId, long localizacionId, int calificacion,
                  String comentario, long fechaVisita, long fechaReview, int likes,
                  boolean verificado) {
        this.reviewId = reviewId;
        this.usuarioId = usuarioId;
        this.localizacionId = localizacionId;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fechaVisita = fechaVisita;
        this.fechaReview = fechaReview;
        this.likes = likes;
        this.verificado = verificado;
    }

    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
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

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public long getFechaVisita() {
        return fechaVisita;
    }

    public void setFechaVisita(long fechaVisita) {
        this.fechaVisita = fechaVisita;
    }

    public long getFechaReview() {
        return fechaReview;
    }

    public void setFechaReview(long fechaReview) {
        this.fechaReview = fechaReview;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }

    public String getUsuarioApellido() {
        return usuarioApellido;
    }

    public void setUsuarioApellido(String usuarioApellido) {
        this.usuarioApellido = usuarioApellido;
    }

    public String getUsuarioFotoPerfil() {
        return usuarioFotoPerfil;
    }

    public void setUsuarioFotoPerfil(String usuarioFotoPerfil) {
        this.usuarioFotoPerfil = usuarioFotoPerfil;
    }

    public String getUsuarioNombreCompleto() {
        return usuarioNombre + " " + usuarioApellido;
    }
}
