package com.example.demo.repositories;

import com.example.demo.model.usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface usuarioRepository extends JpaRepository<usuario,Long> {

    Optional<usuario> findusuarioByusuario(String usuario);
}