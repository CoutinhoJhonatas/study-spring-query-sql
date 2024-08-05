package com.devsuperior.uri2990.repositories;

import com.devsuperior.uri2990.dto.EmpregadoDeptDTO;
import com.devsuperior.uri2990.projections.EmpregadoDeptProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.uri2990.entities.Empregado;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmpregadoRepository extends JpaRepository<Empregado, Long> {

    @Query(nativeQuery = true,
            value = "SELECT e.cpf, e.enome, d.dnome " +
                    "FROM empregados e " +
                    "INNER JOIN departamentos d ON d.dnumero = e.dnumero " +
                    "WHERE e.cpf NOT IN " +
                        "(SELECT e2.cpf " +
                        "FROM empregados e2 " +
                        "INNER JOIN trabalha t ON t.cpf_emp = e.cpf) " +
                    "ORDER BY e.cpf ASC")
    List<EmpregadoDeptProjection> search1();

    @Query(nativeQuery = true,
            value = "SELECT e.cpf, e.enome, d.dnome " +
                    "FROM empregados e " +
                    "INNER JOIN departamentos d ON d.dnumero = e.dnumero " +
                    "LEFT JOIN trabalha t ON t.cpf_emp = e.cpf " +
                    "WHERE t.cpf_emp IS NULL " +
                    "ORDER BY e.cpf ASC")
    List<EmpregadoDeptProjection> search2();

    @Query("SELECT new com.devsuperior.uri2990.dto.EmpregadoDeptDTO(e.cpf, e.enome, e.departamento.dnome) " +
            "FROM Empregado e " +
            "WHERE e.cpf NOT IN " +
                "(SELECT e2.cpf " +
                "FROM Empregado e2 " +
                "INNER JOIN e2.projetosOndeTrabalha) " +
            "ORDER BY e.cpf ASC")
    List<EmpregadoDeptDTO> searchJpql();

}
