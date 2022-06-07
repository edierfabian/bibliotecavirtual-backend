package com.efhh.bibliotecavirtual.controller;

import com.efhh.bibliotecavirtual.dto.UsuarioDTO;
import com.efhh.bibliotecavirtual.exception.ModeloNotFoundException;
import com.efhh.bibliotecavirtual.model.Libro;
import com.efhh.bibliotecavirtual.model.Usuario;
import com.efhh.bibliotecavirtual.service.ILibroService;
import com.efhh.bibliotecavirtual.service.IUsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/usuarios")
public class usuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario() throws Exception {
        List<Usuario> listaUsuario= usuarioService.listarUsuario();
        return new ResponseEntity<List<Usuario>>(listaUsuario, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> listarPorId(@PathVariable("id")Integer id) throws Exception {
        Usuario listarUsuarioPorId=usuarioService.listarUsuarioPorId(id);
        if(listarUsuarioPorId.getIdUsuario()==null){
            throw new ModeloNotFoundException("Id de Usuario No Encontrado: "+id);
        }
        return new ResponseEntity<Usuario>(listarUsuarioPorId,HttpStatus.OK);
    }


    @GetMapping("/hateos/{id}")
    public EntityModel<Usuario> listarUsuarioPorIdHateos(@PathVariable("id") Integer id) throws Exception{
        Usuario listarUsuarioPorIdHateos=usuarioService.listarUsuarioPorId(id);
        if(listarUsuarioPorIdHateos.getIdUsuario()==null){
            throw new ModeloNotFoundException("Id de Usuario No Encontrado: "+id);
        }
        EntityModel<Usuario> recursoUsuarioHateos=EntityModel.of(listarUsuarioPorIdHateos);
        WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).listarUsuarioPorIdHateos(id));
        recursoUsuarioHateos.add(link.withRel("usuario-recurso"));
        return recursoUsuarioHateos;

    }


    @PostMapping
    public ResponseEntity<UsuarioDTO>  registrarUsuarioModelMapper(@Valid @RequestBody UsuarioDTO usuarioDTO) throws Exception {


        Usuario usuario=new ModelMapper().map(usuarioDTO,Usuario.class);

        Usuario usuarioRegistrar=usuarioService.registrarUsuario(usuario);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioRegistrar.getIdUsuario()).toUri();

        return   ResponseEntity.created(location).build();
    }





    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> modificarModelMapperUsuario(@PathVariable("id") Integer id, @RequestBody UsuarioDTO usuarioDTO) throws Exception {

        Usuario usuarioModificar=usuarioService.listarUsuarioPorId(id);
        if(usuarioModificar.getIdUsuario()==null){
            throw new ModeloNotFoundException("Id de Usuario No Encontrado: "+id);
        }
        new ModelMapper().map(usuarioDTO,usuarioModificar);

        Usuario usuario=usuarioService.modificarUsuario(usuarioModificar);
        new ModelMapper().map(usuario,usuarioDTO);

        return ResponseEntity.ok().body(usuarioDTO);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> eliminarUsuario(@PathVariable("id")Integer id) throws Exception {

        Usuario usuarioEliminado=usuarioService.listarUsuarioPorId(id);
        if(usuarioEliminado.getIdUsuario()==null){
            throw new ModeloNotFoundException("Id de Usuario No Encontrado: "+id);
        }
        usuarioService.eliminarUsuario(id);
        return  new ResponseEntity<Void>(HttpStatus.NO_CONTENT);


    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Usuario>> listarUsuarioPageable(Pageable pageable) throws Exception{
        Page<Usuario> listarUsuarioPageable = usuarioService.listarPageable(pageable);
        return new ResponseEntity<Page<Usuario>>(listarUsuarioPageable, HttpStatus.OK);
    }






}
