package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class registro_asistencia_a_sesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_registro;
    private long asistencia;
    private String hora_asistencia;


    @ManyToOne
    @JoinColumn(name = "id_asistente_sesion")
    private asistente_sesion asistente_sesion;

}