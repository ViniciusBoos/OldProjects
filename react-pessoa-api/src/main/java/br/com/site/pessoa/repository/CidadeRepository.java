package br.com.site.pessoa.repository;

import br.com.site.pessoa.model.Cidade;
import br.com.site.pessoa.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    @Query("SELECT c FROM Cidade c WHERE c.estado.sigla = :sigla")
    List<Cidade> findAllCidadesInState(@Param("sigla") String sigla);
}
