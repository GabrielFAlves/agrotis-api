package com.agrotis.api.infrastructure.persistence.jpa;

import com.agrotis.api.infrastructure.persistence.entity.PessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaJpaRepositorySpring extends JpaRepository<PessoaEntity, Long> {
}