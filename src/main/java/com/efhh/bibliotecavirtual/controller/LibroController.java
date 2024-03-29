package com.efhh.bibliotecavirtual.controller;

import com.efhh.bibliotecavirtual.dto.LibroDTO;
import com.efhh.bibliotecavirtual.exception.ModeloNotFoundException;
import com.efhh.bibliotecavirtual.exception.RestExceptionHandler;
import com.efhh.bibliotecavirtual.model.Libro;
import com.efhh.bibliotecavirtual.service.ILibroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public ResponseEntity<Libro> listarPorId(@PathVariable("id")Integer id) throws Exception {
        Libro libroPorId=libroService.listarPorId(id);
        if(libroPorId.getIdLibro()==null){
            throw new ModeloNotFoundException("Id No Encontradodd: "+id);
        }
        return new ResponseEntity<Libro>(libroPorId,HttpStatus.OK);
    }

  /*@PostMapping
    public ResponseEntity<Libro>  registrar(@Valid @RequestBody Libro libro) throws Exception {
        Libro libroRegistrar=libroService.registrar(libro);

        return  new ResponseEntity<Libro>(libroRegistrar,HttpStatus.CREATED);
    }*/
  /*@ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  Libro crear(@Validated @RequestBody LibroDTO libroDTO) throws RestExceptionHandler {
      Libro libro = new ModelMapper().map(libroDTO, Libro.class);
      Libro registrar = libroService.registrar(libro);
      return registrar;
  }*/


    @GetMapping("/hateos/{id}")
    public EntityModel<Libro> listarPorIdHateos(@PathVariable("id") Integer id) throws Exception{
        Libro listarPorIdHateos=libroService.listarPorId(id);
        if(listarPorIdHateos.getIdLibro()==null){
            throw new ModeloNotFoundException("Id No Encontrado: "+id);
        }
        EntityModel<Libro> recursoLibroHateos=EntityModel.of(listarPorIdHateos);
        WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).listarPorId(id));
        recursoLibroHateos.add(link.withRel("libro-recurso"));
        return recursoLibroHateos;

    }

    @PostMapping
    public ResponseEntity<LibroDTO>  registrarLibroModelMapper(@Validated @RequestBody LibroDTO libroDTO) throws RestExceptionHandler {

        Libro libro=new ModelMapper().map(libroDTO,Libro.class);
        Libro libroRegistrar=libroService.registrar(libro);

        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(libroRegistrar.getIdLibro()).toUri();

        return   ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibroDTO> modificarLibro(@Validated @PathVariable("id") Integer id, @RequestBody LibroDTO libroDTO) throws Exception {

       Libro libroModificar=libroService.listarPorId(id);
        if(libroModificar.getIdLibro()==null){
            throw new ModeloNotFoundException("Id No Encontrado: "+id);
        }
        new ModelMapper().map(libroDTO,libroModificar);

        Libro libro=libroService.modificar(libroModificar);
        new ModelMapper().map(libro,libroDTO);
        return ResponseEntity.ok().body(libroDTO);


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

    @GetMapping("/pageable")
    public ResponseEntity<Page<Libro>> listarPageable(Pageable pageable) throws Exception{
        Page<Libro> listarLibroPageable = libroService.listarPageable(pageable);
        return new ResponseEntity<Page<Libro>>(listarLibroPageable, HttpStatus.OK);
    }



}
