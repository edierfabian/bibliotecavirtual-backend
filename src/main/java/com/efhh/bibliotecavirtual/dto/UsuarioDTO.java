package com.efhh.bibliotecavirtual.dto;


import com.efhh.bibliotecavirtual.model.Rol;
import com.efhh.bibliotecavirtual.model.Usuario;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


public class UsuarioDTO {

    private String nombre;
    private String apellidos;
    private String email;
    private String password;

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
