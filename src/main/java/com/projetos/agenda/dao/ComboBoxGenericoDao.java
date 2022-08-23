package com.projetos.agenda.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ComboBoxGenericoDao<T> {

    private final ObservableList<T> observableList = FXCollections.observableArrayList();

    public ObservableList<T> comboBox(String nomeClasse) {
        List lista = new ArrayList<>();
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        lista = session.createQuery("from " + nomeClasse).getResultList();
        session.getTransaction().commit();
        session.close();

        observableList.addAll(lista);
        return observableList;
    }
}
