package com.projetos.agenda.dao;

import com.projetos.agenda.model.Usuario;
import org.hibernate.Session;

public class UsuarioDao {

    public boolean liberarInclusao(Usuario usuario) {

        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();

        var loginUsuario = session.getReference(Usuario.class, usuario.hashCode());

        usuario.setId(loginUsuario.getId());

        session.getTransaction().commit();
        session.close();

        return true;
    }
}
