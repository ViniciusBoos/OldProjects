package br.com.site.pessoa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "PAIS")
public class Pais {

    @Id
    @Column(name = "Codigo")
    private Long codigo;

    @Column(name = "Nome")
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "pais",fetch = FetchType.LAZY)
    private List<Estado> estados;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }
}
