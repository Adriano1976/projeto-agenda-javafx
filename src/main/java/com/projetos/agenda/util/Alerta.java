package com.projetos.agenda.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * <h3>Classe Alerta</h3>
 * <p>Classe responsável em emitir uma mensagem de confirmação ou cancelar por meio do método
 * {@link #msgInformacao(String)} e método {@link #msgConfirmaExclusao(String)}.</p>
 *
 @author Adriano Santos
 */
public class Alerta {
    /**
     * <p>As variáveis <code>btnConfirmar</code> e <code>btnCancelar</code> responsável
     * por representar a classe <code>ButtonType</code> e receber o 'status' de confirmação ao ser chamado.</p>
     */
    static ButtonType btnConfirmar = new ButtonType("Confirmar");
    static ButtonType btnCancelar = new ButtonType("Cancelar");
    static boolean resposta;

    /**
     * <p>Método responsável em enviar uma mensagem de informação ao usuário.</p>
     *
     * @param msg Responsável em receber o status <code>true</code> de confirmação.
     */
    public static void msgInformacao(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Informação sobre cadastro");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    /**
     * Método responsável em enviar uma mensagem de confirmação de exclusão ao usuário.
     *
     * @param mensagemExclusao Responsável em receber o status <code>true</code> de confirmação do usuario.
     * @return O objeto <code>resposta</code> retorna uma resposta, que dependerar do status da confirmação.
     */
    public static boolean msgConfirmaExclusao(String mensagemExclusao) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Exclusão de Registro");
        alert.setContentText("Deseja exlcuir o registro: " + mensagemExclusao + "?");
        alert.getButtonTypes().setAll(btnConfirmar, btnCancelar);
        alert.showAndWait().ifPresent(btn -> resposta = btn == btnConfirmar);
        return resposta;
    }
}
