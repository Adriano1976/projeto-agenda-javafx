package com.projetos.agenda.dao;

import com.projetos.agenda.model.Cidade;
import com.projetos.agenda.model.Contato;
import com.projetos.agenda.model.TipoContato;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class ConexaoBanco {
    private static SessionFactory conexao = null;

    private static SessionFactory buildSessionFactory() {//

        // Objeto que armazena as configurações de conexao.
        Configuration configuracao = new Configuration().configure();

        configuracao.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/agendafx?zeroDateTimeBehavior=convertToNull");
        configuracao.setProperty("hibernate.connection.username", "root");
        configuracao.setProperty("hibernate.connection.password", "251097anl");
        configuracao.setProperty("hibernate.hbm2ddl", "update");
        configuracao.setProperty("hibernate.show_sql", "true");

        // Indicando o mapeamento das classes.
        configuracao.addPackage("com.projetos.agenda.model").addAnnotatedClass(TipoContato.class);
        configuracao.addPackage("com.projetos.agenda.model").addAnnotatedClass(Cidade.class);
        configuracao.addPackage("com.projetos.agenda.model").addAnnotatedClass(Contato.class);

        // sessionFactory recebe a construção de sessão de conexão com banco de dados
        conexao = configuracao.buildSessionFactory();
        return conexao;
    }

    public static SessionFactory getSessionFactory() {
        if (conexao == null) {
            conexao = buildSessionFactory();
        }
        return conexao;
    }
}
