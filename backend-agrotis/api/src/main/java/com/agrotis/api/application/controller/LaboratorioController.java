package com.agrotis.api.application.controller;

import com.agrotis.api.application.dto.LaboratorioDetalhesDTO;
import com.agrotis.api.application.service.LaboratorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/laboratorios")
@RequiredArgsConstructor
public class LaboratorioController {

    private final LaboratorioService laboratorioService;

    @GetMapping
    public ResponseEntity<List<LaboratorioDetalhesDTO>> listarLaboratoriosDetalhados(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicialDe,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicialAte,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinalDe,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinalAte,
            @RequestParam(required = false) String observacoes,
            @RequestParam(required = true) Long quantidadeMinima
    ) {
        List<LaboratorioDetalhesDTO> laboratorios = laboratorioService.listarLaboratoriosDetalhados(
                dataInicialDe, dataInicialAte, dataFinalDe, dataFinalAte, observacoes, quantidadeMinima
        );

        if (laboratorios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(laboratorios);
    }
}