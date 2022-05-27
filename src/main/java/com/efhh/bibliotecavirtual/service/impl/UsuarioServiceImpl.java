package com.efhh.bibliotecavirtual.service.impl;

import com.efhh.bibliotecavirtual.model.Libro;
import com.efhh.bibliotecavirtual.model.Usuario;
import com.efhh.bibliotecavirtual.repository.ILibroRepository;
import com.efhh.bibliotecavirtual.repository.IUsuarioRepository;
import com.efhh.bibliotecavirtual.service.ILibroService;
import com.efhh.bibliotecavirtual.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository  usuarioRepository;


    @Override
    public Usuario registrarUsuario(Usuario usuario) throws Exception {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario modificarUsuario(Usuario usuario) throws Exception {
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> listarUsuario() throws Exception {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> listarPorIdOptional(Integer id) {
        Optional<Usuario> listarUsuarioPorIdOptional= usuarioRepository.findById(id);
        if(listarUsuarioPorIdOptional.isPresent()){
            return Optional.empty();
        }
        return listarUsuarioPorIdOptional;


    }

    @Override
    public Page<Usuario> listarPageable(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Override
    public Usuario listarUsuarioPorId(Integer id) throws Exception {
        Optional<Usuario> op=usuarioRepository.findById(id);
        return op.isPresent()? op.get() : new Usuario();
    }

    @Override
    public void eliminarUsuario(Integer id) throws Exception {
        usuarioRepository.deleteById(id);
    }
}
