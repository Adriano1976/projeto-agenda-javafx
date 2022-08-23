package com.projetos.agenda.controller;

import com.projetos.agenda.util.Uf;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CidadeController implements Initializable, ICadastro {
    @FXML
    private HBox lbTitulo;
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfDescricao;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnExcluir;
    @FXML
    private TextField tfPesquisa;
    @FXML
    private TableView<?> tableView;
    @FXML
    private ComboBox<String> cbUf;
    @FXML
    private TextField tfCep;


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
        cbUf.setItems(Uf.gerarUf());
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
