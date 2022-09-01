package com.projetos.agenda.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * <h3>Classe Responsável em controlar e criar os logs de erro ocorridos</h3>
 *
 * @author Adriano Santos
 */
public class ArquivoLog {

    /**
     * Método responsável em identificar o local onde ficarar o arquivo log por meio da classe
     * {@link File} e inscrita na extensão txt pela classe {@linkplain BufferedWriter}.
     *
     * @param logs Responsável em receber as informações do erro vindo de um determinado método ou classe.
     */
    public void salvarLogs(String[] logs) {

        String pathLog = "C:\\temp\\relatorio\\logsAgenda.txt";
        //criar um Filé para pegar o caminho.
        File pathsourceLog = new File(pathLog);
        //pega somente o caminho do arquivo e coloca numa ‘string’ no "pathfull", ou seja, através do .getParent().
        String pathfullLog = pathsourceLog.getParent();
        //cria pasta, aqui criei uma pasta temp., ou seja, via ficar c:\\temp\temp\\
        boolean sucess = new File(pathfullLog + "C:\\temp\\relatorio").mkdir();
        // Criando novo arquivo .csv
        String pathNewFileLog = pathfullLog + " - logsAgenda.txt";
        // FileWriter — Stream de escrita de caracteres em arquivos, acrescentando ao arquivo existente.

        try (BufferedWriter bufferedWriterLog = new BufferedWriter(new FileWriter(pathNewFileLog, true))) {

            // faz o for e escreve tudo no arquivo da String pathNewFile
            for (String lineLog : logs) {
                bufferedWriterLog.write("Acorreu o erro: " + lineLog);
                bufferedWriterLog.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();

        }

    }
}
