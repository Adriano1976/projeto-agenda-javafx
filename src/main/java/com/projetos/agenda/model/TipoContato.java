package com.projetos.agenda.model;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * <p>Classe principal responsável pelos atributos, métodos construtores e métodos de acesso.
 * Ela também serve como modelo para o mapeamento da classe <code>TipoContato</code> pelo
 * Hibernade na implementação e manutenção da tabela na base de dados.</p>
 *
 * @author Adriano Santos
 *
 */
@Entity
@Table(name = "tipo_contato")
public class TipoContato implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;

    public TipoContato() {
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

    /**
     * @return Retorna uma ‘string’ com o nome do contato.
     */
    @Override
    public String toString() {
        return descricao;
    }
}
