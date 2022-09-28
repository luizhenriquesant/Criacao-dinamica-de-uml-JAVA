/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unesp.rc.repositorio;

import java.util.ArrayList;
import java.util.List;
import br.unesp.rc.modelo.Atributo;
import br.unesp.rc.modelo.Classe;

/**
 *
 * @author luizh
 */
public class ClasseRepositorio {

    private static List<Classe> classes = new ArrayList<>();

    public ClasseRepositorio() {
    }

    public static void adicionarClasse(Classe classe) {
        if (!classes.contains(classe)) {
            classes.add(classe);
        }
    }

    public static void listarTodos() {
        for (Classe classe : classes) {
            System.out.println(classe);
        }
    }

    public static List<Classe> getClasses() {
        return classes;
    }

    public static String classeToUml() {
        String uml = "@startuml\ntitle Exemplo\n";
        String umlRelacionamentos = "";
        for (Classe classe : classes) {
            uml += "Class " + classe.getNomeClasse() + " {\n";
            for (Atributo atributo : classe.getAtributo()) {

                if (atributo.getModificador().equals("private")) {
                    uml += "    - ";
                } else if (atributo.getModificador().equals("public")) {
                    uml += "    + ";
                } else {
                    uml += "    # ";
                }
                uml += atributo.getTipo() + " " + atributo.getNome() + "\n";
            }
            uml += "    + " + classe.getNomeClasse() + " ()";
            uml += "\n}\n";
            if (!classe.getTipoRelacionamento().equals("Sem Relacao")) {
                if (classe.getTipoRelacionamento().equals("Heranca")) {
                    umlRelacionamentos += ClasseRepositorio.umlRelacio(classe.getTipoMultiplicidade(), " --|> ", classe.getNomeClasse(), classe.getRelacionamento(), classe.getMultiplicidade());
                } else if (classe.getTipoRelacionamento().equals("Agregacao")) {
                    umlRelacionamentos += ClasseRepositorio.umlRelacio(classe.getTipoMultiplicidade(), " --o ", classe.getNomeClasse(), classe.getRelacionamento(), classe.getMultiplicidade());
                } else if (classe.getTipoRelacionamento().equals("Composicao")) {
                    umlRelacionamentos += ClasseRepositorio.umlRelacio(classe.getTipoMultiplicidade(), " --* ", classe.getNomeClasse(), classe.getRelacionamento(), classe.getMultiplicidade());
                }
                umlRelacionamentos += "\n";
            } else if (!classe.getTipoMultiplicidade().equals("Sem Relacao")) {
                umlRelacionamentos += ClasseRepositorio.umlRelacio(classe.getTipoMultiplicidade(), " --> ", classe.getNomeClasse(), classe.getMultiplicidade());
                umlRelacionamentos += "\n";
            }
        }
        uml += umlRelacionamentos;
        uml += "@enduml";

        return uml;
    }

    public static String umlRelacio(String multi, String tipo, String nome, String mutliplicidade) {
        String resp = "";
        if (multi.equals("1:1")) {
            resp += nome + " \"1\"" + tipo + " \"1\" " + mutliplicidade;
        } else if (multi.equals("1:1..*")) {
            resp += nome + " \"1\"" + tipo + " \"1..*\" " + mutliplicidade;
        } else if (multi.equals("1:0..*")) {
            resp += nome + " \"1\"" + tipo + " \"0..*\" " + mutliplicidade;
        } else if (multi.equals("*:*")) {
            resp += nome + " \"*\" " + tipo + " \"*\" " + mutliplicidade;
        } else if (multi.equals("0..*:0..*")) {
            resp += nome + " \"0..*\" " + tipo + " \"0..*\" " + mutliplicidade;
        } else if (multi.equals("1..*:1..*")) {
            resp += nome + " \"1..*\" " + tipo + " \"1..*\" " + mutliplicidade;
        }
        return resp;
    }

    public static String umlRelacio(String multi, String tipo, String nome, String relacionamento, String mutliplicidade) {
        String resp = "";
        if (!multi.equals("Sem Relacao")) {
            if (mutliplicidade.equals(relacionamento)) {
                if (multi.equals("1:1")) {
                    resp += nome + " \"1\"" + tipo + " \"1\" " + relacionamento;
                } else if (multi.equals("1:1..*")) {
                    resp += nome + " \"1\"" + tipo + " \"1..*\" " + relacionamento;
                } else if (multi.equals("1:0..*")) {
                    resp += nome + " \"1\"" + tipo + " \"0..*\" " + relacionamento;
                } else if (multi.equals("*:*")) {
                    resp += nome + " \"*\" " + tipo + " \"*\" " + relacionamento;
                } else if (multi.equals("0..*:0..*")) {
                    resp += nome + " \"0..*\" " + tipo + " \"0..*\" " + relacionamento;
                } else if (multi.equals("1..*:1..*")) {
                    resp += nome + " \"1..*\" " + tipo + " \"1..*\" " + relacionamento;
                }
            } else {
                if (multi.equals("1:1")) {
                    resp += nome + " \"1\"" + " --> " + " \"1\" " + mutliplicidade+"\n";
                } else if (multi.equals("1:1..*")) {
                    resp += nome + " \"1\"" + " --> " + " \"1..*\" " + mutliplicidade+"\n";
                } else if (multi.equals("1:0..*")) {
                    resp += nome + " \"1\"" + " --> " + " \"0..*\" " + mutliplicidade+"\n";
                } else if (multi.equals("*:*")) {
                    resp += nome + " \"*\" " + " --> " + " \"*\" " + mutliplicidade+"\n";
                } else if (multi.equals("0..*:0..*")) {
                    resp += nome + " \"0..*\" " + " --> " + " \"0..*\" " + mutliplicidade+"\n";
                } else if (multi.equals("1..*:1..*")) {
                    resp += nome + " \"1..*\" " + " --> " + " \"1..*\" " + mutliplicidade+"\n";
                }
                resp += nome + tipo + relacionamento;
            }
        } else {
            resp += nome + tipo + relacionamento;
        }

        return resp;
    }

}
