package br.com.site.pessoa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "PESSOA_JURIDICA")
@Getter
@Setter
public class PessoaJuridica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoaJuridica;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_Ramo_Atividade")
    private RamoAtividade ramoAtividade;

    @Column(name = "Nome_Fantasia")
    private String nomeFantasia;

    @Column(name = "Inscricao_Estadual")
    private String inscricaoEstadual;

    @Column(name = "Faturamento")
    private Double faturamento;

    @Column(name = "Data_Fundacao")
    private LocalDate dataFundacao;

    @Column(name = "Nire")
    private String nire;

    @Column(name = "Nire_Data")
    private LocalDate nireData;

    @Column(name = "Cnae")
    private String cnae;

    @Column(name = "Usuario_Created")
    private String usuarioCreated;

    @Column(name = "Data_Created")
    private LocalDate dataCreated = LocalDate.now();

    @Column(name = "Usuario_Changed")
    private String usuarioChanged;

    @Column(name = "Data_Changed")
    private LocalDate dataChanged;
}
