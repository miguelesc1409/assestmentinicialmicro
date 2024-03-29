package com.example.demo.repositories;

import com.example.demo.model.categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface categoriaRepository extends JpaRepository<categoria,Long> {

    Optional<categoria> findcategoriaBycategoria(String categoria);
}