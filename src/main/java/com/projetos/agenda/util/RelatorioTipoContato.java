package com.projetos.agenda.util;

import java.io.*;
import java.util.ArrayList;

public class RelatorioTipoContato {

    public void salvarTipoContato(String[] lineTipoContato){

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

            // faz o for e escreve tudo no arquivo da String pathNewFile
            for (String linesalvarTipoContato : lineTipoContato) {
                bufferedWritersalvarTipoContato.write("- " + linesalvarTipoContato);
                bufferedWritersalvarTipoContato.newLine();
            }

        } catch (IOException e) {
            Alerta.msgInformacao("Foi gerado um erro durante a criação do arquivo!");
            e.printStackTrace();
        }
    }

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
            // FileWriter — Stream de escrita de caracteres em arquivos, acrescentando ao arquivo existente.

            // FileWriter — Stream de escrita de caracteres em arquivos, creando/ recriando o arquivo.
            try (BufferedWriter bufferedWriterTipoContato = new BufferedWriter(new FileWriter(pathNewFileTipoContato, true))) {

                for (String file : lines) {
                    bufferedWriterTipoContato.write("- " + file);
                    bufferedWriterTipoContato.newLine();
                }
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
