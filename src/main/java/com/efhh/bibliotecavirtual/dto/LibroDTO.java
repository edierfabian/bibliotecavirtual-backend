package com.efhh.bibliotecavirtual.dto;

import javax.persistence.Column;
import javax.validation.constraints.*;

public class LibroDTO {

    @Size(min = 3, max = 100, message = "El título debe tener {min} caracteres como mínimo y {max} caracteres como máximo")
    @NotNull(message = "El título es obligatorio")
    private String titulo;

    @NotNull(message = "El slug es obligatorio")
    @Pattern(regexp = "[a-z0-9-]+", message = "El slug debe tener un formato válido")
    private String slug;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;
    @NotBlank(message = "La portada es obligatoria")
    private String rutaPortada;

    @NotBlank(message = "El Archivo es obligatoria")
    private String rutaArchivo;
    @NotNull(message = "El precio es obligatorio")
    @PositiveOrZero(message = "El precio debe ser mayor o igual a 0")
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
