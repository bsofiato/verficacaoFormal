package br.usp.ime.mac5732.exercicio1.algoritmo.onthefly;

import br.usp.ime.mac5732.exercicio1.lts.Estado;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AlgorithmIterationState {
  private final Estado q1;
  private final Estado q2;
  
  private Set <String> getActionsIntersection() {
    Set <String> intersection = new HashSet <String> (getQ1().getSaidas().keySet());
    intersection.retainAll(getQ2().getSaidas().keySet());
    return intersection;
  }
  
  private List<AlgorithmIterationStateSucessor> createSucessors(String action) {
    List <AlgorithmIterationStateSucessor> sucessors = new ArrayList<AlgorithmIterationStateSucessor>();
    Collection <Estado> q1Succs  = getQ1().getSaidas().get(action);
    Collection <Estado> q2Succs  = getQ2().getSaidas().get(action);
    for (Estado q1Succ : q1Succs) {
      for (Estado q2Succ : q2Succs) {
        sucessors.add(new AlgorithmIterationStateSucessor(action, new AlgorithmIterationState(q1Succ, q2Succ)));
      }
    }
    return sucessors;
  }

  public AlgorithmIterationState(Estado q1, Estado q2) {
    this.q1 = q1;
    this.q2 = q2;
  }

  public Estado getQ1() {
    return q1;
  }

  public Estado getQ2() {
    return q2;
  }
  
  /**
   * Equivalent to the succ function
   */
  public List <AlgorithmIterationStateSucessor> getSucessors() {
    return getActionsIntersection().stream().map(t-> createSucessors(t)).flatMap(t->t.stream()).collect(Collectors.toList());
  }
}
