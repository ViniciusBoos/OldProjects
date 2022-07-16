package br.com.site.pessoa.repository;

import br.com.site.pessoa.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, String> {

    @Query("SELECT e FROM Estado e WHERE e.pais.codigo = :codigo")
    List<Estado> findAllEstadosInCountry(@Param("codigo") Long codigo);
}
