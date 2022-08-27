package com.projetos.agenda.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cidade")
public class Cidade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;

    @Column(name = "uf", length = 2, nullable = false)
    private String uf;

    @Column(name = "cep", length = 8, nullable = false)
    private Long cep;

    public Cidade() {
    }

    public Cidade(Long id, String descricao, String uf, Long cep) {
        this.id = id;
        this.descricao = descricao;
        this.uf = uf;
        this.cep = cep;
    }

    public Cidade(Cidade cidade) {
        this.id = cidade.getId();
        this.descricao = cidade.getDescricao();
        this.uf = cidade.getUf();
        this.cep = cidade.getCep();
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Long getCep() {
        return cep;
    }

    public void setCep(Long cep) {
        this.cep = cep;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
