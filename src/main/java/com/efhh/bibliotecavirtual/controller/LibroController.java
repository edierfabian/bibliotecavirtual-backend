package com.efhh.bibliotecavirtual.controller;

import com.efhh.bibliotecavirtual.model.Libro;
import com.efhh.bibliotecavirtual.service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private ILibroService libroService;

    @GetMapping
    public List<Libro> listar() throws Exception {
        return libroService.listar();
    }

    @GetMapping("/{id}")
    public Libro listarPorID(@PathVariable("id")Integer id) throws Exception {
        return libroService.listarPorId(id);
    }
    @PostMapping
    public Libro registrar(@RequestBody Libro libro) throws Exception {
        return libroService.registrar(libro);
    }
    @PutMapping
    public Libro modificar(@RequestBody Libro libro) throws Exception {
        return libroService.modificar(libro);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id")Integer id) throws Exception {
        libroService.eliminar(id);
    }


}
