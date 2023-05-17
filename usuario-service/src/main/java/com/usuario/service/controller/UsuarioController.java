package com.usuario.service.controller;

import com.usuario.service.entity.Usuario;
import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;
import com.usuario.service.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        List<Usuario> usuarios = usuarioService.getAll();
        if(usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int id){
        Usuario usuario = usuarioService.getUsuarioById(id);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> guardarusuario(@RequestBody Usuario usuario){
        Usuario nuevoUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @GetMapping("/carros/{usuarioId}")
    public ResponseEntity<List<Carro>> listarCarros(@PathVariable("usuarioId") int usuarioId){
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }
        List<Carro> carros = usuarioService.getCarros(usuarioId);
        return ResponseEntity.ok(carros);
    }

    @GetMapping("/motos/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotos(@PathVariable("usuarioId") int usuarioId){
        Usuario usuario = usuarioService.getUsuarioById(usuarioId);
        if(usuario == null){
            return ResponseEntity.notFound().build();
        }
        List<Moto> motos = usuarioService.getMotos(usuarioId);
        return ResponseEntity.ok(motos);
    }

}
