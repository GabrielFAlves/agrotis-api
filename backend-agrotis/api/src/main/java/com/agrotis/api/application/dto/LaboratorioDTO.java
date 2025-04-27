package com.agrotis.api.application.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LaboratorioDTO {
    private Long id;
    private String nome;
}
