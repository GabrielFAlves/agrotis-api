package com.agrotis.api.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PessoaRequestDTO {

    @NotBlank
    private String nome;

    @NotNull
    private LocalDateTime dataInicial;

    @NotNull
    private LocalDateTime dataFinal;

    @NotNull
    private Long propriedadeId;

    @NotNull
    private Long laboratorioId;

    @Size(max = 100)
    private String observacoes;
}