package com.projetos.agenda.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Uf {
    private static ObservableList<String> observableList;

    public static ObservableList gerarUf() {
        observableList = FXCollections.observableArrayList(
                "AC",
                "AL",
                "AP",
                "AM",
                "BA",
                "CE",
                "ES",
                "GO",
                "MA",
                "MT",
                "MG",
                "PA",
                "PB",
                "PR",
                "PE",
                "PI",
                "RJ",
                "RN",
                "RS",
                "RO",
                "RR",
                "SC",
                "SP",
                "SE",
                "TO",
                "DF"
        );
        return observableList;
    }
}
