package com.example.demo.services;

import com.example.demo.model.asistente;
import com.example.demo.repositories.asistenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class asistenteService
{
    HashMap<String, Object> datos;
    private final asistenteRepository asistenteRepository;

    @Autowired
    public asistenteService (asistenteRepository asistenteRepository)
    {
        this.asistenteRepository = asistenteRepository;
    }

    public List<asistente> getasistente ()
    {
        return this.asistenteRepository.findAll();
    }

    public ResponseEntity<Object> newasistente(asistente asistente)
    {
        Optional<asistente> res = asistenteRepository.findasistenteByasistente(asistente.getasistente());
        datos = new HashMap<>();

        if(res.isPresent()) {
            datos.put("Error:", true);
            datos.put("Message", "Ya existe esa categoría.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        asistenteRepository.save(asistente);
        datos.put("Data", asistente);
        datos.put("Message", "La categoría ha sido insertada con éxito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateasistente(asistente asistente, Long id)
    {
        datos = new HashMap<>();
        Optional<asistente> res = this.asistenteRepository.findById(id);
        if(res.isEmpty())
        {
            datos.put("Error", true);
            datos.put("Message", "No existe una asistente con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        asistente oldasistente = res.get();
        oldasistente.setasistente(asistente.getasistente());
        asistenteRepository.save(oldasistente);
        datos.put("Message", "Se ha actualizado la categoría.");
        datos.put("Data", oldasistente);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> deleteasistente (Long id)
    {
        datos = new HashMap<>();
        boolean existe = this.asistenteRepository.existsById(id);
        if(!existe) {
            datos.put("Error", true);
            datos.put("Message", "No existe una categoría con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        asistenteRepository.deleteById(id);
        datos.put("Message", "La categoría ha sido eliminada");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}