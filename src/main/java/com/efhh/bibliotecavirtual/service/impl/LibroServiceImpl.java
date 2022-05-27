package com.efhh.bibliotecavirtual.service.impl;

import com.efhh.bibliotecavirtual.model.Libro;
import com.efhh.bibliotecavirtual.repository.ILibroRepository;
import com.efhh.bibliotecavirtual.service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements ILibroService {

    @Autowired
    private ILibroRepository libroRepository;
    @Override
    public Libro registrar(Libro libro) throws Exception {
        return libroRepository.save(libro);
    }

    @Override
    public Libro modificar(Libro libro) throws Exception {
        return libroRepository.save(libro);
    }

    @Override
    public List<Libro> listar() throws Exception {
        return libroRepository.findAll();
    }

    @Override
    public Optional<Libro> listarPorIdOptional(Integer id) {
        Optional<Libro> op= libroRepository.findById(id);
        if(op.isPresent()){
            return Optional.empty();
        }
        return op;
    }

    @Override
    public Page<Libro> listarPageable(Pageable pageable) {
        return libroRepository.findAll(pageable);
    }

    @Override
    public Libro listarPorId(Integer id) throws Exception {
        Optional<Libro> op=libroRepository.findById(id);
        return op.isPresent()? op.get() : new Libro();
    }

    @Override
    public Libro eliminar(Integer id) throws Exception {
        libroRepository.deleteById(id);
        return null;
    }
}
