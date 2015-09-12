package br.usp.ime.mac5732.exercicio1.algoritmo.onthefly;

import java.io.Serializable;

public class AlgorithmIterationStateSucessor implements Serializable {
  private final String action;
  private final AlgorithmIterationState algorithmIterationState;

  public AlgorithmIterationStateSucessor(String action, AlgorithmIterationState algorithmIterationState) {
    this.action = action;
    this.algorithmIterationState = algorithmIterationState;
  }
  
  public String getAction() {
    return action;
  }

  public AlgorithmIterationState getAlgorithmIterationState() {
    return algorithmIterationState;
  }
  
}
