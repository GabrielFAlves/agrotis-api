package com.agrotis.api.domain.repository;

import com.agrotis.api.domain.model.Propriedade;

import java.util.Optional;

public interface PropriedadeRepository {
    Optional<Propriedade> findById(Long id);
}