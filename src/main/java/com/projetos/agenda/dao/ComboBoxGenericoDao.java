package com.projetos.agenda.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Classe responsável em controlar os tipos de classes solicitante de conexão na base de dados.</p>
 *
 * @author Adriano Santos
 *
 * @param <T> Responsável em receber um objeto do tipo genérico.
 */
public class ComboBoxGenericoDao<T> {

    /**
     * Variável responsável em receber a lista de dados de um combobox genérico.
     */
    private final ObservableList<T> observableList = FXCollections.observableArrayList();

    /**
     * @param nomeClasse Responsável em receber o nome da classe solicitante.     *
     * @return Retorna os dados solicitados pela classe genérica.
     */
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
