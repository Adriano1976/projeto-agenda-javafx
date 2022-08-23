package com.projetos.agenda.dao;

import com.projetos.agenda.model.TipoContato;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import java.util.List;

public class TipoContatoDao {

    private final ObservableList<TipoContato> observableList = FXCollections.observableArrayList();

    public boolean salvar(TipoContato tipoContato) {
        try {
            Session session = ConexaoBanco.getSessionFactory().openSession();
            session.beginTransaction();
            session.merge(tipoContato);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception erro) {
            System.out.println("Ocorreu o erro: " + erro);
            // Arquivo de log.
            return false;
        }
    }

    public void excluir(TipoContato tipoContato) {
        try {
            Session session = ConexaoBanco.getSessionFactory().openSession();
            session.beginTransaction();
            session.remove(tipoContato);
            session.getTransaction().commit();
            session.clear();
            System.out.println("Registro excluido com sucesso!");

        } catch (Exception erro) {
            System.out.println("Ocorreu o erro: " + erro);
        }
    }

    public List<TipoContato> consultar(String descricao) {
        List lista;
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        if (descricao.length() == 0) {
            lista = session.createQuery(" from TipoContato ").getResultList();
        } else {
            lista = session.createQuery("from TipoContato t where t.descricao like " + "'" + descricao + "%'").getResultList();
        }
        session.getTransaction().commit();
        session.close();

        return lista;
    }

    public ObservableList<TipoContato> comboBox() {
        List lista;
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        lista = session.createQuery("from TipoContato ").getResultList();
        session.getTransaction().commit();
        session.close();

        observableList.addAll(lista);
        return observableList;
    }
}
