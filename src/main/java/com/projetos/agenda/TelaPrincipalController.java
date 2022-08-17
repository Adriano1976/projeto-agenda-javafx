package com.projetos.agenda;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaPrincipalController implements Initializable {

    @FXML
    public MenuBar barraDeMenu;
    @FXML
    public Menu menuArquivo;
    @FXML
    public MenuItem menuItemContato;
    @FXML
    public MenuItem menuItemTipoContato;
    @FXML
    public MenuItem menuItemCidade;
    @FXML
    public MenuItem menuItemUsuario;
    @FXML
    public MenuItem menuItemSair;
    @FXML
    public Menu menuRelatorio;
    @FXML
    public MenuItem menuItemContatoGeral;
    @FXML
    public MenuItem menuItemContatoEndereco;
    @FXML
    public MenuItem menuItemContatoTelefone;
    @FXML
    public MenuItem menuItemContatoTipo;
    @FXML
    public Menu menuSobre;
    @FXML
    public MenuItem menuItemSistema;

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
    public void acessarContato(ActionEvent actionEvent) {
    }

    @FXML
    public void acessarTipoContato(ActionEvent actionEvent) {
    }

    @FXML
    public void acessarCidade(ActionEvent actionEvent) {
    }

    @FXML
    public void acessarUsuario(ActionEvent actionEvent) {
    }

    @FXML
    public void acessarSair(ActionEvent actionEvent) {
    }

    @FXML
    public void acessarRelatorioContatoGeral(ActionEvent actionEvent) {
    }

    @FXML
    public void acessarRelatorioContatoEndereco(ActionEvent actionEvent) {
    }

    @FXML
    public void acessarRelatorioContatoTelefone(ActionEvent actionEvent) {
    }

    @FXML
    public void acessarRelatorioContatoTipo(ActionEvent actionEvent) {
    }

    @FXML
    public void acessarSobreSistema(ActionEvent actionEvent) {
    }
}
