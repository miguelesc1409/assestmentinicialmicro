package com.example.demo.services;

import com.example.demo.model.usuario;
import com.example.demo.repositories.usuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class usuarioService
{
    HashMap<String, Object> datos;
    private final usuarioRepository usuarioRepository;

    @Autowired
    public usuarioService(usuarioRepository usuarioRepository)
    {
        this.usuarioRepository = usuarioRepository;
    }

    public List<usuario> getusuario ()
    {
        return this.usuarioRepository.findAll();
    }

    public ResponseEntity<Object> newusuario(usuario usuario)
    {
        Optional<usuario> res = usuarioRepository.findusuarioByusuario(usuario.getusuario());
        datos = new HashMap<>();

        if(res.isPresent()) {
            datos.put("Error:", true);
            datos.put("Message", "Ya existe esa categoría.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        usuarioRepository.save(usuario);
        datos.put("Data", usuario);
        datos.put("Message", "La categoría ha sido insertada con éxito.");
        return new ResponseEntity<>(
                datos,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> updateusuario(usuario usuario, Long id)
    {
        datos = new HashMap<>();
        Optional<usuario> res = this.usuarioRepository.findById(id);
        if(res.isEmpty())
        {
            datos.put("Error", true);
            datos.put("Message", "No existe una usuario con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        usuario oldusuario = res.get();
        oldusuario.setusuario(usuario.getusuario());
        usuarioRepository.save(oldusuario);
        datos.put("Message", "Se ha actualizado la categoría.");
        datos.put("Data", oldusuario);
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }

    public ResponseEntity<Object> deleteusuario (Long id)
    {
        datos = new HashMap<>();
        boolean existe = this.usuarioRepository.existsById(id);
        if(!existe) {
            datos.put("Error", true);
            datos.put("Message", "No existe una categoría con ese ID.");
            return new ResponseEntity<>(
                    datos,
                    HttpStatus.CONFLICT
            );
        }
        usuarioRepository.deleteById(id);
        datos.put("Message", "La categoría ha sido eliminada");
        return new ResponseEntity<>(
                datos,
                HttpStatus.ACCEPTED
        );
    }
}