package br.com.site.pessoa.repository;

import br.com.site.pessoa.model.PessoaContato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContatoRepository extends JpaRepository<PessoaContato, Long> {

    @Query(value = "SELECT * FROM pessoa_contato WHERE id_pessoa = :id", nativeQuery = true)
    List<PessoaContato> findByPessoaId(@Param("id") Long pessoaId);

    Optional<PessoaContato> findByIdContato(long parseLong);
}
