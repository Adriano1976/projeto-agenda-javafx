package com.projetos.agenda.controller;

import com.projetos.agenda.dao.ComboBoxGenericoDao;
import com.projetos.agenda.dao.ContatoDao;
import com.projetos.agenda.dao.CrudGenericoDao;
import com.projetos.agenda.model.Cidade;
import com.projetos.agenda.model.Contato;
import com.projetos.agenda.model.TipoContato;
import com.projetos.agenda.util.*;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
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
    public TextField tfSobrenome;
    @FXML
    public TextField tfEndereco;
    @FXML
    public TextField tfBairro;
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
    private final CrudGenericoDao<Contato> dao = new CrudGenericoDao<>(Contato.class);
    private final ContatoDao contatoDao = new ContatoDao();
    protected final RelatorioContato relatorioContato = new RelatorioContato();
    private final ObservableList<Contato> observableList = FXCollections.observableArrayList();

    private Contato objetoSelecionado = new Contato();

    public ContatoController() {
    }

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
        lbTitulo.setText("Cadastro de Contato");
        criarColunasTabela();
        atualizarTabela();
        setCamposFormulario();

        var cb = cbCidade.getSelectionModel().getSelectedItem();
        if (cb != null) {
            tfUf.setText(cb.getUf());
            tfCep.setText(String.valueOf(cb.getCep()));
        }

        cbTipoContato.setItems(comboBoxTipoContatoDao.comboBox(TipoContato.class));
        cbCidade.setItems(comboBoxCidadeDao.comboBox(Cidade.class));

        cbCidade.setOnAction(actionEvent -> {
            var c = cbCidade.getSelectionModel().getSelectedItem();
            if (c != null) {
                tfUf.setText(c.getUf());
                tfCep.setText(String.valueOf(c.getCep()));
            }
        });

        ckAtivo.setSelected(true);

        MascaraCampo.mascaraNumero(tfNumero);
        MascaraCampo.mascaraTelefone(tfTelefone1);
    }

    @FXML
    public void incluirResgistro(ActionEvent actionEvent) {
        limparCamposFormulario();
        tfUf.clear();
        tfCep.clear();
    }

    @FXML
    public void salvarResgistro(ActionEvent actionEvent) {

        if (ValidarCampo.checarCampoVazio(tfDescricao, tfSobrenome, tfEndereco, tfBairro,
                tfNumero, tfEmail, tfTelefone1, tfTelefone2, dpNascimento)) {

            Contato contato = new Contato();

            if (objetoSelecionado != null) {
                contato.setId(objetoSelecionado.getId());
            }

            contato.setDescricao(tfDescricao.getText());
            contato.setSobrenome(tfSobrenome.getText());
            contato.setEndereco(tfEndereco.getText());
            contato.setBairro(tfBairro.getText());
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

            String[] lines = new String[]{
                    String.valueOf(contato.getId()),
                    String.valueOf(contato.getDescricao()),
                    String.valueOf(contato.getSobrenome()),
                    String.valueOf(contato.getEndereco()),
                    String.valueOf(contato.getBairro()),
                    String.valueOf(contato.getNumero()),
                    String.valueOf(contato.getCidade()),
                    String.valueOf(contato.getTipoContato()),
                    String.valueOf(contato.getEmail()),
                    String.valueOf(contato.getTelefone1()),
                    String.valueOf(contato.getTelefone2()),
                    String.valueOf(contato.getNascimento()),
                    String.valueOf(contato.getSexo()),
            };

            relatorioContato.salvarContato(lines);

            if (dao.salvar(contato) && contatoDao.liberarInclusao(contato)) {
                Alerta.msgInformacao("Registro gravado com sucesso!");
                atualizarTabela();
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
        if (Alerta.msgConfirmaExclusao(tfDescricao.getText())) {
            dao.excluir(objetoSelecionado);
            limparCamposFormulario();
            atualizarTabela();
            Alerta.msgInformacao("Registro foi excluido com sucesso!");

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
        TableColumn<Contato, Long> colunaId = new TableColumn<>("ID");
        TableColumn<Contato, String> colunaDescricao = new TableColumn<>("Nome Contato");
        TableColumn<Contato, TipoContato> colunaTipoContato = new TableColumn<>("Tipo Contato");
        TableColumn<Contato, Cidade> colunaCidade = new TableColumn<>("Cidade");
        TableColumn<Contato, LocalDate> colunaNascimento = new TableColumn<>("Nascimento");

        // Adicionando as colunas e seus títulos na tabela
        tableView.getColumns().addAll(colunaId, colunaDescricao, colunaTipoContato, colunaCidade, colunaNascimento);

        // Relacionar cada coluna a seu atributo no Model.
        colunaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colunaTipoContato.setCellValueFactory(new PropertyValueFactory<>("tipoContato"));
        colunaCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        colunaNascimento.setCellValueFactory(new PropertyValueFactory<>("nascimento"));

        // Redimensionamento as colunas manualmente.
        colunaId.prefWidthProperty().bind(tableView.widthProperty().multiply(0.05));
        colunaDescricao.prefWidthProperty().bind(tableView.widthProperty().multiply(0.30));
        colunaTipoContato.prefWidthProperty().bind(tableView.widthProperty().multiply(0.20));
        colunaCidade.prefWidthProperty().bind(tableView.widthProperty().multiply(0.30));
        colunaNascimento.prefWidthProperty().bind(tableView.widthProperty().multiply(0.15));

        // Difinindo o formato da data para exibição na tabela
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Convertendo a data da tabela para o formato especificado
        colunaNascimento.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(LocalDate data, boolean empty) {
                super.updateItem(data, empty);
                if (data != null) {
                    setText(formato.format(data));
                } else {
                    setText(null);
                }
            }
        });
    }

    @Override
    public void atualizarTabela() {
        observableList.clear();

        List<Contato> lista = dao.consultar(tfPesquisa.getText());
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
            tfSobrenome.setText(objetoSelecionado.getSobrenome());
            tfEndereco.setText(objetoSelecionado.getEndereco());
            tfBairro.setText(objetoSelecionado.getBairro());
            tfNumero.setText(String.valueOf(objetoSelecionado.getNumero()));
            tfTelefone1.setText(String.valueOf(objetoSelecionado.getTelefone1()));
            tfTelefone2.setText(String.valueOf(objetoSelecionado.getTelefone2()));
            tfEmail.setText(objetoSelecionado.getEmail());
            ckAtivo.setSelected(objetoSelecionado.isAtivo());

            if (Objects.equals(objetoSelecionado.getSexo(), "M")) {
                rbMasculino.setSelected(true);
            } else {
                rbFeminino.setSelected(true);
            }
            dpNascimento.setValue(objetoSelecionado.getNascimento());

            cbTipoContato.getSelectionModel().selectFirst();
            cbTipoContato.setValue(objetoSelecionado.getTipoContato());
            cbCidade.getSelectionModel().selectFirst();
            cbCidade.setValue(objetoSelecionado.getCidade());
            tfUf.setText(cbCidade.getSelectionModel().getSelectedItem().getUf());
            tfCep.setText(String.valueOf(cbCidade.getSelectionModel().getSelectedItem().getCep()));
        }
    }

    @Override
    public void limparCamposFormulario() {
        tfDescricao.clear();
        tfSobrenome.clear();
        tfEndereco.clear();
        tfBairro.clear();
        tfNumero.clear();
        tfTelefone1.clear();
        tfTelefone2.clear();
        tfEmail.clear();
        tfCep.clear();
        tfUf.clear();

        rbMasculino.setSelected(true);
        ckAtivo.setSelected(true);

        cbCidade.getSelectionModel().select(-1);
        cbTipoContato.getSelectionModel().select(-1);

        dpNascimento.setValue(null);
        objetoSelecionado = null;

        tfDescricao.requestFocus();
    }
}
