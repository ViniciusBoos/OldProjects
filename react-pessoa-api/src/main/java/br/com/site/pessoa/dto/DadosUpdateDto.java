package br.com.site.pessoa.dto;

import br.com.site.pessoa.model.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class DadosUpdateDto {

    private Pessoa pessoa;
    private List<PessoaContato> contatoList;
    private PessoaEndereco pessoaEndereco;
    private PessoaTelefone pessoaTelefone;
    private PessoaFisica pessoaFisica;
    private PessoaJuridica pessoaJuridica;
    private Pais pais;
    private Estado estado;
    private Cidade cidade;
}
