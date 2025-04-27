package com.agrotis.api.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "propriedade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropriedadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
}