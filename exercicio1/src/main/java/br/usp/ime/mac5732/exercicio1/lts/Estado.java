package br.usp.ime.mac5732.exercicio1.lts;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Estado implements Serializable {  
  
  private String nome;

  private Map <String, Set <Estado>> entradas = new HashMap <String, Set <Estado>> ();
  private Map <String, Set <Estado>> saidas = new HashMap <String, Set <Estado>> ();
  
  private void initEntradaSaida(String alfabetoEntry) {
    getEntradas().put(alfabetoEntry, new HashSet<Estado>());
    getSaidas().put(alfabetoEntry, new HashSet<Estado>());
  }

  private Estado() {
  }
  public Estado(String nome, Set<String> alfabeto) {
    this.nome = nome;
    alfabeto.stream().forEach(t->initEntradaSaida(t));
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Map<String, Set <Estado>> getEntradas() {
    return entradas;
  }

  public void setEntradas(Map<String, Set <Estado>> entradas) {
    if (entradas == null) {
      getEntradas().clear();
    } else {
      this.entradas = entradas;
    }
  }

  public Map<String, Set <Estado>> getSaidas() {
    return saidas;
  }

  public void setSaidas(Map<String, Set <Estado>> saidas) {
    if (saidas == null) {
      getSaidas().clear();
    } else {
      this.saidas = saidas;
    }
  }
}
