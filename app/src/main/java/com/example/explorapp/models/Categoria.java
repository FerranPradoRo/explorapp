package com.example.explorapp.models;

/**
 * Modelo de datos para la tabla categorias
 */
public class Categoria {
    private long categoriaId;
    private String nombre;
    private String descripcion;
    private String icono;
    private String color;

    // Constructor vacío
    public Categoria() {
    }

    // Constructor completo
    public Categoria(long categoriaId, String nombre, String descripcion,
                     String icono, String color) {
        this.categoriaId = categoriaId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.icono = icono;
        this.color = color;
    }

    // Getters y Setters
    public long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(long categoriaId) {
        this.categoriaId = categoriaId;
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

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
