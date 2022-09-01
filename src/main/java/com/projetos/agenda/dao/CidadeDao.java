package com.projetos.agenda.dao;

import com.projetos.agenda.model.Contato;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Classe responsável em controlar e validar a exclusão dos dados devidamente identificado.</p>
 *
 * @author Adriano Santos
 */
public class CidadeDao {

    /**
     * <p>Método responsável validar e liberar a exclusão das informações de uma linha da base de dados</p>
     *
     * @param id Responsável em receber o 'id' referente do tipo de contato a ser excluido.
     * @return Retorna {@code true} se a exclusão for liberada, caso contrário será retornado {@code false}.
     */
    public static boolean liberaExclusao(Long id) {
        List<Contato> lista = new ArrayList<>();
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        lista = session.createQuery(" from Contato c where c.Cidade = " + id, Contato.class).getResultList();
        session.getTransaction().commit();
        session.close();

        return lista.isEmpty();
    }
}
