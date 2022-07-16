package br.com.site.pessoa.model;

import javax.persistence.*;

@Entity
@Table(name = "LOGRADOURO")
public class Logradouro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lograId;

    @Column(name = "Tipo")
    private String descricao;
}
