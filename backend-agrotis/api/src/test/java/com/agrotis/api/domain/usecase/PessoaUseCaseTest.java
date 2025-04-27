package com.agrotis.api.domain.usecase;

import com.agrotis.api.domain.model.Pessoa;
import com.agrotis.api.domain.repository.PessoaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PessoaUseCaseTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaUseCase pessoaUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCriarPessoa() {
        Pessoa pessoa = criarPessoaMock();

        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);

        Pessoa resultado = pessoaUseCase.criarPessoa(pessoa);

        assertEquals(pessoa, resultado);
        verify(pessoaRepository).save(pessoa);
    }

    @Test
    void deveAtualizarPessoaQuandoExistente() {
        Pessoa pessoaExistente = criarPessoaMock();
        Pessoa pessoaAtualizada = criarPessoaMock();
        pessoaAtualizada.setNome("Nome Atualizado");

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoaExistente));
        when(pessoaRepository.save(pessoaExistente)).thenReturn(pessoaExistente);

        Pessoa resultado = pessoaUseCase.atualizarPessoa(1L, pessoaAtualizada);

        assertEquals("Nome Atualizado", resultado.getNome());
        verify(pessoaRepository).findById(1L);
        verify(pessoaRepository).save(pessoaExistente);
    }

    @Test
    void deveLancarExcecaoAoAtualizarPessoaNaoEncontrada() {
        Pessoa pessoaAtualizada = criarPessoaMock();

        when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException excecao = assertThrows(RuntimeException.class, () -> {
            pessoaUseCase.atualizarPessoa(1L, pessoaAtualizada);
        });

        assertEquals("Pessoa não encontrada com ID: 1", excecao.getMessage());
        verify(pessoaRepository).findById(1L);
    }

    @Test
    void deveListarPessoas() {
        List<Pessoa> pessoas = List.of(criarPessoaMock());

        when(pessoaRepository.findAll()).thenReturn(pessoas);

        List<Pessoa> resultado = pessoaUseCase.listarPessoas();

        assertEquals(1, resultado.size());
        verify(pessoaRepository).findAll();
    }

    @Test
    void deveBuscarPessoaPorId() {
        Pessoa pessoa = criarPessoaMock();

        when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));

        Pessoa resultado = pessoaUseCase.buscarPessoaPorId(1L);

        assertEquals(pessoa, resultado);
        verify(pessoaRepository).findById(1L);
    }

    @Test
    void deveLancarExcecaoAoBuscarPessoaPorIdNaoExistente() {
        when(pessoaRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException excecao = assertThrows(RuntimeException.class, () -> {
            pessoaUseCase.buscarPessoaPorId(1L);
        });

        assertEquals("Pessoa não encontrada com ID: 1", excecao.getMessage());
        verify(pessoaRepository).findById(1L);
    }

    @Test
    void deveDeletarPessoa() {
        doNothing().when(pessoaRepository).deleteById(1L);

        pessoaUseCase.deletarPessoa(1L);

        verify(pessoaRepository).deleteById(1L);
    }

    private Pessoa criarPessoaMock() {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(1L);
        pessoa.setNome("Nome Teste");
        pessoa.setDataInicial(LocalDateTime.now());
        pessoa.setDataFinal(LocalDateTime.now().plusDays(1));
        pessoa.setObservacoes("Observação Teste");
        return pessoa;
    }
}
