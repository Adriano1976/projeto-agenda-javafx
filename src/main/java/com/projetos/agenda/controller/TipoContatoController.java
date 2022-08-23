package com.projetos.agenda.controller;

import com.projetos.agenda.dao.TipoContatoDao;
import com.projetos.agenda.model.TipoContato;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
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

    private final TipoContatoDao dao = new TipoContatoDao();
    private final ObservableList<TipoContato> observableList = FXCollections.observableArrayList();

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
        atualizarTabela();
    }

    @FXML
    public void incluirResgistro(ActionEvent actionEvent) {
    }

    @FXML
    public void salvarResgistro(ActionEvent actionEvent) {
        TipoContato tipoContato = new TipoContato();

        tipoContato.setDescricao(tfDescricao.getText());
        dao.salvar(tipoContato);
        atualizarTabela();
    }

    @FXML
    public void excluirResgistro(ActionEvent actionEvent) {
    }

    @FXML
    public void filtrarRegistro(KeyEvent keyEvent) {
        atualizarTabela();
    }

    public void clicarTabela(MouseEvent mouseEvent) {
        setCamposFormulario();
    }

    @Override
    public void criarColunasTabela() {
        TableColumn<TipoContato, Long> columaId = new TableColumn<>("ID");
        columaId.setMinWidth(40);
        columaId.setMaxWidth(40);
        TableColumn<TipoContato, String> colunaDescricao = new TableColumn<>("DESCRIÇÃO");

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(columaId, colunaDescricao);

        columaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
    }

    @Override
    public void atualizarTabela() {
        observableList.clear();
        List<TipoContato> listaTipos = dao.consultar(tfPesquisa.getText());
        observableList.addAll(listaTipos);
        tableView.getItems().setAll(observableList);
        tableView.getSelectionModel().selectFirst();
    }

    @Override
    public void setCamposFormulario() {
        TipoContato objetoSelecionado = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
        tfId.setText(String.valueOf(objetoSelecionado.getId()));
        tfDescricao.setText(objetoSelecionado.getDescricao());
    }

    @Override
    public void limparCamposFormulario() {

    }
}
