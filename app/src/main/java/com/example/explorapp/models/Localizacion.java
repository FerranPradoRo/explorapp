package com.example.explorapp.models;

/**
 * Modelo de datos para la tabla localizaciones
 */
public class Localizacion {
    private long localizacionId;
    private String nombre;
    private String descripcion;
    private double latitud;
    private double longitud;
    private String direccion;
    private String ciudad;
    private Long categoriaId;
    private String categoriaNombre; // Campo extra del JOIN
    private double costoPromedio;
    private String horarioApertura;
    private String horarioCierre;
    private String telefono;
    private String sitioWeb;
    private float popularidadScore;
    private long fechaCreacion;
    private boolean activo;

    // Constructor vacío
    public Localizacion() {
        this.activo = true;
    }

    // Constructor completo
    public Localizacion(long localizacionId, String nombre, String descripcion,
                        double latitud, double longitud, String direccion, String ciudad,
                        Long categoriaId, double costoPromedio, String horarioApertura,
                        String horarioCierre, String telefono, String sitioWeb,
                        float popularidadScore, long fechaCreacion, boolean activo) {
        this.localizacionId = localizacionId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.categoriaId = categoriaId;
        this.costoPromedio = costoPromedio;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
        this.telefono = telefono;
        this.sitioWeb = sitioWeb;
        this.popularidadScore = popularidadScore;
        this.fechaCreacion = fechaCreacion;
        this.activo = activo;
    }

    // Getters y Setters
    public long getLocalizacionId() {
        return localizacionId;
    }

    public void setLocalizacionId(long localizacionId) {
        this.localizacionId = localizacionId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    public double getCostoPromedio() {
        return costoPromedio;
    }

    public void setCostoPromedio(double costoPromedio) {
        this.costoPromedio = costoPromedio;
    }

    public String getHorarioApertura() {
        return horarioApertura;
    }

    public void setHorarioApertura(String horarioApertura) {
        this.horarioApertura = horarioApertura;
    }

    public String getHorarioCierre() {
        return horarioCierre;
    }

    public void setHorarioCierre(String horarioCierre) {
        this.horarioCierre = horarioCierre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public float getPopularidadScore() {
        return popularidadScore;
    }

    public void setPopularidadScore(float popularidadScore) {
        this.popularidadScore = popularidadScore;
    }

    public long getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(long fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getHorario() {
        if (horarioApertura != null && horarioCierre != null) {
            return horarioApertura + " - " + horarioCierre;
        }
        return "No disponible";
    }
}
