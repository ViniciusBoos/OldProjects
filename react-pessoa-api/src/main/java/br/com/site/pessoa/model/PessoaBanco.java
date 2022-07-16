package br.com.site.pessoa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "PESSOA_BANCO")
public class PessoaBanco implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Id
    @Column(name = "Id_Pessoa_Banco")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoaBanco;

    @Column(name = "Banco")
    private String banco;

    @Column(name = "Dg_Banco")
    private String dgBanco;

    @Column(name = "Agencia")
    private String agencia;

    @Column(name = "dg_Agencia")
    private String dgAgencia;

    @Column(name = "Conta_Corrente")
    private String contaCorrente;

    @Column(name = "Dg_Conta_Corrente")
    private String dgContaCorrente;

    @Column(name = "Usuario_Created")
    private String usuarioCreated;

    @Column(name = "Data_Created")
    private LocalDate dataCreated = LocalDate.now();

    @Column(name = "Usuario_Changed")
    private String usuarioChanged;

    @Column(name = "Data_Changed")
    private LocalDate dataChanged;
}
