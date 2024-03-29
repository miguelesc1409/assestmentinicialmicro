package com.example.demo.repositories;

import com.example.demo.model.sexo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface sexoRepository extends JpaRepository<sexo,Long> {

    Optional<sexo> findsexoBysexo(String sexo);
}