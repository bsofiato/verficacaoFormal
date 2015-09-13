package br.usp.ime.mac5732.exercicio1.algoritmo.onthefly;

import br.usp.ime.mac5732.exercicio1.lts.Estado;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BitArray implements Serializable {
  private final Map <Estado, Boolean> bitArray = new HashMap <Estado, Boolean> ();

  private Map<Estado, Boolean> getBitArray() {
    return bitArray;
  }
  
  public BitArray(Set <Estado> estados) {
    estados.forEach(t-> getBitArray().put(t, false));
  }
  public BitArray(Estado ... estados) {
    this(new HashSet <Estado> (Arrays.asList(estados)));
  }
  
  private boolean get(Estado estado) {
    return getBitArray().get(estado);
  }
  
  private void set(Estado estado) {
    getBitArray().put(estado, true);
  }
  
  public void set(AlgorithmIterationState estado) {
    set(estado.getQ1());
    set(estado.getQ2());
  }
  
  public boolean isSetted(AlgorithmIterationState estado) {
    return get(estado.getQ1()) && get(estado.getQ2());
  }
  
  public boolean isAllSetted() {
    if (getBitArray().isEmpty()) {
      return true;
    } else {
      return getBitArray().values().stream().allMatch(t -> t.booleanValue());
    }
  }
}
