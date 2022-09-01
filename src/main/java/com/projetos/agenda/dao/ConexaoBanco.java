package com.projetos.agenda.dao;

import com.projetos.agenda.model.Cidade;
import com.projetos.agenda.model.Contato;
import com.projetos.agenda.model.TipoContato;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * <p>Classe responsável em fazer a conexão com o banco de dados seguindo as configurações desejadas
 * e controlar a comunicação entre a base de dados e os pacotes reclacionados a cada classe existente</p>
 *
 * @author Adriano Santos
 */
public class ConexaoBanco {
    private static SessionFactory conexao = null;

    /**
     * Método responsável pelas configurações e pacotes desejados para ocorrer uma conexão esperada.
     *
     * @return Retorna o resultado da tentativa de conexão com o banco de dados.
     */
    private static SessionFactory buildSessionFactory() {

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

    /**
     * Método responsável em realizar o texte de validação de conexão.
     *
     * @return Retorna o resultado do texte podendo ser {@code true} se houver conexão ou {@code false}.
     */
    public static SessionFactory getSessionFactory() {
        if (conexao == null) {
            conexao = buildSessionFactory();
        }
        return conexao;
    }
}
