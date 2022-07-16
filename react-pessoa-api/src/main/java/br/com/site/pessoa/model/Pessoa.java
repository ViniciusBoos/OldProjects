package br.com.site.pessoa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "PESSOA")
@Getter @Setter
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Pessoa")
    private Long idPessoa;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regiao_id")
    private Regiao regiao;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_grupo_id")
    private PessoaGrupo pessoaGrupo;

    @JsonIgnore
    @OneToMany(mappedBy = "pessoasRel", fetch = FetchType.LAZY)
    private List<Pessoa> pessoasRel;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pais_codigo")
    private Pais pais;

    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;

    @Column(name = "Cod_Pessoa")
    private Long codPessoa;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Email")
    private String email;

    @Column(name = "Site")
    private String site;

    @Column(name = "Bloqueado")
    private Character bloqueado;

    @Column(name = "Data_Venc_Cadastro")
    private LocalDate dataVencCadastro;

    @Column(name = "Data_Ultima_Venda")
    private LocalDate dataUltimaVenda;

    @Column(name = "Obs_Venda")
    private String obsVenda;

    @Column(name = "Media_Atrazo")
    private LocalDate mediaAtrazo;

    @Column(name = "Valor_Minimo_Faturamento")
    private Double valorMinimoFaturamento;

    @Column(name = "Limite_Credito")
    private Double limiteCredito;

    @Column(name = "Motivo_Bloqueio")
    private String motivoBloqueio;

    @Column(name = "Id_Empresa_Cadastrou")
    private Long idEmpresaCadastrou;

    @Column(name = "Spc_serasa")
    private Character spcSerasa;

    @Column(name = "Nr_Cartao_Fidelidade")
    private String nrCartaoFidelidade;

    @Column(name = "Data_Created")
    private LocalDate dataCreated = LocalDate.now();

    @Column(name = "Nao_Bloq_Automatico")
    private Character NaoBloqAutomatico;



}
