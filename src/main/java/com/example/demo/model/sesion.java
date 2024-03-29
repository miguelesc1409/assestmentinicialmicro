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
public class sesion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_sesion;
    private String titulo;
    private String fecha;
    private String hora;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_panelista")
    private panelista panelista;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private usuario categoria;

}