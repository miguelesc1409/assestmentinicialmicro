package com.example.demo.repositories;

import com.example.demo.model.registro_asistencia_a_sesion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface registro_asistencia_a_sesionRepository extends JpaRepository<registro_asistencia_a_sesion,Long> {

    Optional<registro_asistencia_a_sesion> findregistro_asistencia_a_sesionByregistro_asistencia_a_sesion(String registro_asistencia_a_sesion);
}