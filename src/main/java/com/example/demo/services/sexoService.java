package com.example.demo.services;

import com.example.demo.model.sexo;
import com.example.demo.repositories.sexoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class sexoService
{
    HashMap<String, Object> datos;
    private final sexoRepository sexoRepository;

    @Autowired
    public sexoService(sexoRepository sexoRepository)
    {
        this.sexoRepository = sexoRepository;
    }

    public List<sexo> getsexo ()
    {
        return this.sexoRepository.findAll();
    }

    public ResponseEntity<Object> newsexo(sexo sexo)
    {
        Optional<sexo> res = sexoRepository.findsexoBysexo(sexo.getsexo());
        datos = new HashMap<>();

        if(res.isPresent()) {
            datos.put("Error:", true);
            datos.put("Message", "Ya existe esa categoría.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        sexoRepository.save(sexo);
        datos.put("Data", sexo);
        datos.put("Message", "La categoría ha sido insertada con éxito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updatesexo(sexo sexo, Long id)
    {
        datos = new HashMap<>();
        Optional<sexo> res = this.sexoRepository.findById(id);
        if(res.isEmpty())
        {
            datos.put("Error", true);
            datos.put("Message", "No existe una sexo con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        sexo oldsexo = res.get();
        oldsexo.setsexo(sexo.getsexo());
        sexoRepository.save(oldsexo);
        datos.put("Message", "Se ha actualizado la categoría.");
        datos.put("Data", oldsexo);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> deletesexo (Long id)
    {
        datos = new HashMap<>();
        boolean existe = this.sexoRepository.existsById(id);
        if(!existe) {
            datos.put("Error", true);
            datos.put("Message", "No existe una categoría con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        sexoRepository.deleteById(id);
        datos.put("Message", "La categoría ha sido eliminada");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}