package com.projetos.agenda.util;

import javafx.application.Platform;
import javafx.scene.control.TextField;

public class MascaraCampo {

    private static void limitarTamanhoCampo(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.length() > 6) {
                textField.setText(oldValue);
            }
        });
    }

    private static void limitarTamanhoTelefone(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.length() > 11) {
                textField.setText(oldValue);
            }
        });
    }

    private static void posicionarCursor(TextField textField) {
        Platform.runLater(() -> {
            if (textField.getText().length() != 0) {
                textField.positionCaret(textField.getText().length());
            }
        });
    }

    public static void mascaraNumero(TextField textField) {

        MascaraCampo.limitarTamanhoCampo(textField);

        textField.lengthProperty().addListener((observable, oldValue, newValue) -> {
            String textoDigitado = textField.getText();
            textoDigitado = textoDigitado.replaceAll("[^0-9]", "");
            textField.setText(textoDigitado);
            MascaraCampo.posicionarCursor(textField);
        });
    }

    public static void mascaraTelefone(TextField textField) {

        MascaraCampo.limitarTamanhoTelefone(textField);

        textField.lengthProperty().addListener((observable, oldValue, newValue) -> {
            String textoDigitado = textField.getText();
            textoDigitado = textoDigitado.replaceAll("[^0-9]", "");
            textField.setText(textoDigitado);
            MascaraCampo.posicionarCursor(textField);
        });
    }
}
