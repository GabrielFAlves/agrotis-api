package com.agrotis.api.infrastructure.persistence.jpa;

import com.agrotis.api.domain.model.Laboratorio;
import com.agrotis.api.domain.repository.LaboratorioRepository;
import com.agrotis.api.infrastructure.persistence.entity.LaboratorioEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class LaboratorioRepositoryImpl implements LaboratorioRepository {

    private final LaboratorioJpaRepositorySpring laboratorioJpaRepositorySpring;

    @Override
    public Optional<Laboratorio> findById(Long id) {
        return laboratorioJpaRepositorySpring.findById(id)
                .map(entity -> Laboratorio.builder()
                        .id(entity.getId())
                        .nome(entity.getNome())
                        .build());
    }
}