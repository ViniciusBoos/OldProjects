package br.com.site.pessoa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "PESSOA_SOCIO")
public class PessoaSocio implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa_socio")
    private Pessoa pessoaSocio;

    @Column(name = "Perc_Cap_Total")
    private String percCapTotal;

    @Column(name = "Perc_Cap_Votante")
    private String percCapVotante;

    @Column(name = "Usuario_Created")
    private String usuarioCreated;

    @Column(name = "Data_Created")
    private LocalDate dataCreated = LocalDate.now();

    @Column(name = "Usuario_Changed")
    private String usuarioChanged;

    @Column(name = "Data_Changed")
    private LocalDate dataChanged;
}

