package br.com.site.pessoa.repository;

import br.com.site.pessoa.model.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaJuridicaRepository extends JpaRepository<PessoaJuridica, Long> {

    @Query(value = "SELECT * FROM pessoa_juridica WHERE id_pessoa = :id", nativeQuery = true)
    PessoaJuridica findByPessoaId(@Param("id") Long pessoaId);
}
