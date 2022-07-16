package br.com.site.pessoa.modelo;

import javax.persistence.*;

@Entity
@Table(name = "Teste1")
public class Teste1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "teste2_Id", referencedColumnName = "id")
    private Teste2 teste2;

}
