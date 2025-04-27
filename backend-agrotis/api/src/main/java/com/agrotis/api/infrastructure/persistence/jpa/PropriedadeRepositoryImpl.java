package com.agrotis.api.infrastructure.persistence.jpa;

import com.agrotis.api.domain.model.Propriedade;
import com.agrotis.api.domain.repository.PropriedadeRepository;
import com.agrotis.api.infrastructure.persistence.entity.PropriedadeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PropriedadeRepositoryImpl implements PropriedadeRepository {

    private final PropriedadeJpaRepositorySpring propriedadeJpaRepositorySpring;

    @Override
    public Optional<Propriedade> findById(Long id) {
        return propriedadeJpaRepositorySpring.findById(id)
                .map(entity -> Propriedade.builder()
                        .id(entity.getId())
                        .nome(entity.getNome())
                        .build());
    }
}