package com.example.demo.controllers;

import com.example.demo.model.sesion;
import com.example.demo.services.sesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/sesion")
public class sesionController {
    private final sesionService sesionService;

    @Autowired
    public sesionController(sesionService sesionService)
    {
        this.sesionService = sesionService;
    }

    @GetMapping("/all")
    public List<sesion> getsesion()
    {
        return this.sesionService.getsesion();
    }


    @PostMapping("/add")
    public ResponseEntity<Object> registrarsesion (@RequestBody sesion sesion)
    {
        return this.sesionService.newsesion(sesion);
    }

    @PutMapping("/update/{sesionID}")
    public ResponseEntity<Object> actualizarsesion (@RequestBody sesion sesion, @PathVariable("sesionID") Long id)
    {
        return this.sesionService.updatesesion(sesion, id);
    }

    @DeleteMapping(path = "/delete/{sesionID}")
    public ResponseEntity<Object> eliminarsesion(@PathVariable("sesionID") Long id)
    {
        return this.sesionService.deletesesion(id);
    }
}