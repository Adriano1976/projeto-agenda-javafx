package com.projetos.agenda.controller;

import com.projetos.agenda.dao.ComboBoxGenericoDao;
import com.projetos.agenda.dao.ContatoDao;
import com.projetos.agenda.dao.CrudGenericoDao;
import com.projetos.agenda.model.Cidade;
import com.projetos.agenda.model.Contato;
import com.projetos.agenda.model.TipoContato;
import com.projetos.agenda.util.Alerta;
import com.projetos.agenda.util.MascaraCampo;
import com.projetos.agenda.util.RelatorioContato;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Classe responsável em controlar a relação entre o formalário e a base de dados.
 *
 * @author Adriano Santos
 */
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

    /**
     * Método responsável em preparar os campos para receber novos dados.
     *
     * @param actionEvent Responsável em receber uma ação de um evento ao ser clicado.
     */
    @FXML
    public void incluirResgistro(ActionEvent actionEvent) {
        limparCamposFormulario();
    }

    /**
     * <p>Método responsável em solicitar o salvamento do registro, informado no formulário, numa base de dados,
     * se as condições de cada campo for validade.
     * Para isso, é feito primeiro a validação do campo antes de ser salvo, pois se tiver vazio, ele não salva
     * os campos em branco caso sejam obrigatório.
     * Depois da validação, as informações são identificadas em seus respctivos campos e salvas tanto
     * na base de dados como também em um arquivo com a extensão csv.
     * Porém se ocorrer algum erro de gravação ou campo vazio, será passado uma mensagem ao usuário</p>
     *
     * @param actionEvent Responsável em receber uma ação de um evento ao ser clicado.
     */
    @FXML
    public void salvarResgistro(ActionEvent actionEvent) {

        // Verificar e validar os campos. Se por a caso esteja vazio, será emitido uma mensagem ao usuário
        if (ValidarCampo.checarCampoVazio(tfDescricao, tfSobrenome, tfEndereco, tfBairro, tfCep,
                tfNumero, cbCidade, tfUf, cbTipoContato, tfEmail, tfTelefone1, tfTelefone2, dpNascimento)) {

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

            // Salvando os dados num arquivo com extensão csv.
            relatorioContato.salvarContato(lines);

            // Emitir uma mensagem ao usuário se os dados forma salvos ou não.
            if (dao.salvar(contato) && contatoDao.liberarInclusao(contato)) {
                Alerta.msgInformacao("Registro gravado com sucesso!");
            } else {
                Alerta.msgInformacao("Ocorreu um erro ao tentar gravar o registro!");
            }
            // Atualizar as informações da tabela e limpar os campos.
            atualizarTabela();
            limparCamposFormulario();

        } else {
            Alerta.msgInformacao("Favor, preencher o(s) campo(s) obrigatório(s)");
        }
    }

    /**
     * Método responsável em solicitar a exlusão do registro, identificado no formulário, da base de dados
     * se a condição for validada.
     *
     * @param actionEvent Responsável em receber uma ação de um evento ao ser clicado.
     */
    @FXML
    public void excluirResgistro(ActionEvent actionEvent) {
        if (Alerta.msgConfirmaExclusao(tfDescricao.getText())) {
            dao.excluir(objetoSelecionado);
            limparCamposFormulario();
            atualizarTabela();
            Alerta.msgInformacao("Registro foi excluido com sucesso!");

        }
    }

    /**
     * Método responsável em chamar o método {@code atualizarTabela} ao ser chamado
     * pelo usuário num evento ao usar oo digitar um caracter no campo pesquisa.
     *
     * @param keyEvent Responsável em receber uma ação de um evento ao ser clicado.
     */
    @FXML
    public void filtrarRegistro(KeyEvent keyEvent) {
        atualizarTabela();
    }

    /**
     * Método responsável em chamar o método {@code setCamposFormulario} ao ser chamado
     * pelo usuário num evento ao clicar na tabela.
     *
     * @param mouseEvent Responsável em receber uma ação de um evento ao ser clicado.
     */
    @FXML
    public void clicarTabela(MouseEvent mouseEvent) {
        setCamposFormulario();
    }

    /**
     * Método responsável em chamar o método {@code setCamposFormulario} ao ser chamado
     * pelo usuário num evento ao clicar no teclado.
     *
     * @param keyEvent Responsável em receber uma ação de um evento ao ser clicado.
     */
    @FXML
    public void moverTabela(KeyEvent keyEvent) {
        setCamposFormulario();
    }

    /**
     * Método responsável em controlar e criar as colunas com os nomes e tamanho na tabela do formulário,
     * preenche-la com as informações salvos no banco de dados.
     */
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

    /**
     * Método responsável em mostrar no formulário as informações salvas no banco de dados ao ser chamada.
     */
    @Override
    public void atualizarTabela() {
        observableList.clear();

        List<Contato> lista = dao.consultar(tfPesquisa.getText());
        observableList.addAll(lista);

        tableView.getItems().setAll(observableList);
        tableView.getSelectionModel().selectFirst();
    }

    /**
     * <p>Método responsável em recuperar as informações dos tipos de contatos da base dados e
     * preencher os compos devidamente identificado ao ser chamado por outro método.</p>
     */
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

    /**
     * <p>Método responsável em limpar os campos do formulário ao ser chamado por outro método.</p>
     */
    @Override
    public void limparCamposFormulario() {
        tfId.clear();
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
