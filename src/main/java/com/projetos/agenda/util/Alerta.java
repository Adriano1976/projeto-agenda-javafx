package com.projetos.agenda.util;

import javafx.scene.control.Alert;

public class Alerta {
    public static void msgInformacao(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Informação sobre cadastro");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
