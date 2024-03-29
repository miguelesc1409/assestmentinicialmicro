package com.example.demo.controllers;

import com.example.demo.model.categoria;
import com.example.demo.services.categoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categoria")
public class categoriaController {
    private final categoriaService categoriaService;

    @Autowired
    public categoriaController(categoriaService categoriaService)
    {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/all")
    public List<categoria> getcategoria()
    {
        return this.categoriaService.getcategoria();
    }


    @PostMapping("/add")
    public ResponseEntity<Object> registrarcategoria (@RequestBody categoria categoria)
    {
        return this.categoriaService.newcategoria(categoria);
    }

    @PutMapping("/update/{categoriaID}")
    public ResponseEntity<Object> actualizarcategoria (@RequestBody categoria categoria, @PathVariable("categoriaID") Long id)
    {
        return this.categoriaService.updatecategoria(categoria, id);
    }

    @DeleteMapping(path = "/delete/{categoriaID}")
    public ResponseEntity<Object> eliminarcategoria(@PathVariable("categoriaID") Long id)
    {
        return this.categoriaService.deletecategoria(id);
    }
}