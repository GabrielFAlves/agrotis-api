package com.agrotis.api.domain.usecase;

import com.agrotis.api.domain.model.Pessoa;
import com.agrotis.api.domain.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PessoaUseCase {

    private final PessoaRepository pessoaRepository;

    public Pessoa criarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa atualizarPessoa(Long id, Pessoa pessoaAtualizada) {
        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com ID: " + id));

        pessoaExistente.setNome(pessoaAtualizada.getNome());
        pessoaExistente.setDataInicial(pessoaAtualizada.getDataInicial());
        pessoaExistente.setDataFinal(pessoaAtualizada.getDataFinal());
        pessoaExistente.setObservacoes(pessoaAtualizada.getObservacoes());
        pessoaExistente.setPropriedade(pessoaAtualizada.getPropriedade());
        pessoaExistente.setLaboratorio(pessoaAtualizada.getLaboratorio());

        return pessoaRepository.save(pessoaExistente);
    }

    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPessoaPorId(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com ID: " + id));
    }

    public void deletarPessoa(Long id) {
        pessoaRepository.deleteById(id);
    }
}