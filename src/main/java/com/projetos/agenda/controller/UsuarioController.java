package com.projetos.agenda.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class UsuarioController implements Initializable, ICadastro {
    @FXML
    public HBox lbTitulo;
    @FXML
    public TextField tfId;
    @FXML
    public TextField tfDescricao;
    @FXML
    public Button btnNovo;
    @FXML
    public Button btnSalvar;
    @FXML
    public Button btnExcluir;
    @FXML
    public TextField tfPesquisa;
    @FXML
    public TableView tableView;
    @FXML
    public PasswordField pfSenha;

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     *                  the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void incluirResgistro(ActionEvent actionEvent) {
    }

    @FXML
    public void salvarResgistro(ActionEvent actionEvent) {
    }

    @FXML
    public void excluirResgistro(ActionEvent actionEvent) {
    }

    @FXML
    public void pesquisar(ActionEvent actionEvent) {
    }

    @Override
    public void criarColunasTabela() {

    }

    @Override
    public void atualizarTabela() {

    }

    @Override
    public void setCamposFormulario() {

    }

    @Override
    public void limparCamposFormulario() {

    }
}
