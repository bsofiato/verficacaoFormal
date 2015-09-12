package br.usp.ime.mac5732.exercicio1.algoritmo.onthefly;

import br.usp.ime.mac5732.exercicio1.algoritmo.EquivalenceCheckinAlgorithm;
import br.usp.ime.mac5732.exercicio1.algoritmo.ProgressFeedbackListener;
import br.usp.ime.mac5732.exercicio1.lts.LTS;
import java.util.HashSet;
import java.util.Set;

public class OnTheFlyBehavioralEquivalenceCheckingAlgorithm implements EquivalenceCheckinAlgorithm {
  private enum Result {
    TRUE, 
    FALSE, 
    UNRELIABLE
  }
  
  private Result partialDFS(LTS op1, LTS op2, Set <AlgorithmIterationState> w, ProgressFeedbackListener listener) {
    return Result.FALSE;
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
