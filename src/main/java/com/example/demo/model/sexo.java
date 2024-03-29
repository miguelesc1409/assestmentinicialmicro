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
public class sexo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_sexo;
    private String nombre_sexo;
}