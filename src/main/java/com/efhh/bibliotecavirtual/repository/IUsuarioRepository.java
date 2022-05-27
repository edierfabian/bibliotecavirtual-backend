package com.efhh.bibliotecavirtual.repository;

import com.efhh.bibliotecavirtual.model.Libro;
import com.efhh.bibliotecavirtual.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IUsuarioRepository extends JpaRepository<Usuario,Integer> {

}
