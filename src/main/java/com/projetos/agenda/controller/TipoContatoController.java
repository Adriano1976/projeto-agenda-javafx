package com.projetos.agenda.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TipoContatoController implements Initializable {
    public TextField tfId;
    public TextField tfDescricao;
    public Button btnNovo;
    public Button btnSalvar;
    public Button btnExcluir;
    public TextField tfPesquisa;
    public TableView tableView;

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

    public void incluirResgistro(ActionEvent actionEvent) {
    }

    public void salvarResgistro(ActionEvent actionEvent) {
    }

    public void excluirResgistro(ActionEvent actionEvent) {
    }

    public void pesquisar(ActionEvent actionEvent) {
    }
}
