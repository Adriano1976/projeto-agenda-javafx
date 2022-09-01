package com.projetos.agenda;

import com.projetos.agenda.util.RelatorioContato;
import com.projetos.agenda.util.RelatorioTipoContato;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * <p>Classe Responsável em controlar e manipular os menus e ações da tela principal</p>
 *
 * @author Adriano Santos
 */
public class TelaPrincipalController implements Initializable {

    @FXML
    public MenuBar barraDeMenu;
    @FXML
    public Menu menuArquivo;
    @FXML
    public MenuItem menuItemContato;
    @FXML
    public MenuItem menuItemTipoContato;
    @FXML
    public MenuItem menuItemCidade;
    @FXML
    public MenuItem menuItemSair;
    @FXML
    public Menu menuRelatorio;
    @FXML
    public MenuItem menuItemRelatorioContatoGeral;
    @FXML
    public MenuItem menuItemRelatorioContatoEndereco;
    @FXML
    public MenuItem menuItemRelatorioContatoTelefone;
    @FXML
    public MenuItem menuItemRelatorioContatoTipo;
    @FXML
    public Menu menuSobre;
    @FXML
    public MenuItem menuItemSobreSistema;

    protected final RelatorioTipoContato relatorioTipoContato = new RelatorioTipoContato();
    protected final RelatorioContato relatorioContato = new RelatorioContato();

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
        Image menuItemContatoIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("icons/iconeMenuContato.png")));
        Image menuItemTipoContatoIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("icons/iconeMenuContatoTipo.png")));
        Image menuItemCidadeIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("icons/iconeMenuCidade.png")));
        Image menuItemSairIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("icons/iconeMenuSair.png")));
        Image menuItemRelatorioIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("icons/iconeMenuRelatorio.png")));
        Image menuItemSobreSistemaIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("icons/iconeMenuSobre.png")));

        menuItemContato.setGraphic(new ImageView(menuItemContatoIcon));
        menuItemTipoContato.setGraphic(new ImageView(menuItemTipoContatoIcon));
        menuItemCidade.setGraphic(new ImageView(menuItemCidadeIcon));
        menuItemSair.setGraphic(new ImageView(menuItemSairIcon));

        menuItemRelatorioContatoGeral.setGraphic(new ImageView(menuItemRelatorioIcon));
        menuItemRelatorioContatoEndereco.setGraphic(new ImageView(menuItemRelatorioIcon));
        menuItemRelatorioContatoTelefone.setGraphic(new ImageView(menuItemRelatorioIcon));
        menuItemRelatorioContatoTipo.setGraphic(new ImageView(menuItemRelatorioIcon));

        menuItemSobreSistema.setGraphic(new ImageView(menuItemSobreSistemaIcon));

    }

    /**
     * Método responsável em chamar o método {@code abrirFormulario} para abrir o formulario "contato_view".
     *
     * @param actionEvent Responsável em receber um evento.
     */
    @FXML
    public void acessarContato(ActionEvent actionEvent) {
        abrirFormulario("contato_view");
    }

    /**
     * Método responsável em chamar o método {@code abrirFormulario} para abrir o formulario "tipo_contato_view".
     *
     * @param actionEvent Responsável em receber um evento.
     */
    @FXML
    public void acessarTipoContato(ActionEvent actionEvent) {
        abrirFormulario("tipo_contato_view");
    }

    /**
     * Método responsável em chamar o método {@code abrirFormulario} para abrir o formulario "cidade_view".
     *
     * @param actionEvent Responsável em receber um evento.
     */
    @FXML
    public void acessarCidade(ActionEvent actionEvent) {
        abrirFormulario("cidade_view");
    }

    /**
     * Método responsável em encerrar o programa.
     *
     * @param actionEvent Responsável em receber um evento.
     */
    @FXML
    public void acessarSair(ActionEvent actionEvent) {
        System.exit(0);
    }

    /**
     * Método responsável em solicitar outro método para gerar um relatório geral.
     *
     * @param actionEvent Responsável em receber um evento.
     */
    @FXML
    public void acessarRelatorioContatoGeral(ActionEvent actionEvent) {
        relatorioContato.gerarRelatorioContato();
    }

    /**
     * Método responsável em solicitar outro método para gerar um relatório de contato.
     *
     * @param actionEvent Responsável em receber um evento.
     */
    @FXML
    public void acessarRelatorioContatoEndereco(ActionEvent actionEvent) {
        relatorioContato.gerarRelatorioContato();
    }

    /**
     * Método responsável em solicitar outro método para gerar um relatório com os telefones de contatos.
     *
     * @param actionEvent Responsável em receber um evento.
     */
    @FXML
    public void acessarRelatorioContatoTelefone(ActionEvent actionEvent) {
        relatorioContato.gerarRelatorioContato();
    }

    /**
     * Método responsável em solicitar outro método para gerar um relatório dos tipos de contatos.
     *
     * @param actionEvent Responsável em receber um evento.
     */
    @FXML
    public void acessarRelatorioContatoTipo(ActionEvent actionEvent) {
        relatorioTipoContato.gerarRelatorioTipoContato();
    }

    /**
     * Método responsável em mostrar informações sobre o sistema.
     *
     * @param actionEvent Responsável em receber um evento.
     */
    @FXML
    public void acessarSobreSistema(ActionEvent actionEvent) {
    }

    /**
     * Método responsável em controlar e abrir o formulário quando for chamado por outro método.
     *
     * @param formulario Responsável em receber os comandos para abrir um determinado formulário.
     */
    public void abrirFormulario(String formulario) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(formulario + ".fxml")));
            Image applicationIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("icons/iconeAplicacao..png")));
            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.setTitle("Formulario");
            stage.getIcons().add(applicationIcon);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
