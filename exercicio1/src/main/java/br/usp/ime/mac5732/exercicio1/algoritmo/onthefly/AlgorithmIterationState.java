package br.usp.ime.mac5732.exercicio1.algoritmo.onthefly;

import br.usp.ime.mac5732.exercicio1.lts.Estado;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class AlgorithmIterationState {

  private final Estado q1;
  private final Estado q2;
  private Deque<AlgorithmIterationStateSucessor> sucessors;

  private Set<String> getActions() {
    Set<String> actions = new HashSet<String>(getQ1().getSaidas().keySet());
    actions.addAll(getQ2().getSaidas().keySet());
    return actions;
  }

  private Collection<Estado> getSaidasNormalizadas(Estado estado, String action) {
    Collection<Estado> estados = estado.getSaidas().get(action);
    if (estados.isEmpty()) {
      estados = new ArrayList<Estado> ();
      estados.add(null);
    }
    return estados;
  }

  private List<AlgorithmIterationStateSucessor> createSucessors(String action) {
    List<AlgorithmIterationStateSucessor> sucessors = new ArrayList<AlgorithmIterationStateSucessor>();
    Collection<Estado> q1Succs = getSaidasNormalizadas(getQ1(), action);
    Collection<Estado> q2Succs = getSaidasNormalizadas(getQ1(), action);
    for (Estado q1Succ : q1Succs) {
      for (Estado q2Succ : q2Succs) {
        sucessors.add(new AlgorithmIterationStateSucessor(action, new AlgorithmIterationState(q1Succ, q2Succ)));
      }
    }
    return sucessors;
  }

  private void initSucessors() {
    if (getQ1() != null && getQ2() != null) {
      this.sucessors = new LinkedList<AlgorithmIterationStateSucessor>(getActions().stream().map(t -> createSucessors(t)).flatMap(t -> t.stream()).collect(Collectors.toList()));
    } else {
      this.sucessors = new LinkedList<AlgorithmIterationStateSucessor>();
    }
  }

  public Deque<AlgorithmIterationStateSucessor> getSucessors() {
    if (this.sucessors == null) {
      initSucessors();
    }
    return this.sucessors;
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

  public boolean hasSucessors() {
    return !getSucessors().isEmpty();
  }

  public AlgorithmIterationState chooseAndRemove() {
    return getSucessors().pop().getAlgorithmIterationState();
  }

  @Override
  public int hashCode() {
    int hash = 3;
    hash = 89 * hash + Objects.hashCode(getQ1());
    hash = 89 * hash + Objects.hashCode(getQ2());
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final AlgorithmIterationState other = (AlgorithmIterationState) obj;
    if (!Objects.equals(this.getQ1(), other.getQ1())) {
      return false;
    }
    if (!Objects.equals(this.getQ1(), other.getQ2())) {
      return false;
    }
    return true;
  }
}
