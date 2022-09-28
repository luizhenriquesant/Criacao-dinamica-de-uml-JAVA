/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unesp.rc.repositorio;

import java.util.ArrayList;
import java.util.List;
import br.unesp.rc.modelo.Atributo;

/**
 *
 * @author luizh
 */
public class AtributoRepositorio {

    private static List<Atributo> atributos = new ArrayList<>();

    private static void adicionarAtributo(Atributo atributo) {
        if (!atributos.contains(atributo)) {
            atributos.add(atributo);
        }
    }

    private static void listarTodosAtributos() {
        for (Atributo atributo : atributos) {
            System.out.println(atributo);
        }
    }

    public static List<Atributo> getAtributos() {
        return atributos;
    }

}
