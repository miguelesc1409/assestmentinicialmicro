package com.example.demo.services;

import com.example.demo.model.asistente_sesion;
import com.example.demo.repositories.asistente_sesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class asistente_sesionService
{
    HashMap<String, Object> datos;
    private final asistente_sesionRepository asistente_sesionRepository;

    @Autowired
    public asistente_sesion_sesionService(asistente_sesionRepository asistente_sesionRepository)
    {
        this.asistente_sesionRepository = asistente_sesionRepository;
    }

    public List<asistente_sesion> getasistente_sesion ()
    {
        return this.asistente_sesionRepository.findAll();
    }

    public ResponseEntity<Object> newasistente_sesion(asistente_sesion asistente_sesion)
    {
        Optional<asistente_sesion> res = asistente_sesionRepository.findasistente_sesionByasistente_sesion(asistente_sesion.getasistente_sesion());
        datos = new HashMap<>();

        if(res.isPresent()) {
            datos.put("Error:", true);
            datos.put("Message", "Ya existe esa categoría.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        asistente_sesionRepository.save(asistente_sesion);
        datos.put("Data", asistente_sesion);
        datos.put("Message", "La categoría ha sido insertada con éxito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateasistente_sesion(asistente_sesion asistente_sesion, Long id)
    {
        datos = new HashMap<>();
        Optional<asistente_sesion> res = this.asistente_sesionRepository.findById(id);
        if(res.isEmpty())
        {
            datos.put("Error", true);
            datos.put("Message", "No existe una asistente_sesion con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        asistente_sesion oldasistente_sesion = res.get();
        oldasistente_sesion.setasistente_sesion(asistente_sesion.getasistente_sesion());
        asistente_sesionRepository.save(oldasistente_sesion);
        datos.put("Message", "Se ha actualizado la categoría.");
        datos.put("Data", oldasistente_sesion);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> deleteasistente_sesion (Long id)
    {
        datos = new HashMap<>();
        boolean existe = this.asistente_sesionRepository.existsById(id);
        if(!existe) {
            datos.put("Error", true);
            datos.put("Message", "No existe una categoría con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        asistente_sesionRepository.deleteById(id);
        datos.put("Message", "La categoría ha sido eliminada");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}