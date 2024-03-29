package com.example.demo.controllers;

import com.example.demo.model.registro_asistencia_a_sesion;
import com.example.demo.services.registro_asistencia_a_sesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/registro_asistencia_a_sesion")
public class registro_asistencia_a_sesionController {
    private final registro_asistencia_a_sesionService registro_asistencia_a_sesionService;

    @Autowired
    public registro_asistencia_a_sesionController(registro_asistencia_a_sesionService registro_asistencia_a_sesionService)
    {
        this.registro_asistencia_a_sesionService = registro_asistencia_a_sesionService;
    }

    @GetMapping("/all")
    public List<registro_asistencia_a_sesion> getregistro_asistencia_a_sesion()
    {
        return this.registro_asistencia_a_sesionService.getregistro_asistencia_a_sesion();
    }


    @PostMapping("/add")
    public ResponseEntity<Object> registrarregistro_asistencia_a_sesion (@RequestBody registro_asistencia_a_sesion registro_asistencia_a_sesion)
    {
        return this.registro_asistencia_a_sesionService.newregistro_asistencia_a_sesion(registro_asistencia_a_sesion);
    }

    @PutMapping("/update/{registro_asistencia_a_sesionID}")
    public ResponseEntity<Object> actualizarregistro_asistencia_a_sesion (@RequestBody registro_asistencia_a_sesion registro_asistencia_a_sesion, @PathVariable("registro_asistencia_a_sesionID") Long id)
    {
        return this.registro_asistencia_a_sesionService.updateregistro_asistencia_a_sesion(registro_asistencia_a_sesion, id);
    }

    @DeleteMapping(path = "/delete/{registro_asistencia_a_sesionID}")
    public ResponseEntity<Object> eliminarregistro_asistencia_a_sesion(@PathVariable("registro_asistencia_a_sesionID") Long id)
    {
        return this.registro_asistencia_a_sesionService.deleteregistro_asistencia_a_sesion(id);
    }
}