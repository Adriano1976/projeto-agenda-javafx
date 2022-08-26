package com.projetos.agenda.controller;

import com.projetos.agenda.dao.CrudGenericoDao;
import com.projetos.agenda.dao.TipoContatoDao;
import com.projetos.agenda.model.TipoContato;
import com.projetos.agenda.util.Alerta;
import com.projetos.agenda.util.ValidarCampo;
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

    private final CrudGenericoDao<TipoContato> dao = new CrudGenericoDao<>();
    private TipoContato objetoSelecionado = new TipoContato();
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
        setCamposFormulario();

//        ValidarCampo.checarCampoVazio(tfDescricao);
    }

    @FXML
    public void incluirResgistro(ActionEvent actionEvent) {
        limparCamposFormulario();
    }

    @FXML
    public void salvarResgistro(ActionEvent actionEvent) {

        if (ValidarCampo.checarCampoVazio(tfDescricao)) {
            TipoContato objeto = new TipoContato();

            if (objetoSelecionado != null) {
                objeto.setId(objetoSelecionado.getId());
            }

            objeto.setDescricao(tfDescricao.getText());
            if (dao.salvar(objeto)) {
                Alerta.msgInformacao("Registro gravado com sucesso!");
            } else {
                Alerta.msgInformacao("Ocorreu um erro ao tentar gravar o registro!");
            }
            atualizarTabela();
            limparCamposFormulario();
        } else {
            Alerta.msgInformacao("Favor, preencher o(s) campo(s) obrigatório(s)");
        }
    }

    @FXML
    public void excluirResgistro(ActionEvent actionEvent) {
        if (TipoContatoDao.liberaExclusao(objetoSelecionado.getId())) {

            if (Alerta.msgConfirmaExclusao(tfDescricao.getText())) {
                dao.excluir(objetoSelecionado);
                limparCamposFormulario();
                atualizarTabela();
                Alerta.msgInformacao("Registro excluido com sucesso!");
            }
        } else {
            Alerta.msgInformacao("Exclusão não permitido. \nTipo de Contato utilizada no cadastro de Contato!");
        }
    }

    @FXML
    public void filtrarRegistro(KeyEvent keyEvent) {
        atualizarTabela();
    }

    @FXML
    public void clicarTabela(MouseEvent mouseEvent) {
        setCamposFormulario();
    }

    @FXML
    public void moverTabela(KeyEvent keyEvent) {
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
        List<TipoContato> lista = dao.consultar(tfPesquisa.getText(), "TipoContato");
        observableList.addAll(lista);
        tableView.getItems().setAll(observableList);
        tableView.getSelectionModel().selectFirst();
    }

    @Override
    public void setCamposFormulario() {
        objetoSelecionado = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());

        tfId.setText(String.valueOf(objetoSelecionado.getId()));
        tfDescricao.setText(objetoSelecionado.getDescricao());
    }

    @Override
    public void limparCamposFormulario() {
        objetoSelecionado = null;
        tfId.clear();
        tfDescricao.clear();
        tfDescricao.requestFocus();
    }
}
