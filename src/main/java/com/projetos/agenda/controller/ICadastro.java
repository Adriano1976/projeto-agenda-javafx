package com.projetos.agenda.controller;

/**
 * ‘Interface’ responsável pela inicialização dos métodos para os controladores dos formulários.
 *
 * @author Adriano Santos
 */
public interface ICadastro {
    public abstract void criarColunasTabela();

    public abstract void atualizarTabela();

    public abstract void setCamposFormulario();

    public abstract void limparCamposFormulario();
}
