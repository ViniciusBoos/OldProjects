package br.com.site.pessoa.repository;

import br.com.site.pessoa.model.Cidade;
import br.com.site.pessoa.model.Pessoa;
import br.com.site.pessoa.model.PessoaContato;
import br.com.site.pessoa.model.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {

    @Query(value = "SELECT * FROM pessoa_fisica WHERE id_pessoa = :id", nativeQuery = true)
    PessoaFisica findByPessoaId(@Param("id") Long pessoaId);
}
