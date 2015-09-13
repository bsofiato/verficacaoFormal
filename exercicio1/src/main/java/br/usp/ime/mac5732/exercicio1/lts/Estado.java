package br.usp.ime.mac5732.exercicio1.lts;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Estado implements Serializable {  
  
  private final LTS lts;
  private final String nome;

  private final Map <String, Set <Estado>> entradas = new HashMap <String, Set <Estado>> ();
  private final Map <String, Set <Estado>> saidas = new HashMap <String, Set <Estado>> ();
  
  private void initEntradaSaida(String alfabetoEntry) {
    getEntradas().put(alfabetoEntry, new HashSet<Estado>());
    getSaidas().put(alfabetoEntry, new HashSet<Estado>());
  }

  public Estado(LTS lts, String nome) {
    this.lts = lts;
    this.nome = nome;
    this.lts.getAlfabeto().stream().forEach(t->initEntradaSaida(t));
  }

  public String getNome() {
    return nome;
  }

  public Map<String, Set <Estado>> getEntradas() {
    return entradas;
  }

  public Map<String, Set <Estado>> getSaidas() {
    return saidas;
  }

  public LTS getLTS() {
    return lts;
  }
}
