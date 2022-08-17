package com.projetos.agenda;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader root = new FXMLLoader(MainApplication.class.getResource("TelaPrincipal.fxml"));
        Image applicationIcon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("icons/iconeAplicacao..png")));

        Scene scene = new Scene(root.load(), 320, 240);
        stage.getIcons().add(applicationIcon);
        stage.setTitle("Agenda de Contatos");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}