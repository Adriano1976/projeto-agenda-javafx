package com.projetos.agenda.dao;

import com.projetos.agenda.model.Cidade;
import com.projetos.agenda.model.Contato;
import com.projetos.agenda.model.TipoContato;
import com.projetos.agenda.model.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ConexaoBanco {
    private static SessionFactory conexao = null;

    private static SessionFactory buildSessionFactory() {

        try {
            // Objeto que armazena as configurações de conexao.
            Configuration configuracao = new Configuration().configure();

            // Configurando usuário e senha para acesso ao banco de dados.
            configuracao.setProperty("hibernate.connection.username", "root");
            configuracao.setProperty("hibernate.connection.password", "251097anl");

            // Indicando o mapeamento das classes.
            configuracao.addPackage("com.projetos.agenda.model").addAnnotatedClass(TipoContato.class);
            configuracao.addPackage("com.projetos.agenda.model").addAnnotatedClass(Usuario.class);
            configuracao.addPackage("com.projetos.agenda.model").addAnnotatedClass(Cidade.class);
            configuracao.addPackage("com.projetos.agenda.model").addAnnotatedClass(Contato.class);

            // sessionFactory recebe a construção de sessão de conexão com banco de dados
            conexao = configuracao.buildSessionFactory();
            return conexao;

        } catch (HibernateException e) {
            throw new RuntimeException(e.toString());
        }
    }

    public static SessionFactory getSessionFactory() {
        if (conexao == null) {
            conexao = buildSessionFactory();
        }
        return conexao;
    }
}
