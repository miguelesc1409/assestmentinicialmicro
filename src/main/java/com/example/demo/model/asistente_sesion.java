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
public class asistente_sesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_asistente_sesion;
    @ManyToOne
    @JoinColumn(name = "id_asistente")
    private asistente asistente;

    @ManyToOne
    @JoinColumn(name = "id_sesion")
    private sesion sesion;

}