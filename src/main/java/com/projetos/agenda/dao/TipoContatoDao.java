package com.projetos.agenda.dao;

import com.projetos.agenda.model.TipoContato;
import org.hibernate.Session;

public class TipoContatoDao {

    public void salvar(TipoContato tipoContato) {
        try {
            Session session = ConexaoBanco.getSessionFactory().openSession();
            session.beginTransaction();
            session.merge(tipoContato);
            session.getTransaction().commit();
            session.close();
            System.out.println("Registro gravado com sucesso!");

        } catch (Exception erro) {
            System.out.println("Ocorreu o erro: " + erro);
        }
    }
}
