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
public class categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_categoria;
    private String nombre_categoria;
}