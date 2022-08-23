package com.projetos.agenda.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Alerta {
    static ButtonType btnConfirmar = new ButtonType("Confirmar");
    static ButtonType btnCancelar = new ButtonType("Cancelar");
    static boolean resposta;

    public static void msgInformacao(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Informação sobre cadastro");
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public static boolean msgConfirmaExclusao(String mensagemExclusao) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Exclusão de Registro");
        alert.setContentText("Deseja exlcuir o registro: " + mensagemExclusao + "?");
        alert.getButtonTypes().setAll(btnConfirmar, btnCancelar);
        alert.showAndWait().ifPresent(btn -> resposta = btn == btnConfirmar);
        return resposta;
    }
}
