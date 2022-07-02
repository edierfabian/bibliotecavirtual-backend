package com.efhh.bibliotecavirtual.dto;


import com.efhh.bibliotecavirtual.model.Rol;
import com.efhh.bibliotecavirtual.model.Usuario;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class UsuarioDTO {

    @NotNull(message = "El Nombre es obligatorio")
    private String nombre;
    @NotNull(message = "El título es obligatorio")
    private String apellidos;
    @NotNull(message = "El título es obligatorio")
    @Pattern(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "El Email debe tener un formato válido")
    @Email(message = "Error de formato de correo electrónico")
    private String email;

    @NotNull(message = "El Password es obligatorio")
    @Size(min = 8, max = 16, message = "El password debe tener {min} caracteres como mínimo y {max} caracteres como máximo")
    private String password;
    @NotNull(message = "El Rol es obligatorio")
    @Enumerated(EnumType.STRING)
    private Rol rol;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
