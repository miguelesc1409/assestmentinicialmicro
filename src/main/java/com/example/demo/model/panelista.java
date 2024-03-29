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
public class panelista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_panelista;
    private String nombre;
    private String s_nombre;
    private String p_apellido;
    private String s_apellido;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private usuario usuario;

}