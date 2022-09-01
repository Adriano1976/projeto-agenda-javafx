package com.projetos.agenda.util;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;

/**
 * Classe responsável por controlar e emitir uma mensagem de validação nos
 * campos dos formulários SE algum campo firar em branco.
 *
 * @author Adriano Santos
 */
public class ValidaExibeToolTip {

    /**
     * Método responsável em adicionar uma cor na borda dos campos usando
     * os métodos {@link Node} e {@link Tooltip}.
     *
     * @param node    Recebe as característcas da cor da borda do campo.
     * @param tooltip Devolve uma borda com a cor configurada ao campo.
     */
    public static void adicionarCorBordaTootlTip(Node node, Tooltip tooltip) {
        Tooltip.install(node, tooltip);
        node.setStyle("-fx-border-color: #00CED1;");
    }

    /**
     * Método responsável em remover a cor na borda dos campos usando
     * os métodos {@link Node} e {@link Tooltip}.
     *
     * @param node    Recebe as característcas da cor da borda do campo, sendo nesse caso recebe o valor "null".
     * @param tooltip Devolve um status sem a cor configurada ao campo.
     */

    public static void removerCorBordaTootlTip(Node node, Tooltip tooltip) {
        Tooltip.uninstall(node, tooltip);
        node.setStyle(null);
    }
}
