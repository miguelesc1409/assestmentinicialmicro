package com.example.demo.services;

import com.example.demo.model.registro_asistencia_a_sesion;
import com.example.demo.repositories.registro_asistencia_a_sesionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class registro_asistencia_a_sesionService
{
    HashMap<String, Object> datos;
    private final registro_asistencia_a_sesionRepository registro_asistencia_a_sesionRepository;

    @Autowired
    public registro_asistencia_a_sesionService(registro_asistencia_a_sesionRepository registro_asistencia_a_sesionRepository)
    {
        this.registro_asistencia_a_sesionRepository = registro_asistencia_a_sesionRepository;
    }

    public List<registro_asistencia_a_sesion> getregistro_asistencia_a_sesion ()
    {
        return this.registro_asistencia_a_sesionRepository.findAll();
    }

    public ResponseEntity<Object> newregistro_asistencia_a_sesion(registro_asistencia_a_sesion registro_asistencia_a_sesion)
    {
        Optional<registro_asistencia_a_sesion> res = registro_asistencia_a_sesionRepository.findregistro_asistencia_a_sesionByregistro_asistencia_a_sesion(registro_asistencia_a_sesion.getregistro_asistencia_a_sesion());
        datos = new HashMap<>();

        if(res.isPresent()) {
            datos.put("Error:", true);
            datos.put("Message", "Ya existe esa categoría.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        registro_asistencia_a_sesionRepository.save(registro_asistencia_a_sesion);
        datos.put("Data", registro_asistencia_a_sesion);
        datos.put("Message", "La categoría ha sido insertada con éxito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateregistro_asistencia_a_sesion(registro_asistencia_a_sesion registro_asistencia_a_sesion, Long id)
    {
        datos = new HashMap<>();
        Optional<registro_asistencia_a_sesion> res = this.registro_asistencia_a_sesionRepository.findById(id);
        if(res.isEmpty())
        {
            datos.put("Error", true);
            datos.put("Message", "No existe una registro_asistencia_a_sesion con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        registro_asistencia_a_sesion oldregistro_asistencia_a_sesion = res.get();
        oldregistro_asistencia_a_sesion.setregistro_asistencia_a_sesion(registro_asistencia_a_sesion.getregistro_asistencia_a_sesion());
        registro_asistencia_a_sesionRepository.save(oldregistro_asistencia_a_sesion);
        datos.put("Message", "Se ha actualizado la categoría.");
        datos.put("Data", oldregistro_asistencia_a_sesion);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> deleteregistro_asistencia_a_sesion (Long id)
    {
        datos = new HashMap<>();
        boolean existe = this.registro_asistencia_a_sesionRepository.existsById(id);
        if(!existe) {
            datos.put("Error", true);
            datos.put("Message", "No existe una categoría con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        registro_asistencia_a_sesionRepository.deleteById(id);
        datos.put("Message", "La categoría ha sido eliminada");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}