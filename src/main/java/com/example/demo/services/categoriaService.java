package com.example.demo.services;

import com.example.demo.model.categoria;
import com.example.demo.repositories.categoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class categoriaService
{
    HashMap<String, Object> datos;
    private final categoriaRepository categoriaRepository;

    @Autowired
    public categoriaService (categoriaRepository categoriaRepository)
    {
        this.categoriaRepository = categoriaRepository;
    }

    public List<categoria> getcategoria ()
    {
        return this.categoriaRepository.findAll();
    }

    public ResponseEntity<Object> newcategoria(categoria categoria)
    {
        Optional<categoria> res = categoriaRepository.findcategoriaBycategoria(categoria.getcategoria());
        datos = new HashMap<>();

        if(res.isPresent()) {
            datos.put("Error:", true);
            datos.put("Message", "Ya existe esa categoría.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        categoriaRepository.save(categoria);
        datos.put("Data", categoria);
        datos.put("Message", "La categoría ha sido insertada con éxito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updatecategoria(categoria categoria, Long id)
    {
        datos = new HashMap<>();
        Optional<categoria> res = this.categoriaRepository.findById(id);
        if(res.isEmpty())
        {
            datos.put("Error", true);
            datos.put("Message", "No existe una categoria con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        categoria oldcategoria = res.get();
        oldcategoria.setcategoria(categoria.getcategoria());
        categoriaRepository.save(oldcategoria);
        datos.put("Message", "Se ha actualizado la categoría.");
        datos.put("Data", oldcategoria);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> deletecategoria (Long id)
    {
        datos = new HashMap<>();
        boolean existe = this.categoriaRepository.existsById(id);
        if(!existe) {
            datos.put("Error", true);
            datos.put("Message", "No existe una categoría con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        categoriaRepository.deleteById(id);
        datos.put("Message", "La categoría ha sido eliminada");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}