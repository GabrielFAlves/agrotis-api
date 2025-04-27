package com.agrotis.api.infrastructure.persistence.entity;

import com.agrotis.api.domain.model.Pessoa;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pessoa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDateTime dataInicial;

    private LocalDateTime dataFinal;

    @Column(length = 100)
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "propriedade_id", nullable = false)
    private PropriedadeEntity propriedade;

    @ManyToOne
    @JoinColumn(name = "laboratorio_id", nullable = false)
    private LaboratorioEntity laboratorio;

    public static PessoaEntity fromDomain(Pessoa pessoa) {
        return PessoaEntity.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .dataInicial(pessoa.getDataInicial())
                .dataFinal(pessoa.getDataFinal())
                .observacoes(pessoa.getObservacoes())
                .propriedade(pessoa.getPropriedade() != null
                        ? PropriedadeEntity.builder()
                        .id(pessoa.getPropriedade().getId())
                        .nome(pessoa.getPropriedade().getNome())
                        .build()
                        : null)
                .laboratorio(pessoa.getLaboratorio() != null
                        ? LaboratorioEntity.builder()
                        .id(pessoa.getLaboratorio().getId())
                        .nome(pessoa.getLaboratorio().getNome())
                        .build()
                        : null)
                .build();
    }

    public Pessoa toDomain() {
        return Pessoa.builder()
                .id(this.id)
                .nome(this.nome)
                .dataInicial(this.dataInicial)
                .dataFinal(this.dataFinal)
                .observacoes(this.observacoes)
                .propriedade(this.propriedade != null
                        ? com.agrotis.api.domain.model.Propriedade.builder()
                        .id(this.propriedade.getId())
                        .nome(this.propriedade.getNome())
                        .build()
                        : null)
                .laboratorio(this.laboratorio != null
                        ? com.agrotis.api.domain.model.Laboratorio.builder()
                        .id(this.laboratorio.getId())
                        .nome(this.laboratorio.getNome())
                        .build()
                        : null)
                .build();
    }
}