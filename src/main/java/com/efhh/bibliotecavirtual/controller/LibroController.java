package com.efhh.bibliotecavirtual.controller;

import com.efhh.bibliotecavirtual.exception.ModeloNotFoundException;
import com.efhh.bibliotecavirtual.model.Libro;
import com.efhh.bibliotecavirtual.service.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {

    @Autowired
    private ILibroService libroService;

    @GetMapping
    public ResponseEntity<List<Libro>> listar() throws Exception {
        List<Libro> listaLibro= libroService.listar();
        return new ResponseEntity<List<Libro>>(listaLibro, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> listarPorID(@PathVariable("id")Integer id) throws Exception {
        Libro libroPorId=libroService.listarPorId(id);
        if(libroPorId.getIdLibro()==null){
            throw new ModeloNotFoundException("Id No Encontrado: "+id);
        }
        return new ResponseEntity<Libro>(libroPorId,HttpStatus.OK);
    }
  /*  @PostMapping
    public ResponseEntity<Libro>  registrar(@Valid @RequestBody Libro libro) throws Exception {
        Libro libroRegistrar=libroService.registrar(libro);

        return  new ResponseEntity<Libro>(libroRegistrar,HttpStatus.CREATED);
    }*/

    @PostMapping
    public ResponseEntity<Libro>  registrar(@Valid @RequestBody Libro libro) throws Exception {

        Libro libroRegistrar=libroService.registrar(libro);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(libroRegistrar.getIdLibro()).toUri();

        return   ResponseEntity.created(location).build();
    }
    @PutMapping
    public ResponseEntity<Libro> modificar(@Valid @RequestBody Libro libro) throws Exception {
        Libro libroModificar=libroService.registrar(libro);

        return  new ResponseEntity<Libro>(libroModificar,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> eliminar(@PathVariable("id")Integer id) throws Exception {

        Libro libroEliminado=libroService.listarPorId(id);
        if(libroEliminado.getIdLibro()==null){
            throw new ModeloNotFoundException("Id No Encontrado: "+id);
        }
        libroService.eliminar(id);
        return  new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


}
