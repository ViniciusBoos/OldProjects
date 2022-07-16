package br.com.site.pessoa.repository;

import br.com.site.pessoa.model.PessoaTelefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<PessoaTelefone, Long> {

    @Query(value = "SELECT * FROM pessoa_telefone WHERE id_pessoa = :id", nativeQuery = true)
    PessoaTelefone findByPessoaId(@Param("id") Long pessoaId);
}
