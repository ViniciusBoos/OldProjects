package br.com.site.pessoa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "PESSOA_OBS")
public class PessoaObs implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Id
    @Column(name = "Id_Pessoa_Obs")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoaObs;

    @Column(name = "Observacao")
    private String observacao;

    @Column(name = "Usuario_Created")
    private String usuarioCreated;

    @Column(name = "Data_Created")
    private LocalDate dataCreated = LocalDate.now();

    @Column(name = "Usuario_Changed")
    private String usuarioChanged;

    @Column(name = "Data_Changed")
    private LocalDate dataChanged;
}
