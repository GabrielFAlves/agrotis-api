package com.agrotis.api.application.controller;

import com.agrotis.api.domain.model.Propriedade;
import com.agrotis.api.infrastructure.persistence.jpa.PropriedadeJpaRepositorySpring;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/propriedades")
@RequiredArgsConstructor
public class PropriedadeController {

    private final PropriedadeJpaRepositorySpring propriedadeJpaRepositorySpring;

    @GetMapping
    public ResponseEntity<List<Propriedade>> listarPropriedades() {
        List<Propriedade> propriedades = propriedadeJpaRepositorySpring.findAll()
                .stream()
                .map(entity -> Propriedade.builder()
                        .id(entity.getId())
                        .nome(entity.getNome())
                        .build())
                .toList();

        if (propriedades.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(propriedades);
    }
}
