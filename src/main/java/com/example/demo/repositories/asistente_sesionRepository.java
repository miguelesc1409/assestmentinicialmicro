package com.example.demo.repositories;

import com.example.demo.model.asistente;
import com.example.demo.model.asistente_sesion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface asistente_sesionRepository extends JpaRepository<asistente_sesion,Long> {

    Optional<asistente> findasistente_sesionByasistente_sesion(String asistente);
}