package com.projetos.agenda.controller;

import com.projetos.agenda.dao.CrudGenericoDao;
import com.projetos.agenda.dao.TipoContatoDao;
import com.projetos.agenda.model.TipoContato;
import com.projetos.agenda.util.Alerta;
import com.projetos.agenda.util.RelatorioTipoContato;
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

    private final CrudGenericoDao<TipoContato> dao = new CrudGenericoDao<>(TipoContato.class);
    private TipoContato objetoSelecionado = new TipoContato();

    protected final RelatorioTipoContato relatorio = new RelatorioTipoContato();
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
        if (ValidarCampo.checarCampoVazio(tfDescricao)) {
            TipoContato objeto = new TipoContato();

            if (objetoSelecionado != null) {
                objeto.setId(objetoSelecionado.getId());
            }
            objeto.setDescricao(tfDescricao.getText());

            String[] lines = new String[]{
                    String.valueOf(objeto.getDescricao())
            };

            // Salvando os dados num arquivo com extensão csv.
            relatorio.salvarTipoContato(lines);

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
        TableColumn<TipoContato, Long> columaId = new TableColumn<>("ID");
        columaId.setMinWidth(40);
        columaId.setMaxWidth(40);
        TableColumn<TipoContato, String> colunaDescricao = new TableColumn<>("DESCRIÇÃO");

        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(columaId, colunaDescricao);

        columaId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colunaDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
    }

    /**
     * Método responsável em mostrar no formulário as informações salvas no banco de dados ao ser chamada.
     */
    @Override
    public void atualizarTabela() {
        observableList.clear();
        List<TipoContato> lista = dao.consultar(tfPesquisa.getText());
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
        objetoSelecionado = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());

        tfId.setText(String.valueOf(objetoSelecionado.getId()));
        tfDescricao.setText(objetoSelecionado.getDescricao());
    }

    /**
     * <p>Método responsável em limpar os campos do formulário ao ser chamado por outro método.</p>
     */
    @Override
    public void limparCamposFormulario() {
        objetoSelecionado = null;
        tfId.clear();
        tfDescricao.clear();
        tfDescricao.requestFocus();
    }
}
