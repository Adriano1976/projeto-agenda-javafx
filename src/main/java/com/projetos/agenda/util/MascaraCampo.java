package com.projetos.agenda.util;

import javafx.application.Platform;
import javafx.scene.control.TextField;

/**
 * <p>Classe responsável por controlar e validar os campos que trabalhe com a classe {@link TextField}.
 *
 * @author Adriano Santos
 */
public class MascaraCampo {

    /**
     * Método responsável em controlar os dados de entrada e limitar a quantidades de caracter digitado
     * até no máximo 6 dígitos.
     *
     * @param textField Responsável em receber os dados do usuário e validar o limite de dados
     *                  permitido no campo seguindo a condicional <code>if</code> e devolver o resultado
     *                  da validação dos dados digitados e devolver o valor anterior.
     */
    private static void limitarTamanhoCampo(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.length() > 6) {
                textField.setText(oldValue);
            }
        });
    }

    /**
     * Método responsável em controlar os dados de entrada e limitar a quantidade de números digitados
     * em até 10 caracteres.
     *
     * @param textField Responsável em receber os dados do usuário e validar o limite de dados
     *                  permitido no campo seguindo a condicional <code>if</code> e devolver o resultado
     *                  da validação dos dados digitados devolvendo o valor anterior no compo.
     */
    private static void limitarTamanhoTelefone(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.length() > 11) {
                textField.setText(oldValue);
            }
        });
    }

    /**
     * Método responsável em posicionar o cursor do mouse no início do campo.
     *
     * @param textField Responsável em receber o valor do Método {@link #mascaraNumero(TextField)}.
     */
    private static void posicionarCursor(TextField textField) {
        Platform.runLater(() -> {
            if (textField.getText().length() != 0) {
                textField.positionCaret(textField.getText().length());
            }
        });
    }

    /**
     * Método responsável em controlar o limite de caracteres digitados, com o
     * Método {@link #limitarTamanhoCampo(TextField)} e o tipo de dados de entrada. Se o valor digitado for
     * deferente de um número, o mesmo não permite a entrada de qualquer outro valor.
     *
     * @param textField Responsável em receber o número da casa e devolver um estado de aceitação.
     *                  SE as informações digitadas forem corretas.
     */
    public static void mascaraNumero(TextField textField) {

        MascaraCampo.limitarTamanhoCampo(textField);

        textField.lengthProperty().addListener((observable, oldValue, newValue) -> {
            String textoDigitado = textField.getText();
            textoDigitado = textoDigitado.replaceAll("[^0-9]", "");
            textField.setText(textoDigitado);
            MascaraCampo.posicionarCursor(textField);
        });
    }

    /**
     * Método responsável em controlar o limite de caracteres digitados, com o
     * Método {@link #limitarTamanhoTelefone(TextField)} e o tipo de dados de entrada.
     * Se o valor digitado for deferente de um número, o mesmo não permite a entrada
     * de qualquer outro valor.
     *
     * @param textField Responsável em receber o número de telefone e devolver um
     *                  estado de aceitação. SE as informações digitadas forem corretas.
     */

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
