package br.usp.ime.mac5732.exercicio1.lts;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class LTS implements Serializable {
  private final Set <String> alfabeto;
  private final Set <Estado> estados = new HashSet <Estado> ();
  
  private Estado estadoInicial;

  public LTS(Set <String> alfabeto) {
    this.alfabeto = alfabeto;
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

  public Set<Estado> getEstados() {
    return estados;
  }

  public Estado addEstado(String estado) {
    Estado e = new Estado(this, estado, getAlfabeto());
    getEstados().add(e);
    return e;
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
