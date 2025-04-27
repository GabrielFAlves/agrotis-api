package com.agrotis.api.application.service;

import com.agrotis.api.application.dto.PessoaRequestDTO;
import com.agrotis.api.application.service.PessoaValidationService;
import com.agrotis.api.domain.model.Propriedade;
import com.agrotis.api.domain.model.Laboratorio;
import com.agrotis.api.domain.repository.PropriedadeRepository;
import com.agrotis.api.domain.repository.LaboratorioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class PessoaValidationServiceTest {

    @Mock
    private PropriedadeRepository propriedadeRepository;

    @Mock
    private LaboratorioRepository laboratorioRepository;

    @InjectMocks
    private PessoaValidationService pessoaValidationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveLancarExcecaoQuandoPropriedadeIdForNulo() {
        PessoaRequestDTO request = PessoaRequestDTO.builder()
                .propriedadeId(null)
                .laboratorioId(1L)
                .build();

        assertThrows(IllegalArgumentException.class, () -> pessoaValidationService.validarPessoaRequest(request));
    }

    @Test
    void deveLancarExcecaoQuandoLaboratorioIdForNulo() {
        PessoaRequestDTO request = PessoaRequestDTO.builder()
                .propriedadeId(1L)
                .laboratorioId(null)
                .build();

        assertThrows(IllegalArgumentException.class, () -> pessoaValidationService.validarPessoaRequest(request));
    }

    @Test
    void deveLancarExcecaoQuandoPropriedadeNaoEncontrada() {
        PessoaRequestDTO request = PessoaRequestDTO.builder()
                .propriedadeId(1L)
                .laboratorioId(2L)
                .build();

        when(propriedadeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> pessoaValidationService.validarPessoaRequest(request));
    }

    @Test
    void deveLancarExcecaoQuandoLaboratorioNaoEncontrado() {
        PessoaRequestDTO request = PessoaRequestDTO.builder()
                .propriedadeId(1L)
                .laboratorioId(2L)
                .build();

        when(propriedadeRepository.findById(1L)).thenReturn(Optional.of(new Propriedade()));
        when(laboratorioRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> pessoaValidationService.validarPessoaRequest(request));
    }

    @Test
    void deveValidarComSucessoQuandoTudoEstiverCorreto() {
        PessoaRequestDTO request = PessoaRequestDTO.builder()
                .propriedadeId(1L)
                .laboratorioId(2L)
                .build();

        when(propriedadeRepository.findById(1L)).thenReturn(Optional.of(new Propriedade()));
        when(laboratorioRepository.findById(2L)).thenReturn(Optional.of(new Laboratorio()));

        pessoaValidationService.validarPessoaRequest(request);
    }
}
