/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unesp.rc.modelo;

import br.unesp.rc.modelo.Atributo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luizh
 */
public class Classe {

    private String nomeClasse;
    private String nomePacote;
    private String relacionamento;
    private String tipoRelacionamento;
    private String multiplicidade;
    private String tipoMultiplicidade;

    private List<Atributo> atributo = new ArrayList<>();

    public Classe() {
    }

    public Classe(String nomeClasse, String nomePacote) {
        this.nomeClasse = nomeClasse;
        this.nomePacote = nomePacote;
    }

    public String classeToUml() {
        String uml = "@startuml\ntitle Exemplo\n";
        String umlRelacionamentos = "";
        uml += "Class " + this.nomeClasse + " {\n";
        for (Atributo atributos : this.atributo) {
            System.out.println(atributos.getModificador());
            if (atributos.getModificador().equals("private")) {
                uml += "    - ";
            } else if (atributos.getModificador().equals("public")) {
                uml += "    + ";
            } else {
                uml += "    # ";
            }
            uml += atributos.getTipo() + " " + atributos.getNome() + "\n";

        }
        uml += "    + " + this.nomeClasse + " ()";
        uml += "\n}\n";
        if (!this.tipoRelacionamento.equals("Sem Relacao")) {
            if (this.tipoRelacionamento.equals("Heranca")) {
                umlRelacionamentos += Classe.umlRelacio(this.tipoMultiplicidade, " --|> ", this.nomeClasse, this.relacionamento, this.multiplicidade);
                umlRelacionamentos += this.nomeClasse + " <|-- " + this.relacionamento;
            } else if (this.tipoRelacionamento.equals("Agregacao")) {
                umlRelacionamentos += Classe.umlRelacio(this.tipoMultiplicidade, " --o ", this.nomeClasse, this.relacionamento, this.multiplicidade);
                umlRelacionamentos += this.nomeClasse + " *-- " + this.relacionamento;
            } else if (this.tipoRelacionamento.equals("Composicao")) {
                umlRelacionamentos += Classe.umlRelacio(this.tipoMultiplicidade, " --* ", this.nomeClasse, this.relacionamento, this.multiplicidade);
                umlRelacionamentos += this.nomeClasse + " o-- " + this.relacionamento;
            }
            umlRelacionamentos += "\n";
        } else if (!this.tipoMultiplicidade.equals("Sem Relacao")) {
            umlRelacionamentos += Classe.umlRelacio(this.tipoMultiplicidade, " --> ", this.nomeClasse, this.multiplicidade);
            umlRelacionamentos += "\n";
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
                    resp += nome + " \"1\"" + " --> " + " \"1\" " + mutliplicidade + "\n";
                } else if (multi.equals("1:1..*")) {
                    resp += nome + " \"1\"" + " --> " + " \"1..*\" " + mutliplicidade + "\n";
                } else if (multi.equals("1:0..*")) {
                    resp += nome + " \"1\"" + " --> " + " \"0..*\" " + mutliplicidade + "\n";
                } else if (multi.equals("*:*")) {
                    resp += nome + " \"*\" " + " --> " + " \"*\" " + mutliplicidade + "\n";
                } else if (multi.equals("0..*:0..*")) {
                    resp += nome + " \"0..*\" " + " --> " + " \"0..*\" " + mutliplicidade + "\n";
                } else if (multi.equals("1..*:1..*")) {
                    resp += nome + " \"1..*\" " + " --> " + " \"1..*\" " + mutliplicidade + "\n";
                }
                resp += nome + tipo + relacionamento;
            }
        } else {
            resp += nome + tipo + relacionamento;
        }

        return resp;
    }

    public String getNomeClasse() {
        return nomeClasse;
    }

    public void setNomeClasse(String nomeClasse) {
        this.nomeClasse = nomeClasse;
    }

    public String getNomePacote() {
        return nomePacote;
    }

    public void setNomePacote(String nomePacote) {
        this.nomePacote = nomePacote;
    }

    public List<Atributo> getAtributo() {
        return atributo;
    }

    public void setAtributo(List<Atributo> atributo) {
        this.atributo = atributo;
    }

    public String getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(String relacionamento) {
        this.relacionamento = relacionamento;
    }

    public String getTipoRelacionamento() {
        return tipoRelacionamento;
    }

    public void setTipoRelacionamento(String tipoRelacionamento) {
        this.tipoRelacionamento = tipoRelacionamento;
    }

    public String getMultiplicidade() {
        return multiplicidade;
    }

    public void setMultiplicidade(String multiplicidade) {
        this.multiplicidade = multiplicidade;
    }

    public String getTipoMultiplicidade() {
        return tipoMultiplicidade;
    }

    public void setTipoMultiplicidade(String tipoMultiplicidade) {
        this.tipoMultiplicidade = tipoMultiplicidade;
    }

    public void setAtributoNaLista(Atributo atributos) {
        if (!atributo.contains(atributos)) {
            atributo.add(atributos);
        }
    }

    @Override
    public String toString() {
        return "Classe{" + "nomeClasse=" + nomeClasse + ", nomePacote=" + nomePacote + ", atributo=" + atributo + '}';
    }

}
