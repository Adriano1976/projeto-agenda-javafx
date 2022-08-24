package com.projetos.agenda.controller;

import com.projetos.agenda.dao.ComboBoxGenericoDao;
import com.projetos.agenda.dao.CrudGenericoDao;
import com.projetos.agenda.model.Cidade;
import com.projetos.agenda.model.Contato;
import com.projetos.agenda.model.TipoContato;
import com.projetos.agenda.util.Alerta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class ContatoController implements Initializable, ICadastro {
    @FXML
    public Label lbTitulo;
    @FXML
    public TextField tfId;
    @FXML
    public Button btnNovo;
    @FXML
    public Button btnSalvar;
    @FXML
    public Button btnExcluir;
    @FXML
    public TextField tfPesquisa;
    @FXML
    public TableView<Contato> tableView;
    @FXML
    public TextField tfDescricao;
    @FXML
    public TextField tfEndereco;
    @FXML
    public TextField tfNumero;
    @FXML
    public ComboBox<Cidade> cbCidade;
    @FXML
    public TextField tfUf;
    @FXML
    public TextField tfCep;
    @FXML
    public TextField tfTelefone1;
    @FXML
    public TextField tfTelefone2;
    @FXML
    public ComboBox<TipoContato> cbTipoContato;
    @FXML
    public CheckBox ckAtivo;
    @FXML
    public TextField tfEmail;
    @FXML
    public RadioButton rbFeminino;
    @FXML
    public ToggleGroup sexo;
    @FXML
    public RadioButton rbMasculino;
    @FXML
    public DatePicker dpNascimento;

    private final ComboBoxGenericoDao<TipoContato> comboBoxTipoContatoDao = new ComboBoxGenericoDao<>();
    private final ComboBoxGenericoDao<Cidade> comboBoxCidadeDao = new ComboBoxGenericoDao<>();
    private final CrudGenericoDao<Contato> dao = new CrudGenericoDao<>();
    private List<Contato> lista;
    private final ObservableList<Contato> observableList = FXCollections.observableArrayList();
    private final Contato objetoSelecionado = new Contato();



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
        cbTipoContato.setItems(comboBoxTipoContatoDao.comboBox("TipoContato"));
        cbCidade.setItems(comboBoxCidadeDao.comboBox("Cidade"));

        cbCidade.setOnAction(actionEvent -> {
            tfUf.setText(cbCidade.getSelectionModel().getSelectedItem().getUf());
            tfCep.setText(String.valueOf(cbCidade.getSelectionModel().getSelectedItem().getCep()));
        });
    }

    @FXML
    public void incluirResgistro(ActionEvent actionEvent) {
    }

    @FXML
    public void salvarResgistro(ActionEvent actionEvent) {
        Contato contato = new Contato();

        contato.setDescricao(tfDescricao.getText());
        contato.setEndereco(tfEndereco.getText());
        contato.setNumero(Integer.parseInt(tfNumero.getText()));
        contato.setCidade(cbCidade.getSelectionModel().getSelectedItem());
        contato.setTipoContato(cbTipoContato.getSelectionModel().getSelectedItem());
        contato.setEmail(tfEmail.getText());
        contato.setTelefone1(Long.parseLong(tfTelefone1.getText()));
        contato.setTelefone2(Long.parseLong(tfTelefone2.getText()));
        LocalDate dataNascimento = dpNascimento.getValue();
        contato.setNascimento(dataNascimento);

        contato.setAtivo(ckAtivo.isSelected());

        contato.setSexo(rbMasculino.isSelected() ? "M" : "F");

        if (dao.salvar(contato)) {
            Alerta.msgInformacao("Registro gravado com sucesso!");
        } else {
            Alerta.msgInformacao("Ocorreu um erro ao tentar gravar o registro!");
        }
    }

    @FXML
    public void excluirResgistro(ActionEvent actionEvent) {
    }

    @FXML
    public void filtrarRegistro(KeyEvent keyEvent) {
    }

    @FXML
    public void moverTabela(KeyEvent keyEvent) {
    }

    @FXML
    public void clicarTabela(MouseEvent mouseEvent) {
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
