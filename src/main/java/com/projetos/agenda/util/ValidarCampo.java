package com.projetos.agenda.util;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsával em conferir validar se o campo está vazio ou não, e enviar uma mensagem ao usuário.
 *
 * @author Adriano Santos
 */
public class ValidarCampo {
    private static final Tooltip tooltip = new Tooltip("Campo obrigatório");

    /**
     * Método responsável em checar SE o campo está vazio e não permitir que o formulário seja salvo com o campo vazio.
     *
     * @param nodes Responsável em receber o estado de cada campo e manipular o objeto recebido.
     * @return retorna um valor booleano, informando se o campo está vazio ou não.
     */
    public static boolean checarCampoVazio(Node... nodes) {
        List<Node> camposFalha = new ArrayList<>();

        tooltip.setStyle(
                "-fx-background-color: linear-gradient(#000, #B22222);" +
                        "-fx-font-wight: bold;"
        );

        tooltip.setShowDelay(Duration.seconds(0));

        for (Node nodeFor : nodes) {

            // Validação para campo TextField
            if (nodeFor instanceof TextField textField) {

                // Capturar caracter digitado e retirar borda de vazio.
                textField.textProperty().addListener((observable, oldValue, newValue) -> {
                    ValidaExibeToolTip.removerCorBordaTootlTip(textField, tooltip);
                });

                // Se o campo estiver vazio, adicionar a borda acusando o erro.
                if (textField.getText().trim().equals("")) {
                    camposFalha.add(nodeFor);
                    ValidaExibeToolTip.adicionarCorBordaTootlTip(textField, tooltip);
                }
            }

            // Validação para campo ComboBox
            if (nodeFor instanceof ComboBox<?> comboBox) {

                // Capturar caracter digitado e retirar borda de vazio.
                comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
                    ValidaExibeToolTip.removerCorBordaTootlTip(comboBox, tooltip);
                });

                // Se o campo estiver vazio, adicionar a borda acusando o erro.
                if (comboBox.getValue() == null) {
                    camposFalha.add(nodeFor);
                    ValidaExibeToolTip.adicionarCorBordaTootlTip(comboBox, tooltip);
                }
            }

            // Validação para campo ComboBox
            if (nodeFor instanceof DatePicker data) {

                // Capturar caracter digitado e retirar borda de vazio.
                data.valueProperty().addListener((observable, oldValue, newValue) -> {
                    ValidaExibeToolTip.removerCorBordaTootlTip(data, tooltip);
                });

                // Se o campo estiver vazio, adicionar a borda acusando o erro.
                if (data.getValue() == null) {
                    camposFalha.add(nodeFor);
                    ValidaExibeToolTip.adicionarCorBordaTootlTip(data, tooltip);
                }
            }
        }

        return camposFalha.isEmpty();
    }
}
