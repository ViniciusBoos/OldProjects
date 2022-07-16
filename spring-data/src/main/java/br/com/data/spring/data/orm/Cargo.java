package br.com.data.spring.data.orm;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cargos")
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descricao;

    @OneToMany(mappedBy = "cargoId")
    private List<Funcionario> funcionarios;

    public Cargo(String descricao) {
        this.descricao = descricao;
    }
    public Cargo() {}

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "[id= " + id + " | " + descricao + "]";
    }
}
