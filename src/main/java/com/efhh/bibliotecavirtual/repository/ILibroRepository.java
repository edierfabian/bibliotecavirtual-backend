package com.efhh.bibliotecavirtual.repository;

import com.efhh.bibliotecavirtual.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ILibroRepository extends JpaRepository<Libro,Integer> {

}
