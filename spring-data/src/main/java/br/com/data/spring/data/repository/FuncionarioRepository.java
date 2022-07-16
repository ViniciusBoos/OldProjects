package br.com.data.spring.data.repository;

import br.com.data.spring.data.orm.Funcionario;
import br.com.data.spring.data.orm.FuncionarioProcecao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer>,
        JpaSpecificationExecutor<Funcionario> {

    List<Funcionario> findByNome(String nome);

    @Query("SELECT f FROM Funcionario f WHERE f.dataContratacao = :data AND f.salario >= :salario")
    List<Funcionario> encontrePorDataESalarioMaiorQue(LocalDate data, BigDecimal salario);

    @Query(value = "SELECT * FROM funcionarios f WHERE f.data_contratacao >= :data", nativeQuery = true)
    List<Funcionario> encontrePorDataContratacaoMaiorQue(LocalDate data);

    @Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f", nativeQuery = true)
    List<FuncionarioProcecao> encontrePorSalario();
}