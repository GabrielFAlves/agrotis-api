package com.agrotis.api.application.controller;

import com.agrotis.api.application.dto.InfosPropriedadeDTO;
import com.agrotis.api.application.dto.LaboratorioDTO;
import com.agrotis.api.application.dto.PessoaRequestDTO;
import com.agrotis.api.application.dto.PessoaResponseDTO;
import com.agrotis.api.application.service.PessoaValidationService;
import com.agrotis.api.domain.model.Laboratorio;
import com.agrotis.api.domain.model.Pessoa;
import com.agrotis.api.domain.model.Propriedade;
import com.agrotis.api.domain.usecase.PessoaUseCase;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pessoas")
@RequiredArgsConstructor
public class PessoaController {

    private final PessoaUseCase pessoaUseCase;
    private final PessoaValidationService pessoaValidationService;

    @PostMapping
    public ResponseEntity<?> criarPessoa(@Valid @RequestBody PessoaRequestDTO request) {
        try {
            pessoaValidationService.validarPessoaRequest(request);

            Pessoa pessoa = mapToDomain(request);
            Pessoa pessoaCriada = pessoaUseCase.criarPessoa(pessoa);
            return new ResponseEntity<>(mapToResponse(pessoaCriada), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<PessoaResponseDTO>> listarPessoas() {
        List<Pessoa> pessoas = pessoaUseCase.listarPessoas();
        List<PessoaResponseDTO> response = pessoas.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPessoaPorId(@PathVariable Long id) {
        Pessoa pessoa = pessoaUseCase.buscarPessoaPorId(id);
        if (pessoa == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapToResponse(pessoa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarPessoa(@PathVariable Long id, @Valid @RequestBody PessoaRequestDTO request) {
        try {
            pessoaValidationService.validarPessoaRequest(request);

            Pessoa pessoaAtualizada = mapToDomain(request);
            Pessoa pessoaSalva = pessoaUseCase.atualizarPessoa(id, pessoaAtualizada);
            return ResponseEntity.ok(mapToResponse(pessoaSalva));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        pessoaUseCase.deletarPessoa(id);
        return ResponseEntity.noContent().build();
    }

    private Pessoa mapToDomain(PessoaRequestDTO request) {
        return Pessoa.builder()
                .nome(request.getNome())
                .dataInicial(request.getDataInicial())
                .dataFinal(request.getDataFinal())
                .observacoes(request.getObservacoes())
                .propriedade(Propriedade.builder().id(request.getPropriedadeId()).build())
                .laboratorio(Laboratorio.builder().id(request.getLaboratorioId()).build())
                .build();
    }

    private PessoaResponseDTO mapToResponse(Pessoa pessoa) {
        return PessoaResponseDTO.builder()
                .nome(pessoa.getNome())
                .dataInicial(pessoa.getDataInicial())
                .dataFinal(pessoa.getDataFinal())
                .observacoes(pessoa.getObservacoes())
                .infosPropriedade(
                        pessoa.getPropriedade() != null
                                ? InfosPropriedadeDTO.builder()
                                .id(pessoa.getPropriedade().getId())
                                .nome(pessoa.getPropriedade().getNome())
                                .build()
                                : null
                )
                .laboratorio(
                        pessoa.getLaboratorio() != null
                                ? LaboratorioDTO.builder()
                                .id(pessoa.getLaboratorio().getId())
                                .nome(pessoa.getLaboratorio().getNome())
                                .build()
                                : null
                )
                .build();
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<String> handleInvalidFormatException(InvalidFormatException ex) {
        return ResponseEntity.badRequest().body("Formato de dados inválido. Verifique os campos enviados.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        String mensagemErro = ex.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .orElse("Erro de validação");
        return ResponseEntity.badRequest().body(mensagemErro);
    }
}
