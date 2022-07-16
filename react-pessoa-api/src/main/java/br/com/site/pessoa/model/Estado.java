package br.com.site.pessoa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ESTADO")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
public class Estado {

    @Id
    @Column(name = "Sigla")
    private String sigla;

    @Column(name = "Regiao")
    private String regiao;

    @Column(name = "Nome")
    private String nome;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pais_codigo", referencedColumnName = "codigo")
    private Pais pais;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "estado")
    private List<Cidade> cidades;

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
}
