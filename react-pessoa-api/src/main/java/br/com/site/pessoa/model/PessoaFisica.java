package br.com.site.pessoa.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "PESSOA_FISICA")
@Getter
public class PessoaFisica implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPessoaFisica;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profissao")
    private Profissao Profisssao;

    @Column(name="Rg")
    private String rg;

    @Column(name="Orgao_Emissor_Rg")
    private String orgaoEmissorRg;

    @Column(name="Estado_Civil")
    @Enumerated(EnumType.STRING)
    private EstadoCivil estadoCivil;

    @Column(name="Sexo")
    private String sexo;

    @Column(name = "Data_Nascimento")
    private String dataNascimento;

    @Column(name="Nome_Pai")
    private String nomePai;

    @Column(name = "Nome_Mae")
    private String nomeMae;

    @Column(name="Nome_Empresa")
    private String nomeEmpresa;

    @Column(name="Data_Adimissao_Emp")
    private LocalDate dataAdmissaoEmp;

    @Column(name="Rendimento")
    private Double rendimento;

    @Column(name="Usuario_Created")
    private String usuarioCreated;

    @Column(name="Data_Created")
    private LocalDate dataCreated = LocalDate.now();

    @Column(name="Usuario_Changed")
    private String usuarioChanged;

    @Column(name="Data_Changed")
    private LocalDate dataChanged;

    public Long getIdPessoaFisica() {
        return idPessoaFisica;
    }

    public void setIdPessoaFisica(Long idPessoaFisica) {
        this.idPessoaFisica = idPessoaFisica;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Profissao getProfisssao() {
        return Profisssao;
    }

    public void setProfisssao(Profissao profisssao) {
        Profisssao = profisssao;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getOrgaoEmissorRg() {
        return orgaoEmissorRg;
    }

    public void setOrgaoEmissorRg(String orgaoEmissorRg) {
        this.orgaoEmissorRg = orgaoEmissorRg;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public LocalDate getDataAdmissaoEmp() {
        return dataAdmissaoEmp;
    }

    public void setDataAdmissaoEmp(LocalDate dataAdmissaoEmp) {
        this.dataAdmissaoEmp = dataAdmissaoEmp;
    }

    public Double getRendimento() {
        return rendimento;
    }

    public void setRendimento(Double rendimento) {
        this.rendimento = rendimento;
    }

    public String getUsuarioCreated() {
        return usuarioCreated;
    }

    public void setUsuarioCreated(String usuarioCreated) {
        this.usuarioCreated = usuarioCreated;
    }

    public LocalDate getDataCreated() {
        return dataCreated;
    }

    public void setDataCreated(LocalDate dataCreated) {
        this.dataCreated = dataCreated;
    }

    public String getUsuarioChanged() {
        return usuarioChanged;
    }

    public void setUsuarioChanged(String usuarioChanged) {
        this.usuarioChanged = usuarioChanged;
    }

    public LocalDate getDataChanged() {
        return dataChanged;
    }

    public void setDataChanged(LocalDate dataChanged) {
        this.dataChanged = dataChanged;
    }
}
