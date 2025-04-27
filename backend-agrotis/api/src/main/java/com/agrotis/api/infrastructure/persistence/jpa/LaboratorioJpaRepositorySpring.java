package com.agrotis.api.infrastructure.persistence.jpa;

import com.agrotis.api.infrastructure.persistence.entity.LaboratorioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratorioJpaRepositorySpring extends JpaRepository<LaboratorioEntity, Long> {
}