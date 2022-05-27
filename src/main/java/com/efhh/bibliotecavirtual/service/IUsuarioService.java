package com.efhh.bibliotecavirtual.service;

import com.efhh.bibliotecavirtual.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    Usuario registrarUsuario(Usuario usuario) throws Exception;
    Usuario modificarUsuario(Usuario usuario) throws Exception;
    List<Usuario> listarUsuario() throws Exception;
    Optional<Usuario> listarPorIdOptional(Integer id) ;
    Page<Usuario> listarPageable(Pageable pageable);

    Usuario listarUsuarioPorId(Integer id) throws Exception;
    void eliminarUsuario(Integer id) throws Exception;
}
