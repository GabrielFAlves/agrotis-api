package com.agrotis.api.application.service;

import com.agrotis.api.application.dto.LaboratorioDetalhesDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LaboratorioService {

    @PersistenceContext
    private EntityManager entityManager;

    public List<LaboratorioDetalhesDTO> listarLaboratoriosDetalhados(
            LocalDateTime dataInicialDe,
            LocalDateTime dataInicialAte,
            LocalDateTime dataFinalDe,
            LocalDateTime dataFinalAte,
            String observacoes,
            Long quantidadeMinima
    ) {

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT p.laboratorio.id, UPPER(p.laboratorio.nome), COUNT(p.id) ");
        jpql.append("FROM PessoaEntity p ");
        jpql.append("WHERE 1=1 ");

        if (dataInicialDe != null) {
            jpql.append("AND p.dataInicial >= :dataInicialDe ");
        }
        if (dataInicialAte != null) {
            jpql.append("AND p.dataInicial <= :dataInicialAte ");
        }
        if (dataFinalDe != null) {
            jpql.append("AND p.dataFinal >= :dataFinalDe ");
        }
        if (dataFinalAte != null) {
            jpql.append("AND p.dataFinal <= :dataFinalAte ");
        }
        if (observacoes != null && !observacoes.isEmpty()) {
            jpql.append("AND LOWER(p.observacoes) LIKE LOWER(CONCAT('%', :observacoes, '%')) ");
        }

        jpql.append("GROUP BY p.laboratorio.id, p.laboratorio.nome ");
        jpql.append("HAVING COUNT(p.id) >= :quantidadeMinima ");
        jpql.append("ORDER BY COUNT(p.id) DESC ");
        if (dataInicialDe != null || dataInicialAte != null) {
            jpql.append(", MIN(p.dataInicial) ASC ");
        }

        TypedQuery<Object[]> query = entityManager.createQuery(jpql.toString(), Object[].class);

        if (dataInicialDe != null) {
            query.setParameter("dataInicialDe", dataInicialDe);
        }
        if (dataInicialAte != null) {
            query.setParameter("dataInicialAte", dataInicialAte);
        }
        if (dataFinalDe != null) {
            query.setParameter("dataFinalDe", dataFinalDe);
        }
        if (dataFinalAte != null) {
            query.setParameter("dataFinalAte", dataFinalAte);
        }
        if (observacoes != null && !observacoes.isEmpty()) {
            query.setParameter("observacoes", observacoes);
        }
        query.setParameter("quantidadeMinima", quantidadeMinima);

        List<Object[]> resultados = query.getResultList();

        return resultados.stream()
                .map(obj -> LaboratorioDetalhesDTO.builder()
                        .id((Long) obj[0])
                        .nome((String) obj[1])
                        .quantidadePessoas((Long) obj[2])
                        .build())
                .collect(Collectors.toList());
    }
}
