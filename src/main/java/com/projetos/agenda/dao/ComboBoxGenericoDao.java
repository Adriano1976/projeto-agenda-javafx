package com.projetos.agenda.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ComboBoxGenericoDao<T> {

    private final ObservableList<T> observableList = FXCollections.observableArrayList();

    public ObservableList<T> comboBox(Class<T> nomeClasse) {
        List<T> lista = new ArrayList<>();
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        lista = session.createQuery("select object from " + nomeClasse.getName() + " object", nomeClasse).getResultList();
        session.getTransaction().commit();
        session.close();

        observableList.addAll(lista);
        return observableList;
    }
}
