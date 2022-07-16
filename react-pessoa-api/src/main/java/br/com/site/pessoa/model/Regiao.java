package br.com.site.pessoa.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "REGIAO")
public class Regiao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Regiao")
    private Long idRegiao;

    @Column(name = "Descricao")
    private String descricao;

    @Column(name = "Usuario_Created")
    private String usuarioCreated;

    @Column(name = "Data_Created")
    private LocalDate dataCreated = LocalDate.now();

    @Column(name = "Usuario_Changed")
    private String usuarioChanged;

    @Column(name = "Data_Changed")
    private LocalDate dataChanged;
}
