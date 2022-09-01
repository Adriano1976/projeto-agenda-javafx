package com.projetos.agenda.util;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Classe responsável por controlar e gerar as Unidades Federativas (UF).
 *
 * @author Adriano Santos
 */
public class Uf {

    /**
     * Método responsável observar a solicitação do usuário e devolver a resposta desejada.
     *
     * @return Retorna uma Unidade Federativa (UF)
     */
    public static ObservableList gerarUf() {
        return FXCollections.observableArrayList(
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
    }
}
