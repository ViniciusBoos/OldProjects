package br.com.site.pessoa.repository;

import br.com.site.pessoa.model.PessoaEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<PessoaEndereco, Long> {

    @Query(value = "SELECT * FROM pessoa_endereco WHERE id_pessoa = :id", nativeQuery = true)
    PessoaEndereco findByPessoaId(@Param("id") Long pessoaId);
}
