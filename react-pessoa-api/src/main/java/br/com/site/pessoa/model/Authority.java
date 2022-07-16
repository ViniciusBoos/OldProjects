package br.com.site.pessoa.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "authorities")
public class Authority implements Serializable {

    @Id
    @OneToOne
    private User user;
}
