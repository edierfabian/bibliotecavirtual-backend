package com.efhh.bibliotecavirtual.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLibro;

    @Size(min = 3,message = "Titulo debe tener minimo 3 caracteres")
    @Column(name="titulo",nullable = false,length = 100)
    private String titulo;
    @Size(min = 3,message = "Slug debe tener minimo 3 caracteres")
    @Column(name="slug",nullable = false,length = 100)
    private String slug;
    @Size(min = 3,message = "Descripcion debe tener minimo 3 caracteres")
    @Column(name="descripcion",nullable = false,length = 200)
    private String descripcion;

    @Column(name="precio",nullable = false)
    private Float precio;
    @Column(name="fechaCreacion")
    private LocalDateTime fechaCreacion;


    public Integer getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
        this.idLibro = idLibro;
    }

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

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @PrePersist
    private void asignarFechaCreacion() {
        fechaCreacion = LocalDateTime.now();
    }

}
