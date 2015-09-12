package br.usp.ime.mac5732.exercicio1.lts;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class LTS implements Serializable {
  private Set <String> alfabeto = new HashSet <String> ();
  private Set <Estado> estados = new HashSet <Estado> ();
  
  private Estado estadoInicial;

  public LTS() {
  }

  public LTS(Set <String> alfabeto, Set <Estado> estados, Estado estadoInicial) {
    setAlfabeto(alfabeto);
    setEstados(estados);
    setEstadoInicial(estadoInicial);
  }

  public Estado getEstadoInicial() {
    return estadoInicial;
  }

  public void setEstadoInicial(Estado estadoInicial) {
    this.estadoInicial = estadoInicial;
  }

  public Set<String> getAlfabeto() {
    return alfabeto;
  }

  public void setAlfabeto(Set<String> alfabeto) {
    if (alfabeto == null) {
      getAlfabeto().clear();
    } else {
      this.alfabeto = alfabeto;
    }
  }

  public Set<Estado> getEstados() {
    return estados;
  }

  public void setEstados(Set<Estado> estados) {
    if (estados == null) {
      getEstados().clear();
    } else {
      this.estados = estados;
    }
  }
  
  public Estado getEstado(String estado) {
    for (Estado e : getEstados()) {
      if (e.getNome().equals(estado)) {
        return e;
      }
    }
    return null;
  }
  
  
}
