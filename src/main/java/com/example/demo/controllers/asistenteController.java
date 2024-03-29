package com.example.demo.controllers;

import com.example.demo.model.asistente;
import com.example.demo.services.asistenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/asistente")
public class asistenteController {
    private final asistenteService asistenteService;

    @Autowired
    public asistenteController (asistenteService asistenteService)
    {
        this.asistenteService = asistenteService;
    }

    @GetMapping("/all")
    public List<asistente> getasistente()
    {
        return this.asistenteService.getasistente();
    }


    @PostMapping("/add")
    public ResponseEntity<Object> registrarasistente (@RequestBody asistente asistente)
    {
        return this.asistenteService.newasistente(asistente);
    }

    @PutMapping("/update/{asistenteID}")
    public ResponseEntity<Object> actualizarasistente (@RequestBody asistente asistente, @PathVariable("asistenteID") Long id)
    {
        return this.asistenteService.updateasistente(asistente, id);
    }

    @DeleteMapping(path = "/delete/{asistenteID}")
    public ResponseEntity<Object> eliminarasistente(@PathVariable("asistenteID") Long id)
    {
        return this.asistenteService.deleteasistente(id);
    }
}