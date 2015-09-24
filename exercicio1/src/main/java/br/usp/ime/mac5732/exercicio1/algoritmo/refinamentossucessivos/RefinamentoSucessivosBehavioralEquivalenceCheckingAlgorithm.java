package br.usp.ime.mac5732.exercicio1.algoritmo.refinamentossucessivos;

import br.usp.ime.mac5732.exercicio1.algoritmo.EquivalenceCheckinAlgorithm;
import br.usp.ime.mac5732.exercicio1.algoritmo.ProgressFeedbackListener;
import br.usp.ime.mac5732.exercicio1.lts.LTS;

public class RefinamentoSucessivosBehavioralEquivalenceCheckingAlgorithm implements EquivalenceCheckinAlgorithm{
  @Override
  public boolean isEquivalent(LTS op1, LTS op2, ProgressFeedbackListener listener) {
    RefinamentosSucessivos rs = new RefinamentosSucessivos(op1);
		Partition part = rs.getPartition();

		RefinamentosSucessivos rs2 = new RefinamentosSucessivos(op2);
		Partition part2 = rs2.getPartition();
		
		return Partition.comparePartitions(part, part2);    
  }
}
