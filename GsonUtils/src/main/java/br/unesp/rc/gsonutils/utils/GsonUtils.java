package br.unesp.rc.gsonutils.utils;

import br.unesp.rc.modelo.Classe;
import com.google.gson.Gson;

/**
 * Esta é uma classe utilitária para conversão de objetos em JSON e vice-versa.
 * 
 * @author Prof. Frank J. Affonso
 */
public class GsonUtils {

    private GsonUtils() {
    }

    /**
     * Converte um objeto em JSON
     * 
     * @param objeto é um objeto de uma classe que será convertido em JSON.
     * 
     * @return String que é o arquivo em JSON
     */
    public static String objetoToXML(Classe objeto) {
        Gson gson = new Gson();

        return gson.toJson(objeto);
    }

    /**
     * Converte uma string (arquivo JSON) em um objeto.
     * 
     * @param json é o arquivo JSON de uma classe
     * 
     * @param classe representa a classe a ser convertida
     * 
     * @return um objeto da classe fornecida como parâmetro
     */
    public static Object xmlToObjeto(String json, Class classe) {
        Gson gson = new Gson();

        return gson.fromJson(json, classe);
    }
}
