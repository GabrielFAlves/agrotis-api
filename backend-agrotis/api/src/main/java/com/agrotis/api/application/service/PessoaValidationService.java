package com.agrotis.api.application.service;

import com.agrotis.api.application.dto.PessoaRequestDTO;
import com.agrotis.api.domain.repository.LaboratorioRepository;
import com.agrotis.api.domain.repository.PropriedadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PessoaValidationService {

    private final PropriedadeRepository propriedadeRepository;
    private final LaboratorioRepository laboratorioRepository;

    public void validarPessoaRequest(PessoaRequestDTO request) {
        if (request.getPropriedadeId() == null) {
            throw new IllegalArgumentException("O ID da propriedade é obrigatório.");
        }

        if (request.getLaboratorioId() == null) {
            throw new IllegalArgumentException("O ID do laboratório é obrigatório.");
        }

        if (propriedadeRepository.findById(request.getPropriedadeId()).isEmpty()) {
            throw new IllegalArgumentException("Propriedade não encontrada com ID: " + request.getPropriedadeId());
        }

        if (laboratorioRepository.findById(request.getLaboratorioId()).isEmpty()) {
            throw new IllegalArgumentException("Laboratório não encontrado com ID: " + request.getLaboratorioId());
        }
    }
}
