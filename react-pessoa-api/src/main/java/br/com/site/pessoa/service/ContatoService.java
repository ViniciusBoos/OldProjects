package br.com.site.pessoa.service;

import br.com.site.pessoa.dto.ContatoDto;
import br.com.site.pessoa.model.Pessoa;
import br.com.site.pessoa.model.PessoaContato;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ContatoService {

    public static PessoaContato criarContato(Pessoa pessoa, ContatoDto contatoDto){
        PessoaContato contato = new PessoaContato();

        if (!contatoDto.getData().equals("")) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataNascimento = LocalDate.parse(contatoDto.getData(), formatter);
            contato.setDataNascimento(dataNascimento);
        }

        contato.setNome(contatoDto.getNome());
        contato.setTelefone(contatoDto.getNumero());
        contato.setEmail(contatoDto.getEmail());
        contato.setPessoa(pessoa);

        return contato;
    }

    public static PessoaContato alterarContato(ContatoDto contatoDto, PessoaContato pessoaContato) {
        pessoaContato.setNome(contatoDto.getNome());
        pessoaContato.setTelefone(contatoDto.getNumero());
        pessoaContato.setEmail(contatoDto.getEmail());
        if (contatoDto.getData() != null) {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataNascimento = LocalDate.parse(contatoDto.getData(), formatter);
            pessoaContato.setDataNascimento(dataNascimento);
        }
        return pessoaContato;
    }
}
