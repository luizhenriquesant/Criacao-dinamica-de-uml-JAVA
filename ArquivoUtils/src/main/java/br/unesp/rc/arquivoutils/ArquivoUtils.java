package br.unesp.rc.arquivoutils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Esta classe permite escrever e ler em/de arquivos.
 *
 * @author Prof. Frank J. Affonso
 */
public class ArquivoUtils {

    private ArquivoUtils() {

    }

    /**
     * Este método permite salvar um arquivo em um diretório
     * 
     * @param diretorio local onde será salvo o arquivo. Exemplo: "C:\Teste\"
     * @param nomeArquivoComExtensao nome do arquivo com extensão. Exemplo: "teste.json"
     * @param conteudo conteúdo do arquivo
     * @return true|false para execução dessa operação
     */
    public static boolean salvar(String diretorio,
            String nomeArquivoComExtensao,
            String conteudo) {
        boolean b = false;

        String arquivo = diretorio + nomeArquivoComExtensao;
        File file = new File(arquivo);
        if (file.exists()){
            file.delete();
        }
        
        try {
            FileWriter writer = new FileWriter(arquivo, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(conteudo);
            bufferedWriter.close();
            b = true;
        } catch (IOException ex) {
            System.out.println("Mensagem: " + ex.getMessage());
        }

        return b;
    }

    /**
     * Este método permite ler um arquivo localizado em um diretório
     * 
     * @param diretorioCompleto caminho completo onde está localizado o arquivo. Exemplo: "C:\Teste\teste.json"
     * @return conteúdo do arquivo.
     */
    public static String leitura(String diretorioCompleto) {
        StringBuilder conteudo = new StringBuilder();

        try {
            FileReader reader = new FileReader(diretorioCompleto);
            BufferedReader bufferedReader = new BufferedReader(reader);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                conteudo.append(line).append("\n");
            }
            reader.close();

        } catch (IOException ex) {
            System.out.println("Mensagem: " + ex.getMessage());
        }
        return conteudo.toString();
    }
}
