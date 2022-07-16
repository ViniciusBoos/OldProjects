package br.com.site.pessoa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "PESSOA_CONTATO")
@Getter
@Setter
public class PessoaContato implements Serializable {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Id
    @Column(name = "Id_Contato")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idContato;

    @OneToOne
    @JoinColumn(name = "id_contato_funcao")
    private ContatoFuncao contatoFuncao;

    @Column(name = "Nome")
    private String Nome;

    @Column(name = "Telefone")
    private String telefone;

    @Column(name = "Data_Nascimento")
    private LocalDate dataNascimento;

    @Column(name = "Email")
    private String email;

    @Column(name = "Observacao")
    private String Observacao;

    @Column(name = "Ativo")
    private Character ativo;

    @Column(name = "Usuario_Created")
    private String usuarioCreated;

    @Column(name = "Data_Created")
    private LocalDate dataCreated = LocalDate.now();

    @Column(name = "Usuario_Changed")
    private String usuarioChanged;

    @Column(name = "Data_Changed")
    private LocalDate dataChanged;
}
