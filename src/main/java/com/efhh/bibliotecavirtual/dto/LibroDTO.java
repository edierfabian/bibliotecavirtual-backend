package com.efhh.bibliotecavirtual.dto;

import javax.persistence.Column;
import javax.validation.constraints.Size;

public class LibroDTO {

    @Size(min = 3,message = "Titulo debe tener minimo 3 caracteres")
    private String titulo;

    @Size(min = 3,message = "Slug debe tener minimo 3 caracteres")
    private String slug;

    @Size(min = 3,message = "Descripcion debe tener minimo 3 caracteres")
    private String descripcion;

    private String rutaPortada;

    private String rutaArchivo;

    private Float precio;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaPortada() {
        return rutaPortada;
    }

    public void setRutaPortada(String rutaPortada) {
        this.rutaPortada = rutaPortada;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}
