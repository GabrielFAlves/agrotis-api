package com.agrotis.api.domain.repository;

import com.agrotis.api.domain.model.Pessoa;

import java.util.List;
import java.util.Optional;

public interface PessoaRepository {
    Pessoa save(Pessoa pessoa);
    Optional<Pessoa> findById(Long id);
    List<Pessoa> findAll();
    void deleteById(Long id);
}