package br.com.site.pessoa.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PROFISSAO")
public class Profissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Profissao")
    private Long idProfissao;

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
