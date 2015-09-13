package br.usp.ime.mac5732.exercicio1.algoritmo.onthefly;

import br.usp.ime.mac5732.exercicio1.lts.Estado;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AlgorithmIterationState {
  private final Estado q1;
  private final Estado q2;
  private final boolean failure;
  
  private Set <String> getActions() {
    Set <String> actions = new HashSet <String> (getQ1().getSaidas().keySet());
    actions.addAll(getQ2().getSaidas().keySet());
    return actions;
  }
  
  private Collection <Estado> getSaidasNormalizadas(Estado estado, String action) {
    Collection <Estado> estados = estado.getSaidas().get(action);
    return (estados.isEmpty()) ? Arrays.asList(null) : estados;
  }
  
  private List<AlgorithmIterationStateSucessor> createSucessors(String action) {
    List <AlgorithmIterationStateSucessor> sucessors = new ArrayList<AlgorithmIterationStateSucessor>();
    Collection <Estado> q1Succs  = getSaidasNormalizadas(getQ1(), action);
    Collection <Estado> q2Succs  = getSaidasNormalizadas(getQ1(), action);
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
    this.failure = (q1 == null) || (q2 == null);
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
    if (!isFailure()) {
      return getActions().stream().map(t-> createSucessors(t)).flatMap(t->t.stream()).collect(Collectors.toList());
    } else { 
      throw new IllegalStateException("Nao se pode pegar sucessores de estados em falha");
    }
  }

  public boolean isFailure() {
    return failure;
  }
}
