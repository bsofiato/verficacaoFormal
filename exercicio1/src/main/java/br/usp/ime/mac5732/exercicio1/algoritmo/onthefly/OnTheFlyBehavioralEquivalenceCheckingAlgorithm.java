package br.usp.ime.mac5732.exercicio1.algoritmo.onthefly;

import br.usp.ime.mac5732.exercicio1.algoritmo.EquivalenceCheckinAlgorithm;
import br.usp.ime.mac5732.exercicio1.algoritmo.ProgressFeedbackListener;
import br.usp.ime.mac5732.exercicio1.lts.LTS;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class OnTheFlyBehavioralEquivalenceCheckingAlgorithm implements EquivalenceCheckinAlgorithm {
  private enum Result {
    TRUE, 
    FALSE, 
    UNRELIABLE
  }
  
  private boolean isInUnion(AlgorithmIterationState state, Set <AlgorithmIterationState> v, Set <AlgorithmIterationState> w) {
    return (v.contains(state) || w.contains(state));
  }
  
  private boolean isAllSet(BitSet bitSet) {
    return bitSet.cardinality() == bitSet.size();
  }
  
  private Stack <BitSet> createST2(LTS op1, LTS op2) {
    Stack <BitSet> st2 = new Stack <BitSet> ();
    st2.push(new BitSet(2));
    st2.push(new BitSet(op1.getEstadoInicial().getSaidas().size() + op2.getEstadoInicial().getSaidas().size()));
    return st2;
  }
  
  private Stack <AlgorithmIterationStateSucessor> createST1(LTS op1, LTS op2) {
    AlgorithmIterationState estadoInicial =  new AlgorithmIterationState(op1.getEstadoInicial(), op2.getEstadoInicial());
    Stack <AlgorithmIterationStateSucessor> st1 = new Stack<AlgorithmIterationStateSucessor> ();
    st1.add(new AlgorithmIterationStateSucessor(null, estadoInicial));
    st1.addAll(estadoInicial.getSucessors());
    return st1;
  }
  
  private Result partialDFS(LTS op1, LTS op2, Set <AlgorithmIterationState> w, ProgressFeedbackListener listener) {
    boolean stable = false;
    Stack <AlgorithmIterationStateSucessor> st1 = createST1(op1, op2);
    Stack <BitSet> st2 = createST2(op1, op2);
    
    Set <AlgorithmIterationState> v = new HashSet <AlgorithmIterationState> ();
    Set <AlgorithmIterationState> r = new HashSet <AlgorithmIterationState> ();
    
    while (!st1.isEmpty()) {
      stable = true;
      AlgorithmIterationStateSucessor topST1 = st1.peek();
      BitSet m = st2.peek();
      if (!topST1.getAlgorithmIterationState().hasSucessors()) {
        AlgorithmIterationState currentState = topST1.getAlgorithmIterationState().chooseAndRemove();  // (q0', q1')
        if (!isInUnion(currentState, v, w)) {
          
        } else {
          if (!w.contains(currentState)) {
            m.set(0, 1);
          }
        }
      } else {
        st1.pop();
        st2.pop();
        v.add(topST1.getAlgorithmIterationState());
        BitSet mLinha = st2.peek();
        if (isAllSet(m)) {
          mLinha.set(0, 1);
        } else {
          w.add(topST1.getAlgorithmIterationState());
          if (r.contains(topST1.getAlgorithmIterationState())) {
            stable = false;
          }
        }
      }
    }
    BitSet m = st2.peek();
    if ((!m.get(0)) && (!m.get(1))) {
      return Result.FALSE;
    } else {
      return (stable) ? Result.TRUE : Result.UNRELIABLE;
    }
  }
  
  @Override
  public boolean isEquivalent(LTS op1, LTS op2, ProgressFeedbackListener listener) {
    do {
      Set <AlgorithmIterationState> w = new HashSet <AlgorithmIterationState> ();
      
      Result result = partialDFS(op1, op2, w, listener);
      
      if (result == Result.TRUE) {
        return true;
      } else if (result == Result.FALSE) {
        return false;
      }
    } while (true);
  }
}
