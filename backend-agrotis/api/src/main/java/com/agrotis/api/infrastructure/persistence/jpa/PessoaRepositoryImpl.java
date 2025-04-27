package com.agrotis.api.infrastructure.persistence.jpa;

import com.agrotis.api.domain.model.Pessoa;
import com.agrotis.api.domain.repository.PessoaRepository;
import com.agrotis.api.infrastructure.persistence.entity.PessoaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PessoaRepositoryImpl implements PessoaRepository {

    private final PessoaJpaRepositorySpring pessoaJpaRepositorySpring;

    @Override
    public Pessoa save(Pessoa pessoa) {
        PessoaEntity entity = PessoaEntity.fromDomain(pessoa);
        PessoaEntity saved = pessoaJpaRepositorySpring.save(entity);
        return saved.toDomain();
    }

    @Override
    public Optional<Pessoa> findById(Long id) {
        return pessoaJpaRepositorySpring.findById(id)
                .map(PessoaEntity::toDomain);
    }

    @Override
    public List<Pessoa> findAll() {
        return pessoaJpaRepositorySpring.findAll()
                .stream()
                .map(PessoaEntity::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        pessoaJpaRepositorySpring.deleteById(id);
    }
}