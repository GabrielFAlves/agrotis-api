package com.agrotis.api.domain.repository;

import com.agrotis.api.domain.model.Laboratorio;

import java.util.Optional;

public interface LaboratorioRepository {
    Optional<Laboratorio> findById(Long id);
}