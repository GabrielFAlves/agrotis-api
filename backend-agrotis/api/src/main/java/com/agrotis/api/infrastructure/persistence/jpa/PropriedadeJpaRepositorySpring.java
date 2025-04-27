package com.agrotis.api.infrastructure.persistence.jpa;

import com.agrotis.api.infrastructure.persistence.entity.PropriedadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropriedadeJpaRepositorySpring extends JpaRepository<PropriedadeEntity, Long> {
}