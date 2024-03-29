package com.example.demo.services;

import com.example.demo.model.panelista;
import com.example.demo.repositories.panelistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class panelistaService
{
    HashMap<String, Object> datos;
    private final panelistaRepository panelistaRepository;

    @Autowired
    public panelistaService(panelistaRepository panelistaRepository)
    {
        this.panelistaRepository = panelistaRepository;
    }

    public List<panelista> getpanelista ()
    {
        return this.panelistaRepository.findAll();
    }

    public ResponseEntity<Object> newpanelista(panelista panelista)
    {
        Optional<panelista> res = panelistaRepository.findpanelistaBypanelista(panelista.getpanelista());
        datos = new HashMap<>();

        if(res.isPresent()) {
            datos.put("Error:", true);
            datos.put("Message", "Ya existe esa categoría.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        panelistaRepository.save(panelista);
        datos.put("Data", panelista);
        datos.put("Message", "La categoría ha sido insertada con éxito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updatepanelista(panelista panelista, Long id)
    {
        datos = new HashMap<>();
        Optional<panelista> res = this.panelistaRepository.findById(id);
        if(res.isEmpty())
        {
            datos.put("Error", true);
            datos.put("Message", "No existe una panelista con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        panelista oldpanelista = res.get();
        oldpanelista.setpanelista(panelista.getpanelista());
        panelistaRepository.save(oldpanelista);
        datos.put("Message", "Se ha actualizado la categoría.");
        datos.put("Data", oldpanelista);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> deletepanelista (Long id)
    {
        datos = new HashMap<>();
        boolean existe = this.panelistaRepository.existsById(id);
        if(!existe) {
            datos.put("Error", true);
            datos.put("Message", "No existe una categoría con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        panelistaRepository.deleteById(id);
        datos.put("Message", "La categoría ha sido eliminada");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}