package br.usp.ime.mac5732.exercicio1.algoritmo.refinamentossucessivos;

public class RefineTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RefinamentosSucessivos rs = new RefinamentosSucessivos("C:\\Fabio\\lts-simplest.json");
		Partition part = rs.getPartition();
		System.out.println("Particionamento Final:");
		part.printRo();
		
		RefinamentosSucessivos rs2 = new RefinamentosSucessivos("C:\\Fabio\\lts-simplest-diferent-states.json");
		Partition part2 = rs2.getPartition();
		System.out.println("Particionamento Final:");
		part2.printRo();
		
		boolean b = Partition.comparePartitions(part, part2);
		System.out.println(b);
	}

}
