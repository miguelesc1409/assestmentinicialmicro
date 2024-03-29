package com.example.demo.repositories;

import com.example.demo.model.asistente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface asistenteRepository extends JpaRepository<asistente,Long> {

    Optional<asistente> findasistenteByasistente(String asistente);
}