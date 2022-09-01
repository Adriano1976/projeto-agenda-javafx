package com.projetos.agenda.dao;

import com.projetos.agenda.model.Cidade;
import com.projetos.agenda.model.Contato;
import com.projetos.agenda.model.TipoContato;
import org.hibernate.Session;

/**
 * <p>Classe responsável em controlar e validar a inclusão dos dados devidamente identificado.</p>
 *
 * @author Adriano Santos
 */
public class ContatoDao {

    /**
     * <p>Método responsável validar e liberar a exclusão das informações de uma linha da base de dados</p>
     *
     * @param contato Responsável em receber os registros do contato a ser inserido na base de dados.
     * @return Retorna {@code true} se a inclusão for liberada, caso contrário não será liberado.
     */
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
