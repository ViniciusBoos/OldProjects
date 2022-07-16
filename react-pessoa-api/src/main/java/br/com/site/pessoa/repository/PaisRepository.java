package br.com.site.pessoa.repository;

import br.com.site.pessoa.model.Cidade;
import br.com.site.pessoa.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {
}
