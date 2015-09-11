package br.usp.ime.mac5732.exercicio1.lts;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Estado implements Serializable {  
  
  private String nome;

  private Map <String, Estado> entradas = new HashMap <String, Estado> ();
  private Map <String, Estado> saidas = new HashMap <String, Estado> ();

  public Estado(String nome) {
    this.nome = nome;
  }

  public Estado() {
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Map<String, Estado> getEntradas() {
    return entradas;
  }

  public void setEntradas(Map<String, Estado> entradas) {
    if (entradas == null) {
      getEntradas().clear();
    } else {
      this.entradas = entradas;
    }
  }

  public Map<String, Estado> getSaidas() {
    return saidas;
  }

  public void setSaidas(Map<String, Estado> saidas) {
    if (saidas == null) {
      getSaidas().clear();
    } else {
      this.saidas = saidas;
    }
  }
}
