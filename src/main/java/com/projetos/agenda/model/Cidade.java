package com.projetos.agenda.model;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 * <p>Classe principal responsável pelos atributos, métodos construtores e métodos de acesso.
 * Ela também serve como modelo para o mapeamento da classe <code>Cidade</code> pelo Hibernate na implementação
 * e manutenção da tabela Cidade.</p>
 *
 * @author Adriano Santos
 */
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

    /**
     * <h3>Método construtor básico.</h3>
     */
    public Cidade() {
    }

    /**
     * <h3>Método construtor com parâmetro</h3>
     *
     * @param id        Código de identificação da cidade
     * @param descricao Nome da cidade
     * @param uf        Estado da cidade
     * @param cep       Cep da cidade
     */
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

    /**
     * <p>Método responsável em informar os objetos na classe Cidade.</p>
     *
     * @return Retorna todos os dados de contato do tipo <code>String</code> ao ser chamado.
     */
    @Override
    public String toString() {
        return descricao;
    }
}
