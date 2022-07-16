package br.com.site.pessoa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "PESSOA_ENDERECO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter @Setter
public class PessoaEndereco implements Serializable {

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @Id
    @Column(name = "Id_Endereco")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndereco;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "estado_sigla")
    private Estado estado;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cidage_codigo")
    private Cidade cidade;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_logradouro")
    private Logradouro logradouro;

    @Column(name = "cep")
    private String cep;

    @Column(name = "Rua")
    private String rua;

    @Column(name = "Fachada")
    private String fachada;

    @Column(name = "Complemento")
    private String Complemento;

    @Column(name = "Bairro")
    private String bairro;

    @Column(name = "Inscricao_Estadual")
    private String InscricaoEstadual;

    @Column(name = "Inscricao_Municipal")
    private String InscricaoMunicipal;

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
