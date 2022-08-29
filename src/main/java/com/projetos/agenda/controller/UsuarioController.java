package com.projetos.agenda.controller;

import com.projetos.agenda.dao.CrudGenericoDao;
import com.projetos.agenda.dao.UsuarioDao;
import com.projetos.agenda.model.Usuario;
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
import java.util.Objects;
import java.util.ResourceBundle;

public class UsuarioController implements Initializable, ICadastro {
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
    public TableView<Usuario> tableView;
    @FXML
    public PasswordField pfSenha;
    @FXML
    public PasswordField pfSenha1;

    private final CrudGenericoDao<Usuario> dao = new CrudGenericoDao<>(Usuario.class);
    private final UsuarioDao usuarioDao = new UsuarioDao();
    private final ObservableList<Usuario> observableList = FXCollections.observableArrayList();
    private Usuario objetoSelecionado = new Usuario();



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
        lbTitulo.setText("Cadastro de Usuário");
        criarColunasTabela();
        atualizarTabela();
        setCamposFormulario();
    }

    @FXML
    public void incluirResgistro(ActionEvent actionEvent) {
        limparCamposFormulario();
    }

    @FXML
    public void salvarResgistro(ActionEvent actionEvent) {

        if (ValidarCampo.checarCampoVazio(tfDescricao, pfSenha, pfSenha1)) {
            Usuario usuario = new Usuario();

            if (objetoSelecionado != null) {
                usuario.setId(objetoSelecionado.getId());
            }

            usuario.setDescricao(tfDescricao.getText());
            usuario.setSenha(pfSenha.getText());
            usuario.setSenha1(pfSenha1.getText());


            if (!Objects.equals(pfSenha.getText(), pfSenha1.getText())) {
                Alerta.msgInformacao("Senha de confirmação está diferente da primeira senha!");
            } else {
                if (dao.salvar(usuario) && usuarioDao.liberarInclusao(usuario)) {
                    Alerta.msgInformacao("Registro gravado com sucesso!");
                } else {
                    Alerta.msgInformacao("Ocorreu um erro ao tentar gravar o registro!");
                }
            }
            atualizarTabela();
            limparCamposFormulario();
        } else {
            Alerta.msgInformacao("Favor, preencher o(s) campo(s) obrigatório(s)");
        }
    }

    @FXML
    public void excluirResgistro(ActionEvent actionEvent) {
        if (Alerta.msgConfirmaExclusao(tfDescricao.getText())) {
            dao.excluir(objetoSelecionado);
            limparCamposFormulario();
            atualizarTabela();
            Alerta.msgInformacao("Registro excluido com sucesso!");
        }
    }

    @FXML
    public void filtrarRegistro(KeyEvent keyEvent) {
        atualizarTabela();
    }

    @FXML
    public void moverTabela(KeyEvent keyEvent) {
        setCamposFormulario();
    }

    @FXML
    public void clicarTabela(MouseEvent mouseEvent) {
        setCamposFormulario();
    }

    @Override
    public void criarColunasTabela() {
        TableColumn<Usuario, Long> columaId = new TableColumn<>("ID");
        columaId.setMinWidth(40);
        columaId.setMaxWidth(40);
        TableColumn<Usuario, String> colunaDescricao = new TableColumn<>("DESCRIÇÃO");
        TableColumn<Usuario, String> colunaSenha = new TableColumn<>("SENHA 1");
        TableColumn<Usuario, String> colunaSenha1 = new TableColumn<>("SENHA 2");

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(columaId, colunaDescricao, colunaSenha, colunaSenha1);

        columaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaSenha.setCellValueFactory(new PropertyValueFactory<>("senha"));
        colunaSenha1.setCellValueFactory(new PropertyValueFactory<>("senha1"));

    }

    @Override
    public void atualizarTabela() {
        observableList.clear();
        List<Usuario> lista = dao.consultar(tfPesquisa.getText());
        observableList.addAll(lista);
        tableView.getItems().setAll(observableList);
        tableView.getSelectionModel().selectFirst();
    }

    @Override
    public void setCamposFormulario() {
        if (!tableView.getItems().isEmpty()) {
            objetoSelecionado = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());

            tfId.setText(String.valueOf(objetoSelecionado.getId()));
            tfDescricao.setText(objetoSelecionado.getDescricao());
            pfSenha.setText(objetoSelecionado.getSenha());
            pfSenha1.setText(objetoSelecionado.getSenha1());
        }
    }

    @Override
    public void limparCamposFormulario() {
        objetoSelecionado = null;
        tfId.clear();
        tfDescricao.clear();
        pfSenha.clear();
        pfSenha1.clear();
        tfDescricao.requestFocus();
    }
}
