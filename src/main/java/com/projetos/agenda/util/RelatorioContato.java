package com.projetos.agenda.util;

import java.io.*;
import java.util.ArrayList;

/**
 * Classe responsável em controlar a geração de arquivos no formato csv dos contatos.
 *
 * @author Adriano Santos
 */
public class RelatorioContato {

    private final DataLocal dataLocal = new DataLocal();
    /**
     * Método responsável em salvar os dados de contato no formato csv no caminho informado
     * pela variável <code>pathsalvarContato</code>.
     *
     * @param lineContato Responsável em receber os dados de contato e salvar na pasta "temp".
     */
    public void salvarContato(String[] lineContato) {

        String pathsalvarContato = "C:\\temp\\relatorio\\Contato.csv";
        //criar um Filé para pegar o caminho.
        File pathsource = new File(pathsalvarContato);
        //pega somente o caminho do arquivo e coloca numa ‘string’ no "pathfull", ou seja, através do .getParent().
        String pathfull = pathsource.getParent();
        //cria pasta, aqui criei uma pasta temp., ou seja, via ficar c:\\temp\temp\\
        boolean sucess = new File(pathfull + "C:\\temp\\relatorio").mkdir();
        // Criando novo arquivo .csv
        String pathNewFileSalvarContato = pathfull + " - Contato.csv";
        // FileWriter — Stream de escrita de caracteres em arquivos, acrescentando ao arquivo existente.
        try (BufferedWriter bufferedWritersalvarContato = new BufferedWriter(new FileWriter(pathNewFileSalvarContato, true))) {

            // faz o for e escreve tudo no arquivo da String pathNewFile
            bufferedWritersalvarContato.write(dataLocal.getDateTime() + "; ");
            for (String lineSC : lineContato) {
                bufferedWritersalvarContato.write(lineSC + "; ");
            }
            bufferedWritersalvarContato.newLine();

        } catch (IOException e) {
            Alerta.msgInformacao("Foi gerado um erro durante a criação do arquivo!");
            e.printStackTrace();
        }
    }

    /**
     * Método responsável em gerar o relatório de Contato no formato "csv".
     * Ele está dividido em 2 etapas:
     * — Ler os dados do arquivo já salvo no variável <code>pathGerar</code> usando o método <code>BufferedReader</code>.
     * — Escrever os dados num novo arquivo usando o método <code>BufferedWriter</code>.
     */
    public void gerarRelatorioContato() {

        String pathGerar = "C:\\temp\\relatorio - Contato.csv";

        try (BufferedReader bufferedReaderContato = new BufferedReader(new FileReader(pathGerar))) {

            // faz o for e escreve tudo no arquivo da String pathNewFile
            ArrayList<String> lines = new ArrayList<>();
            String line = bufferedReaderContato.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReaderContato.readLine();
            }

            String pathoutContato = "C:\\Users\\ADRIANO\\Dropbox\\PC (2)\\Documents\\logAgenda.csv";
            //criar um Filé para pegar o caminho.
            File pathsourceContato = new File(pathoutContato);
            //pega somente o caminho do arquivo e coloca numa ‘string’ no "pathfull", ou seja, através do .getParent().
            String pathfullContato = pathsourceContato.getParent();
            //cria pasta, aqui criei uma pasta temp., ou seja, via ficar c:\\temp\temp\\
            boolean sucess = new File(pathfullContato + "\\Agenda").mkdir();
            // Criando novo arquivo .csv
            String pathNewFileContato = pathfullContato + "\\Agenda\\relatorioContato.csv";

            // FileWriter — Stream de escrita de caracteres em arquivos, creando/ recriando o arquivo.
            try (BufferedWriter bufferedWriterContato = new BufferedWriter(new FileWriter(pathNewFileContato))) {

                for (String fileGRC : lines) {
                    bufferedWriterContato.write(fileGRC);
                    bufferedWriterContato.newLine();
                }
                bufferedWriterContato.newLine();

                Alerta.msgInformacao(
                        "Relatório Contato criado com sucesso!\nLocal: C:\\Users\\ADRIANO\\Dropbox\\PC (2)\\Documents\\Agenda"
                );

            } catch (IOException e) {
                e.printStackTrace();
                Alerta.msgInformacao("Erro ao criar relatório Contato!");
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
