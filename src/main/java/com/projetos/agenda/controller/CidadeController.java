package com.projetos.agenda.controller;

import com.projetos.agenda.dao.CrudGenericoDao;
import com.projetos.agenda.model.Cidade;
import com.projetos.agenda.util.Alerta;
import com.projetos.agenda.util.Uf;
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

public class CidadeController implements Initializable, ICadastro {
    @FXML
    private Label lbTitulo;
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
    private TableView<Cidade> tableView;
    @FXML
    private ComboBox<String> cbUf;
    @FXML
    private TextField tfCep;

    private final CrudGenericoDao<Cidade> dao = new CrudGenericoDao<>();
    private Cidade objetoSelecionado = new Cidade();
    private final ObservableList<Cidade> observableList = FXCollections.observableArrayList();


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
        lbTitulo.setText("Cadastro de Cidade");
        criarColunasTabela();
        atualizarTabela();
        setCamposFormulario();
        cbUf.setItems(Uf.gerarUf());
    }

    @FXML
    public void incluirResgistro(ActionEvent actionEvent) {
        limparCamposFormulario();
    }

    @FXML
    public void salvarResgistro(ActionEvent actionEvent) {
        Cidade objeto = new Cidade();

        if (objetoSelecionado != null) {
            objeto.setId(objetoSelecionado.getId());
        }

        objeto.setDescricao(tfDescricao.getText());
        objeto.setCep(Long.parseLong(tfCep.getText()));
        objeto.setUf(cbUf.getValue());

        if (dao.salvar(objeto)) {
            Alerta.msgInformacao("Registro gravado com sucesso!");
        } else {
            Alerta.msgInformacao("Ocorreu um erro ao tentar gravar o registro!");
        }
        atualizarTabela();
        limparCamposFormulario();
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
    public void clicarTabela(MouseEvent mouseEvent) {
        setCamposFormulario();
    }

    @FXML
    public void moverTabela(KeyEvent keyEvent) {
        setCamposFormulario();
    }

    @Override
    public void criarColunasTabela() {
        // Colunas que aparecerão na tabela.
        TableColumn<Cidade, Long> columaId = new TableColumn<>("ID");
        columaId.setMinWidth(40);
        columaId.setMaxWidth(40);
        TableColumn<Cidade, String> colunaDescricao = new TableColumn<>("DESCRIÇÃO");
        TableColumn<Cidade, String> colunaUf = new TableColumn<>("UF");
        columaId.setMinWidth(40);
        columaId.setMaxWidth(40);
        TableColumn<Cidade, String> colunaCep = new TableColumn<>("CEP");

        // Redimensionamento as colunas automaticamente.
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Adicionando as colunas e seus títulos na tabela
        tableView.getColumns().addAll(columaId, colunaDescricao, colunaUf, colunaCep);

        // Relacionar cada coluna a seu atributo no Model.
        columaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaUf.setCellValueFactory(new PropertyValueFactory<>("uf"));
        colunaCep.setCellValueFactory(new PropertyValueFactory<>("cep"));
    }

    @Override
    public void atualizarTabela() {
        observableList.clear();
        List<Cidade> lista = dao.consultar(tfPesquisa.getText(), "Cidade");
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
            tfCep.setText(String.valueOf(objetoSelecionado.getCep()));
            cbUf.setValue(objetoSelecionado.getUf());
        }
    }

    @Override
    public void limparCamposFormulario() {
        objetoSelecionado = null;
        tfId.clear();
        tfDescricao.clear();
        tfCep.clear();
        cbUf.getSelectionModel().select(-1);
        tfDescricao.requestFocus();
    }
}
