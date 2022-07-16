package br.com.site.pessoa.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    private String email;
    private String username;
    private String password;
    private String enabled;


    public User(){}
    public User(String nome, String email, String senha) {
        this.username = nome;
        this.email = email;
        this.password = senha;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getNome() {
        return username;
    }

    public void setNome(String nome) {
        this.username = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
