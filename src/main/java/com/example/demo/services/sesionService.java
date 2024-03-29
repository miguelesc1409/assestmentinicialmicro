package com.example.demo.services;

import com.example.demo.model.sesion;
import com.example.demo.repositories.sesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class sesionService
{
    HashMap<String, Object> datos;
    private final sesionRepository sesionRepository;

    @Autowired
    public sesionService(sesionRepository sesionRepository)
    {
        this.sesionRepository = sesionRepository;
    }

    public List<sesion> getsesion ()
    {
        return this.sesionRepository.findAll();
    }

    public ResponseEntity<Object> newsesion(sesion sesion)
    {
        Optional<sesion> res = sesionRepository.findsesionBysesion(sesion.getsesion());
        datos = new HashMap<>();

        if(res.isPresent()) {
            datos.put("Error:", true);
            datos.put("Message", "Ya existe esa categoría.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        sesionRepository.save(sesion);
        datos.put("Data", sesion);
        datos.put("Message", "La categoría ha sido insertada con éxito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updatesesion(sesion sesion, Long id)
    {
        datos = new HashMap<>();
        Optional<sesion> res = this.sesionRepository.findById(id);
        if(res.isEmpty())
        {
            datos.put("Error", true);
            datos.put("Message", "No existe una sesion con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        sesion oldsesion = res.get();
        oldsesion.setsesion(sesion.getsesion());
        sesionRepository.save(oldsesion);
        datos.put("Message", "Se ha actualizado la categoría.");
        datos.put("Data", oldsesion);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> deletesesion (Long id)
    {
        datos = new HashMap<>();
        boolean existe = this.sesionRepository.existsById(id);
        if(!existe) {
            datos.put("Error", true);
            datos.put("Message", "No existe una categoría con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        sesionRepository.deleteById(id);
        datos.put("Message", "La categoría ha sido eliminada");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}