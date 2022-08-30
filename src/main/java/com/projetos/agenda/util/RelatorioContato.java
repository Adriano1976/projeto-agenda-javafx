package com.projetos.agenda.util;

import java.io.*;
import java.util.ArrayList;

public class RelatorioContato {

    public void salvarContato(String[] lineContato){

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
            for (String line : lineContato) {
                bufferedWritersalvarContato.write("- " + line);
                bufferedWritersalvarContato.newLine();
            }

        } catch (IOException e) {
            Alerta.msgInformacao("Foi gerado um erro durante a criação do arquivo!");
            e.printStackTrace();
        }
    }

    public void gerarRelatorioContato() {

        String pathGerar = "C:\\temp\\relatorio - Contato.csv";

        try (BufferedReader bufferedReaderContato = new BufferedReader(new FileReader(pathGerar))) {

            // faz o for e escreve tudo no arquivo da String pathNewFile
            ArrayList<String> lines = new ArrayList<>(); // Read all the lines and save it on an Array
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
            // FileWriter — Stream de escrita de caracteres em arquivos, acrescentando ao arquivo existente.

            // FileWriter — Stream de escrita de caracteres em arquivos, creando/ recriando o arquivo.
            try (BufferedWriter bufferedWriterContato = new BufferedWriter(new FileWriter(pathNewFileContato, true))) {

                for (String file : lines) {
                    bufferedWriterContato.write("- " + file);
                    bufferedWriterContato.newLine();
                }
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
