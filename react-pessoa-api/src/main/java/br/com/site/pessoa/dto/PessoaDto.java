package br.com.site.pessoa.dto;

import br.com.site.pessoa.model.*;
import br.com.site.pessoa.repository.CidadeRepository;
import br.com.site.pessoa.repository.EstadoRepository;
import br.com.site.pessoa.repository.PaisRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDto {

    private String id;
    private String pais;
    private String estado;
    private String cidade;
    private String fachada;
    private String cep;
    private String bairro;
    private String rua;
    private String complemento;
    private String nome;
    private String email;
    private String numero;
    private String obsNumero;
    private String rg;
    private String cnae;
    private String tipo;

    public Pessoa getPessoa(PaisRepository paisRepository) {
        Pessoa pessoa = new Pessoa();

        pessoa.setNome(nome);
        pessoa.setEmail(email);
        pessoa.setPais(paisRepository.getById(Long.parseLong(pais)));

        if (tipo.equals("0")) {
            pessoa.setTipoPessoa(TipoPessoa.PESSOA_FISICA);
        } else {
            pessoa.setTipoPessoa(TipoPessoa.PESSOA_JURIDICA);
        }

        return pessoa;
    }

    public PessoaTelefone getTelefone() {
        PessoaTelefone telefone = new PessoaTelefone();

        String ddd = numero.substring(0, 2);
        String numeroApenasDoTelefone = numero.substring(2);

        telefone.setTelefone(numeroApenasDoTelefone);
        telefone.setDdd(ddd);
        telefone.setObservacao(obsNumero);

        return telefone;
    }

    public PessoaEndereco getEndereco(EstadoRepository estadoRepository,
                                      CidadeRepository cidadeRepository) {
        PessoaEndereco endereco = new PessoaEndereco();

        if (!cidade.equals("0") && !cidade.equals("1")) {
            endereco.setCidade(cidadeRepository.getById(Long.parseLong(cidade)));
        }
        if (!estado.equals("0") && !estado.equals("1")) {
            endereco.setEstado(estadoRepository.getById(estado));
        }
        endereco.setRua(rua);
        endereco.setBairro(bairro);
        endereco.setCep(cep);
        endereco.setFachada(fachada);
        endereco.setComplemento(complemento);

        return endereco;
    }

    public PessoaFisica getPessoaFisica(Pessoa pessoa) {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setPessoa(pessoa);
        pessoaFisica.setRg(rg);

        return pessoaFisica;
    }

    public PessoaJuridica getPessoaJuridica(Pessoa pessoa) {
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setPessoa(pessoa);
        pessoaJuridica.setCnae(cnae);

        return pessoaJuridica;
    }

}
