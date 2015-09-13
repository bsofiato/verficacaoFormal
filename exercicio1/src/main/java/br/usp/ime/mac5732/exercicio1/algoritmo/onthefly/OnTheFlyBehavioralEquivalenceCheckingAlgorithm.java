package br.usp.ime.mac5732.exercicio1.algoritmo.onthefly;

import br.usp.ime.mac5732.exercicio1.algoritmo.EquivalenceCheckinAlgorithm;
import br.usp.ime.mac5732.exercicio1.algoritmo.ProgressFeedbackListener;
import br.usp.ime.mac5732.exercicio1.lts.Estado;
import br.usp.ime.mac5732.exercicio1.lts.LTS;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Stream;

//PROBLEMA NO ST2
public class OnTheFlyBehavioralEquivalenceCheckingAlgorithm implements EquivalenceCheckinAlgorithm {
  private enum Result {
    TRUE, 
    FALSE, 
    UNRELIABLE
  }
  
  private boolean contains(Stack <AlgorithmIterationStateSucessor> st1, AlgorithmIterationState state) {
    return st1.stream().anyMatch(t->t.getAlgorithmIterationState().equals(state));
  }
  private boolean isInUnion(AlgorithmIterationState state, Set <AlgorithmIterationState> v, Set <AlgorithmIterationState> w) {
    return (v.contains(state) || w.contains(state));
  }
  
  private Stack <BitArray> createST2(AlgorithmIterationState estadoInicial) {
    Stack <BitArray> st2 = new Stack <BitArray> ();
    st2.push(new BitArray(estadoInicial.getQ1(), estadoInicial.getQ2()));
    st2.push(estadoInicial.createBitArray());
    return st2;
  }
  
  private Stack <AlgorithmIterationStateSucessor> createST1(AlgorithmIterationState estadoInicial) {
    Stack <AlgorithmIterationStateSucessor> st1 = new Stack<AlgorithmIterationStateSucessor> ();
    st1.add(new AlgorithmIterationStateSucessor(null, estadoInicial));
    return st1;
  }
  
  private Result partialDFS(LTS op1, LTS op2, Set <AlgorithmIterationState> w, ProgressFeedbackListener listener) {
    boolean stable = false;
    
    AlgorithmIterationState estadoInicial =  new AlgorithmIterationState(op1.getEstadoInicial(), op2.getEstadoInicial()); // (q01, q02)
        
    Stack <AlgorithmIterationStateSucessor> st1 = createST1(estadoInicial);
    Stack <BitArray> st2 = createST2(estadoInicial);
    
    Set <AlgorithmIterationState> v = new HashSet <AlgorithmIterationState> ();
    Set <AlgorithmIterationState> r = new HashSet <AlgorithmIterationState> ();
    
    while (!st1.isEmpty()) {
      stable = true;
      AlgorithmIterationStateSucessor topST1 = st1.peek();
      BitArray m = st2.peek();
      if (topST1.getAlgorithmIterationState().hasSucessors()) {
        AlgorithmIterationStateSucessor currentStateSucessor = topST1.getAlgorithmIterationState().chooseAndRemove();  // (q0', q1')
        AlgorithmIterationState currentState = currentStateSucessor.getAlgorithmIterationState();
        if (!isInUnion(currentState, v, w)) {
          if (!contains(st1, currentState)) {
            if (!currentStateSucessor.isFailure()) {
              st1.push(currentStateSucessor);
              st2.push(currentState.createBitArray());
            }
          } else {
            r.add(currentState);
            m.set(currentState);
          }
        } else {
          if (!w.contains(currentState)) {
            m.set(currentState);
          }
        }
      } else {
        st1.pop();
        st2.pop();
        v.add(topST1.getAlgorithmIterationState());
        BitArray mLinha = (BitArray)(st2.peek());
        if (m.isAllSetted()) {
          mLinha.set(topST1.getAlgorithmIterationState());
        } else {
          w.add(topST1.getAlgorithmIterationState());
          if (r.contains(topST1.getAlgorithmIterationState())) {
            stable = false;
          }
        }
      }
    }
    BitArray m = st2.peek();
    if (!m.isSetted(estadoInicial)) {
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
