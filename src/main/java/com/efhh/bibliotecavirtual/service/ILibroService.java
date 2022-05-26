package com.efhh.bibliotecavirtual.service;

import com.efhh.bibliotecavirtual.model.Libro;

import java.util.List;
import java.util.Optional;

public interface ILibroService {

    Libro registrar(Libro libro) throws Exception;
    Libro modificar(Libro libro) throws Exception;
    List<Libro> listar() throws Exception;
    Optional<Libro> listarPorIdOptional(Integer id) ;

    Libro listarPorId(Integer id) throws Exception;
    Libro eliminar(Integer id) throws Exception;
}
