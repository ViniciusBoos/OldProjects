package br.com.site.pessoa.modelo;

import javax.persistence.*;

@Entity
@Table(name = "Teste2")
public class Teste2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
