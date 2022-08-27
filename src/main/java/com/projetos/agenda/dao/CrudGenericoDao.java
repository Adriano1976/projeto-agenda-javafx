package com.projetos.agenda.dao;

import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CrudGenericoDao<T> {

    private final Class<T> classeNome;

    public CrudGenericoDao(Class<T> classeNome) {
        this.classeNome = classeNome;
    }

    public boolean salvar(T nomeClasse) {
        try {
            Session session = ConexaoBanco.getSessionFactory().openSession();
            session.beginTransaction();
            session.merge(nomeClasse);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception erro) {
            System.out.println("Ocorreu o erro: " + erro);
            // Arquivo de log.
            return false;
        }
    }

    public void excluir(T nomeClasse) {
        try {
            Session session = ConexaoBanco.getSessionFactory().openSession();
            session.beginTransaction();
            session.remove(nomeClasse);
            session.getTransaction().commit();
            session.clear();
            System.out.println("Registro excluido com sucesso!");

        } catch (Exception erro) {
            System.out.println("Ocorreu o erro: " + erro);
        }
    }

    public List<T> consultar(String descricao) {
        List<T> lista = new ArrayList<>();
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        String query;
        if (descricao.length() == 0) {
            query = "select object from " + classeNome.getName() + " object";
        } else {
            query = "select object from " + classeNome.getName() + " object" + " where object.descricao like " + "'" + descricao + "%'";
        }
        lista = session.createQuery(query, classeNome).getResultList();
        session.getTransaction().commit();
        session.close();

        return lista;
    }
}
