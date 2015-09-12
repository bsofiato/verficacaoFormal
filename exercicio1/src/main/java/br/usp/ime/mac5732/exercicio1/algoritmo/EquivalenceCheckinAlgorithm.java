package br.usp.ime.mac5732.exercicio1.algoritmo;

import br.usp.ime.mac5732.exercicio1.lts.LTS;

public interface EquivalenceCheckinAlgorithm {
  boolean isEquivalent(LTS op1, LTS op2, ProgressFeedbackListener listener);
}
