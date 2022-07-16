package br.com.site.pessoa.dto;

import br.com.site.pessoa.model.Pessoa;
import br.com.site.pessoa.model.PessoaContato;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ContatoDto {

    String idContato;
    String nome;
    String numero;
    String email;
    String data;

    public PessoaContato createContato(Pessoa pessoa) {
        PessoaContato contato = new PessoaContato();

        if (!data.equals("")) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataNascimento = LocalDate.parse(data, formatter);
            contato.setDataNascimento(dataNascimento);
        }

        contato.setNome(nome);
        contato.setTelefone(numero);
        contato.setEmail(email);
        contato.setPessoa(pessoa);

        return contato;
    }

    public LocalDate getDataNascimento() {

        if (data != null) {
            if (!data.equals("")) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                return LocalDate.parse(data, formatter);
            }
        }
        return null;
    }
}
