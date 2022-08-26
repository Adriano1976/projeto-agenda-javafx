package com.projetos.agenda.dao;

import com.projetos.agenda.model.Cidade;
import com.projetos.agenda.model.Contato;
import com.projetos.agenda.model.TipoContato;
import org.hibernate.Session;

public class ContatoDao {

    public boolean liberarInclusao(Contato contato) {

        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        var contatoCidade = session.getReference(Cidade.class, contato.getCidade().getId());
        var contatoTipoContato = session.getReference(TipoContato.class, contato.getTipoContato().getId());

        contato.setCidade(contatoCidade);
        contato.setTipoContato(contatoTipoContato);

        session.getTransaction().commit();
        session.close();

        return true;
    }
}
