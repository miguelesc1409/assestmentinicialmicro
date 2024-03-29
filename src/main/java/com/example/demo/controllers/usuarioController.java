package com.example.demo.controllers;

import com.example.demo.model.usuario;
import com.example.demo.services.usuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/usuario")
public class usuarioController {
    private final usuarioService usuarioService;

    @Autowired
    public usuarioController(usuarioService usuarioService)
    {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/all")
    public List<usuario> getusuario()
    {
        return this.usuarioService.getusuario();
    }


    @PostMapping("/add")
    public ResponseEntity<Object> registrarusuario (@RequestBody usuario usuario)
    {
        return this.usuarioService.newusuario(usuario);
    }

    @PutMapping("/update/{usuarioID}")
    public ResponseEntity<Object> actualizarusuario (@RequestBody usuario usuario, @PathVariable("usuarioID") Long id)
    {
        return this.usuarioService.updateusuario(usuario, id);
    }

    @DeleteMapping(path = "/delete/{usuarioID}")
    public ResponseEntity<Object> eliminarusuario(@PathVariable("usuarioID") Long id)
    {
        return this.usuarioService.deleteusuario(id);
    }
}