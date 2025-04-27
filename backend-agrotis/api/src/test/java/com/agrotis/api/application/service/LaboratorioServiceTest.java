package com.agrotis.api.application.service;

import com.agrotis.api.application.dto.LaboratorioDetalhesDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class LaboratorioServiceTest {

    @Mock
    private EntityManager entityManager;

    @Mock
    private TypedQuery<Object[]> typedQuery;

    @InjectMocks
    private LaboratorioService laboratorioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarLaboratoriosDetalhados() {
        List<Object[]> resultados = new ArrayList<>();
        resultados.add(new Object[]{1L, "LABORATORIO A", 5L});

        when(entityManager.createQuery(anyString(), eq(Object[].class))).thenReturn(typedQuery);
        when(typedQuery.getResultList()).thenReturn(resultados);

        List<LaboratorioDetalhesDTO> lista = laboratorioService.listarLaboratoriosDetalhados(
                null, null, null, null, null, 1L
        );
        
        assertEquals(1, lista.size());
        assertEquals(1L, lista.get(0).getId());
        assertEquals("LABORATORIO A", lista.get(0).getNome());
        assertEquals(5L, lista.get(0).getQuantidadePessoas());
    }
}
