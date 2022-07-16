package br.com.site.pessoa.repository;

import br.com.site.pessoa.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    @Query(value = "SELECT e.email FROM pessoa e", nativeQuery = true )
    List<String> findAllEmails();

    @Query(value = "SELECT p FROM Pessoa p WHERE p.email= :email")
    Pessoa findByEmail(@Param("email") String email);

    List<Pessoa> findByNomeLike(String nome);
}
