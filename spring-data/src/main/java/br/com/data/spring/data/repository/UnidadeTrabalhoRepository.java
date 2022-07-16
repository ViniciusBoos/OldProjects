package br.com.data.spring.data.repository;

import br.com.data.spring.data.orm.UnidadeTrabalho;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadeTrabalhoRepository extends CrudRepository<UnidadeTrabalho, Integer> {
}
