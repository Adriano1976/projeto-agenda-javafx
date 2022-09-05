package com.projetos.agenda.util;

import java.io.*;
import java.util.ArrayList;

/**
 * Classe responsável em controlar a geração de arquivos no formato "csv" dos tipos de contatos.
 *
 * @author Adriano Santos
 */

public class RelatorioTipoContato {

    private final DataLocal dataLocal = new DataLocal();
    /**
     * Método responsável em salvar os dados de tipo de contato no formato csv no caminho informado
     * pela variável <code>pathsalvarTipoContato</code>.
     *
     * @param lineTipoContato Fesponsável em receber os dados dos tipos contatos e salvar na pasta "temp".     *
     */

    public void salvarTipoContato(String[] lineTipoContato) {

        String pathsalvarTipoContato = "C:\\temp\\relatorio\\TipoContato.csv";
        //criar um Filé para pegar o caminho.
        File pathsourcesalvarTipoContato = new File(pathsalvarTipoContato);
        //pega somente o caminho do arquivo e coloca numa ‘string’ no "pathfull", ou seja, através do .getParent().
        String pathfullsalvarTipoContato = pathsourcesalvarTipoContato.getParent();
        //cria pasta, aqui criei uma pasta temp., ou seja, via ficar c:\\temp\temp\\
        boolean sucess = new File(pathfullsalvarTipoContato + "C:\\temp\\relatorio").mkdir();
        // Criando novo arquivo .csv
        String pathNewFilesalvarTipoContato = pathfullsalvarTipoContato + " - TipoContato.csv";
        // FileWriter — Stream de escrita de caracteres em arquivos, acrescentando ao arquivo existente.
        try (BufferedWriter bufferedWritersalvarTipoContato = new BufferedWriter(new FileWriter(pathNewFilesalvarTipoContato, true))) {

            bufferedWritersalvarTipoContato.write(dataLocal.getDateTime() + "; ");
            // faz o for e escreve tudo no arquivo da String pathNewFile
            for (String lineSTC : lineTipoContato) {
                bufferedWritersalvarTipoContato.write(lineSTC + "; ");

            }
            bufferedWritersalvarTipoContato.newLine();

        } catch (IOException e) {
            Alerta.msgInformacao("Foi gerado um erro durante a criação do arquivo!");
            e.printStackTrace();
        }
    }

    /**
     * Método responsável em gerar o relatório de Contato no formato "csv".
     * Ele está dividido em 2 etapas:
     * — Ler os dados do arquivo já salvo no variável <code>pathTipoContato</code> usando o método {@link BufferedReader}.
     * — Escrever os dados num novo arquivo usando o método {@link BufferedWriter}.
     */
    public void gerarRelatorioTipoContato() {

        String pathTipoContato = "C:\\temp\\relatorio - TipoContato.csv";

        try (BufferedReader bufferedReaderTipoContato = new BufferedReader(new FileReader(pathTipoContato))) {

            // faz o for e escreve tudo no arquivo da String pathNewFile
            ArrayList<String> lines = new ArrayList<>(); // Read all the lines and save it on an Array
            String line = bufferedReaderTipoContato.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReaderTipoContato.readLine();
            }

            String pathoutTipoContato = "C:\\Users\\ADRIANO\\Dropbox\\PC (2)\\Documents\\logAgenda.csv";
            //criar um Filé para pegar o caminho.
            File pathsourceTipoContato = new File(pathoutTipoContato);
            //pega somente o caminho do arquivo e coloca numa ‘string’ no "pathfull", ou seja, através do .getParent().
            String pathfullTipoContato = pathsourceTipoContato.getParent();
            //cria pasta, aqui criei uma pasta temp., ou seja, via ficar c:\\temp\temp\\
            boolean sucess = new File(pathfullTipoContato + "\\Agenda").mkdir();
            // Criando novo arquivo .csv
            String pathNewFileTipoContato = pathfullTipoContato + "\\Agenda\\relatorioTipoContato.csv";

            // FileWriter — Stream de escrita de caracteres em arquivos, creando/ recriando o arquivo.
            try (BufferedWriter bufferedWriterTipoContato = new BufferedWriter(new FileWriter(pathNewFileTipoContato))) {

                for (String fileGRTC : lines) {
                    bufferedWriterTipoContato.write(fileGRTC);
                    bufferedWriterTipoContato.newLine();
                }
                bufferedWriterTipoContato.newLine();

                Alerta.msgInformacao(
                        "Relatório Tipo Contato criado com sucesso!\nLocal: C:\\Users\\ADRIANO\\Dropbox\\PC (2)\\Documents\\Agenda"
                );

            } catch (IOException e) {
                e.printStackTrace();
                Alerta.msgInformacao("Erro ao criar relatório Tipo Contato!");
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}
