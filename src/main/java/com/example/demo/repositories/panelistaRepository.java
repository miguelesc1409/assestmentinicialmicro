package com.example.demo.repositories;

import com.example.demo.model.panelista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface panelistaRepository extends JpaRepository<panelista,Long> {

    Optional<panelista> findpanelistaBypanelista(String panelista);
}