package com.JCJ.gescon.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "T_COCHE")
public class Coche {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int identificador;

    @NotBlank
    @Column(name = "marca", nullable = false, length = 50)
    private String marca;

    @NotBlank
    @Column(name = "modelo", nullable = false, length = 50)
    private String modelo;

    @Positive
    @Column(name = "cilindrada", nullable = false)
    private int cilindrada;
}
