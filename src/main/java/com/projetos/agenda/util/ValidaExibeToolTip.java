package com.projetos.agenda.util;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

import java.lang.reflect.Field;

public class ValidaExibeToolTip {

    // Adicionar e remover os estilos da borda.
    public static void adicionarCorBordaTootlTip(Node node, Tooltip tooltip) {
        Tooltip.install(node, tooltip);
        node.setStyle("-fx-border-color: #00CED1;");
    }

    public static void removerCorBordaTootlTip(Node node, Tooltip tooltip) {
        Tooltip.uninstall(node, tooltip);
        node.setStyle(null);
    }

    public static void mensagem(Node node, Tooltip tooltip) {
        Tooltip.install(node, tooltip);
    }

    // Exibição do TooltTip (Duração e comportamento)
    public static void tempoToolTip(Tooltip tooltip) {
        try {
            Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
            fieldBehavior.setAccessible(false);
            Object objBehavior = fieldBehavior.get(tooltip);

            Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
            fieldTimer.setAccessible(true);
            Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

            objTimer.getKeyFrames().clear();
            objTimer.getKeyFrames().add(new KeyFrame(new Duration(0)));

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
