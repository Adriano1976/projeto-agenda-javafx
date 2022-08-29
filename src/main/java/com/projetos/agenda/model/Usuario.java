package com.projetos.agenda.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;

    @Column(name = "senha", length = 8, nullable = false)
    private String senha;

    @Column(name = "senha1", length = 8, nullable = false)
    private String senha1;

    public Usuario() {
    }

    public Usuario(Long id, String descricao, String senha, String senha1) {
        this.id = id;
        this.descricao = descricao;
        this.senha = senha;
        this.senha1 = senha1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSenha() {
        return senha.replace(senha, "********");
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha1() {
        return senha1;
    }

    public void setSenha1(String senha1) {
        this.senha1 = senha1;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;

        if (getId() != null ? !getId().equals(usuario.getId()) : usuario.getId() != null) return false;
        if (getDescricao() != null ? !getDescricao().equals(usuario.getDescricao()) : usuario.getDescricao() != null)
            return false;
        if (getSenha() != null ? !getSenha().equals(usuario.getSenha()) : usuario.getSenha() != null) return false;
        return getSenha1() != null ? getSenha1().equals(usuario.getSenha1()) : usuario.getSenha1() == null;
    }

    @Override
    public int hashCode() {
        return getDescricao().hashCode();
    }
}
