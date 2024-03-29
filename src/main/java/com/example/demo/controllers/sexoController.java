package com.example.demo.controllers;

import com.example.demo.model.sexo;
import com.example.demo.services.sexoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sexo")
public class sexoController {
    private final sexoService sexoService;

    @Autowired
    public sexoController(sexoService sexoService)
    {
        this.sexoService = sexoService;
    }

    @GetMapping("/all")
    public List<sexo> getsexo()
    {
        return this.sexoService.getsexo();
    }


    @PostMapping("/add")
    public ResponseEntity<Object> registrarsexo (@RequestBody sexo sexo)
    {
        return this.sexoService.newsexo(sexo);
    }

    @PutMapping("/update/{sexoID}")
    public ResponseEntity<Object> actualizarsexo (@RequestBody sexo sexo, @PathVariable("sexoID") Long id)
    {
        return this.sexoService.updatesexo(sexo, id);
    }

    @DeleteMapping(path = "/delete/{sexoID}")
    public ResponseEntity<Object> eliminarsexo(@PathVariable("sexoID") Long id)
    {
        return this.sexoService.deletesexo(id);
    }
}