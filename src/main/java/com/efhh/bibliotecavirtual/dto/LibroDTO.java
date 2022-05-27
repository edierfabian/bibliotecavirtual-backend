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
}
