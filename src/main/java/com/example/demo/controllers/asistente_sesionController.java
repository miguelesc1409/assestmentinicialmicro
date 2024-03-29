package com.example.demo.controllers;

import com.example.demo.model.asistente_sesion;
import com.example.demo.services.asistente_sesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/asistente_sesion")
public class asistente_sesionController {
    private final asistente_sesionService asistente_sesionService;

    @Autowired
    public asistente_sesionController (asistente_sesionService asistente_sesionService)
    {
        this.asistente_sesionService = asistente_sesionService;
    }

    @GetMapping("/all")
    public List<asistente_sesion> getasistente_sesion()
    {
        return this.asistente_sesionService.getasistente_sesion();
    }


    @PostMapping("/add")
    public ResponseEntity<Object> registrarasistente_sesion (@RequestBody asistente_sesion asistente_sesion)
    {
        return this.asistente_sesionService.newasistente_sesion(asistente_sesion);
    }

    @PutMapping("/update/{asistente_sesionID}")
    public ResponseEntity<Object> actualizarasistente_sesion (@RequestBody asistente_sesion asistente_sesion, @PathVariable("asistente_sesionID") Long id)
    {
        return this.asistente_sesionService.updateasistente_sesion(asistente_sesion, id);
    }

    @DeleteMapping(path = "/delete/{asistente_sesionID}")
    public ResponseEntity<Object> eliminarasistente_sesion(@PathVariable("asistente_sesionID") Long id)
    {
        return this.asistente_sesionService.deleteasistente_sesion(id);
    }
}