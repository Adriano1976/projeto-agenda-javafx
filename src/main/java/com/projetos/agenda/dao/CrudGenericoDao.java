package com.projetos.agenda.dao;

import com.projetos.agenda.util.ArquivoLog;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Classe responsável em controlar a ação de criar, salvar, alterar e excluir informações
 * contidas na base de dados</p>
 *
 * @author Adriano Santos
 *
 * @param <T> Recebe o nome de uma classe ou método generico.
 */
public class CrudGenericoDao<T> {

    /**
     * Variável responsável em representar o nome da classe generica.
     */
    private final Class<T> classeNome;

    private final ArquivoLog log = new ArquivoLog();

    /**
     * @param classeNome Parâmetro usado pelo construtor da classe.
     */
    public CrudGenericoDao(Class<T> classeNome) {
        this.classeNome = classeNome;
    }

    /**
     * Método responsável em controlar e salvar as informações na base de dados
     * depois que for feito a conexão pela classe {@link ConexaoBanco}. Se ocorrer
     * algum erro na tentativa de salvamento dos dados.
     *
     * @param nomeClasse Parâmetro que irá receber o nome da classe solicitante.
     * @return Retorna {@code true} se os dados foram salvos ou {@code false}.
     */
    public boolean salvar(T nomeClasse) {
        try {
            Session session = ConexaoBanco.getSessionFactory().openSession();
            session.beginTransaction();
            session.merge(nomeClasse);
            session.getTransaction().commit();
            session.close();
            return true;

        } catch (Exception erro) {
            String[] lines = new String[]{
                    erro.getMessage()
            };

            log.salvarLogs(lines);
            return false;
        }
    }

    /**
     * Método responsável em controlar e excluir as informações na base de dados
     * depois que for feito a conexão pela classe {@link ConexaoBanco}. Se ocorrer
     * algum erro na tentativa de salvamento dos dados.
     *
     * @param nomeClasse Parâmetro usado pelo construtor da classe.
     */
    public void excluir(T nomeClasse) {
        try {
            Session session = ConexaoBanco.getSessionFactory().openSession();
            session.beginTransaction();
            session.remove(nomeClasse);
            session.getTransaction().commit();
            session.clear();
            System.out.println("Registro excluido com sucesso!");

        } catch (Exception erro) {
            String[] lines = new String[]{
                    erro.getMessage()
            };

            log.salvarLogs(lines);
        }
    }

    /**
     * Método responsável em controlar e consultar as informações na base de dados
     * depois que for feito a conexão pela classe {@link ConexaoBanco}. Essa consulta
     * está condicionada onde SE
     * Se ocorrer
     * algum erro na tentativa de salvamento dos dados.
     *
     * @param descricao Responsável em receber a descrição dos dados de solicitação.
     * @return Retorna uma lista de dados solicitados por outra classe ou método.
     */
    public List<T> consultar(String descricao) {
        List<T> lista = new ArrayList<>();
        Session session = ConexaoBanco.getSessionFactory().openSession();
        session.beginTransaction();
        String query;
        if (descricao.length() == 0) {
            query = "select object from " + classeNome.getName() + " object";
        } else {
            query = "select object from " + classeNome.getName() + " object" + " where object.descricao like " + "'" + descricao + "%'";
        }
        lista = session.createQuery(query, classeNome).getResultList();
        session.getTransaction().commit();
        session.close();

        return lista;
    }
}
