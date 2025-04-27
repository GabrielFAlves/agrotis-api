package com.agrotis.api.application.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PessoaResponseDTO {
    private String nome;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
    private InfosPropriedadeDTO infosPropriedade;
    private LaboratorioDTO laboratorio;
    private String observacoes;
}
