package br.com.site.pessoa.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "PESSOA_GRUPO")
public class PessoaGrupo implements Serializable {

    @Id
    @Column(name = "Id_Grupo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGrupo;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Ativo")
    private String ativo;

    @Column(name = "Usuario_Created")
    private String usuarioCreated;

    @Column(name = "Data_Created")
    private LocalDate dataCreated = LocalDate.now();

    @Column(name = "Usuario_Changed")
    private String usuarioChanged;

    @Column(name = "Data_Changed")
    private LocalDate dataChanged;
}
