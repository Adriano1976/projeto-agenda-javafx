package com.projetos.agenda.dao;

import com.projetos.agenda.model.Contato;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CidadeDao {

    public static boolean liberaExclusao(Long id) {
        List<Contato> lista = new ArrayList<>();
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        lista = session.createQuery(" from Contato where Cidade = " + id).getResultList();
        session.getTransaction().commit();
        session.close();

        return lista.isEmpty();
    }
}
