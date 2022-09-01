package com.projetos.agenda.controller;

import com.projetos.agenda.dao.CidadeDao;
import com.projetos.agenda.dao.CrudGenericoDao;
import com.projetos.agenda.model.Cidade;
import com.projetos.agenda.util.Alerta;
import com.projetos.agenda.util.Uf;
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

/**
 * Classe responsável em controlar a relação entre o formalário e a base de dados.
 *
 * @author Adriano Santos
 */
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

    private final CrudGenericoDao<Cidade> dao = new CrudGenericoDao<>(Cidade.class);
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
        if (ValidarCampo.checarCampoVazio(tfDescricao, tfCep, cbUf)) {
            Cidade objeto = new Cidade();

            if (objetoSelecionado != null) {
                objeto.setId(objetoSelecionado.getId());
            }

            objeto.setDescricao(tfDescricao.getText());
            objeto.setCep(Long.parseLong(tfCep.getText()));
            objeto.setUf(cbUf.getValue());

            // Emitir uma mensagem ao usuário se os dados forma salvos ou não.
            if (dao.salvar(objeto)) {
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
        if (CidadeDao.liberaExclusao(objetoSelecionado.getId())) {

            if (Alerta.msgConfirmaExclusao(tfDescricao.getText())) {
                dao.excluir(objetoSelecionado);
                limparCamposFormulario();
                atualizarTabela();
                Alerta.msgInformacao("Registro excluido com sucesso!");
            }
        } else {
            Alerta.msgInformacao("Exclusão não permitido. \nCidade utilizada no cadastro de Contato!");
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

    /**
     * Método responsável em mostrar no formulário as informações salvas no banco de dados ao ser chamada.
     */
    @Override
    public void atualizarTabela() {
        observableList.clear();
        List<Cidade> lista = dao.consultar(tfPesquisa.getText());
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
            tfCep.setText(String.valueOf(objetoSelecionado.getCep()));
            cbUf.setValue(objetoSelecionado.getUf());
        }
    }

    /**
     * <p>Método responsável em limpar os campos do formulário ao ser chamado por outro método.</p>
     */
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
