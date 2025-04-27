package com.agrotis.api.application.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LaboratorioDetalhesDTO {
    private Long id;
    private String nome;
    private Long quantidadePessoas;
}
