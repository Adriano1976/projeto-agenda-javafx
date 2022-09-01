package com.projetos.agenda.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * <p>Classe principal responsável pelos atributos, métodos construtores e métodos de acesso.
 * Ela também serve como modelo para o mapeamento da classe <code>Contato</code> pelo
 * Hibernade na implementação e manutenção da tabela Contato.</p>
 *
 * @author Adriano Santos
 **/

@Entity
@Table(name = "contato")
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;

    @Column(name = "sobrenome", length = 100, nullable = false)
    private String sobrenome;

    @Column(name = "endereco", length = 100)
    private String endereco;

    @Column(name = "bairro", length = 100)
    private String bairro;

    @Column(name = "numero_residencia", length = 15)
    private int numero;

    @OneToOne
    private Cidade Cidade;

    @Column(name = "email", length = 50, nullable = false)
    private String email;

    @Column(name = "data_nascimento")
    private LocalDate nascimento;

    @Column(name = "telefone1", length = 11, nullable = false)
    private Long telefone1;

    @Column(name = "telefone2", length = 11, nullable = false)
    private Long telefone2;

    @OneToOne
    private TipoContato TipoContato;

    @Column(nullable = false)
    private boolean ativo;

    @Column(length = 1)
    private String sexo;

    /**
     * <h3>Método Construtor básico</h3>
     */
    public Contato() {
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

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Cidade getCidade() {
        return Cidade;
    }

    public void setCidade(Cidade Cidade) {
        this.Cidade = Cidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public Long getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(Long telefone1) {
        this.telefone1 = telefone1;
    }

    public Long getTelefone2() {
        return telefone2;
    }

    public void setTelefone2(Long telefone2) {
        this.telefone2 = telefone2;
    }

    public TipoContato getTipoContato() {
        return TipoContato;
    }

    public void setTipoContato(TipoContato TipoContato) {
        this.TipoContato = TipoContato;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * <p>Método responsável em informar os objetos na classe Contato.</p>
     *
     * @return Retorna todos os dados de contato do tipo <code>String</code> ao ser chamado.
     */
    @Override
    public String toString() {
        return "Contato: " + id +
                ", Nome: " + descricao +
                ", Sobrenome: " + sobrenome +
                ", Endereço: " + endereco +
                ", Bairro: " + bairro +
                ", Número: " + numero +
                ", Cidade: " + Cidade +
                ", Email: " + email +
                ", Data de Nascimento: " + nascimento +
                ", Telefone 1: " + telefone1 +
                ", Telefone 2: " + telefone2 +
                ", Tipo de Contato: " + TipoContato +
                ", Status: " + ativo +
                ", Sexo: " + sexo;
    }
}
