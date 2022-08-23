package com.projetos.agenda.dao;

import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CrudGenericoDao<T> {

    public boolean salvar(T tipoContato) {
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

    public void excluir(T tipoContato) {
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

    public List<T> consultar(String descricao, String nomeClasse) {
        List lista = new ArrayList<>();
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        if (descricao.length() == 0) {
            lista = session.createQuery(" from " + nomeClasse).getResultList();
        } else {
            lista = session.createQuery(" from " + nomeClasse + " m where m.descricao like " + "'" + descricao + "%'").getResultList();
        }
        session.getTransaction().commit();
        session.close();

        return lista;
    }
}
