package com.projetos.agenda.controller;

import com.projetos.agenda.dao.TipoContatoDao;
import com.projetos.agenda.model.TipoContato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class TipoContatoController implements Initializable, ICadastro {
    @FXML
    public Label lbTitulo;
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
    public TableView<TipoContato> tableView;

    TipoContatoDao dao = new TipoContatoDao();

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
        lbTitulo.setText("Cadastro de Tipo de Contato");
        criarColunasTabela();
    }

    @FXML
    public void incluirResgistro(ActionEvent actionEvent) {
    }

    @FXML
    public void salvarResgistro(ActionEvent actionEvent) {
        TipoContato tipoContato = new TipoContato();

        tipoContato.setDescricao(tfDescricao.getText());
        dao.salvar(tipoContato);
    }

    @FXML
    public void excluirResgistro(ActionEvent actionEvent) {
    }

    @FXML
    public void pesquisar(ActionEvent actionEvent) {
    }

    @Override
    public void criarColunasTabela() {
        TableColumn<TipoContato, Long> columaId = new TableColumn<>("ID");
        TableColumn<TipoContato, String> colunaDescricao = new TableColumn<>("DESCRIÇÃO");

        tableView.getColumns().addAll(columaId, colunaDescricao);

        columaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
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
